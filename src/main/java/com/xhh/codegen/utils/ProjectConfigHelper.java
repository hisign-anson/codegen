package com.xhh.codegen.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.xhh.codegen.model.InOutType;
import com.xhh.codegen.model.JdbcConfig;
import com.xhh.codegen.model.OutputModel;
import com.xhh.codegen.model.TemplateModel;
import com.xhh.codegen.service.BuildConfigHandler;
import com.xhh.codegen.service.ColumnHandler;
import com.xhh.codegen.service.DbProvider;

/**
 * �������ļ����ȡ��Ŀ������Ϣ�ĸ�����
 * @author ������
 *
 */
public class ProjectConfigHelper {
	private static final String CODEGEN_DEFAULT = "codegen-default.xml";
	private static final String DEFAULT_PROJECT_NAME = "defaultProject";
	private static final String USERCONFIGFILE_DEFAULT = "codegen-config.xml";
	
	private static Map<String, ProjectConfig> projectConfigMap = null;
	
	private static ProjectConfig firstProjectConfig = null;
	
	/**
	 * ȡ����Ŀ�����ļ���Ϣ�����web.xml����������Ϊ��codegen.config���������ĳ�ʼ������
	 * �������WEB-INFĿ¼�µ��ļ�·��������ļ�����Ӣ�Ķ��ŷָ�����
	 * ���ȡ��Ӧ�������ļ������δ���øò�������Ĭ�϶�ȡ��·����Ŀ¼�µ�"codegen-config.xml"��
	 * �����������������ȷ��srcĿ¼�´���codegen-config.xml�ļ���
	 * @param relativePath �����classpath����Դ·��
	 * @param projectName
	 * @return
	 */
	public static ProjectConfig getProjectConfig(String relativePath,String projectName){
		if(projectConfigMap==null){
			loadConfigFromFile(relativePath);
		}
		
		if(projectConfigMap.containsKey(projectName)){
			return projectConfigMap.get(projectName);
		}
		
		return null;
	}
	
	/**
	 * ȡ��Ĭ����Ŀ������Ϣ�����û�����ã���Ĭ��Ϊ�������ļ�������˳��ĵ�һ����Ŀ
	 * @param relativePath �����classpath����Դ·��
	 * @return
	 */
	public static ProjectConfig getDefaultProjectConfig(String relativePath){
		if(projectConfigMap==null || projectConfigMap.size()==0){
			loadConfigFromFile(relativePath);
		}
		for(Entry<String, ProjectConfig> entry : projectConfigMap.entrySet()){
			if(entry.getValue().isDefaultProject()){
				firstProjectConfig = entry.getValue();
			}
		}
		return firstProjectConfig;
	}
	
	/**
	 * ���ݵ�ǰweb�����Ļ�����ȡ������Ŀ����
	 * @param relativePath �����classpath����Դ·��
	 * @return
	 */
	public static Map<String, ProjectConfig> getAllProjectConfig(String relativePath){
		if(projectConfigMap==null){
			loadConfigFromFile(relativePath);
		}
		return projectConfigMap;
	}
	
	/**
	 * ���ݵ�ǰweb�����Ļ������¼���������Ϣ
	 * @param relativePath �����classpath����Դ·��
	 */
	public static void refreshConfig(String relativePath){
		if(projectConfigMap!=null){
			projectConfigMap.clear();
		}
		loadConfigFromFile(relativePath);
	}
	
	/**
	 * ʵ�ִ������ļ�������Ŀ������Ϣ
	 * @param relativePath �����classpath����Դ·��
	 */
	private static void loadConfigFromFile(String relativePath){
		projectConfigMap = new LinkedHashMap<String, ProjectConfig>();
		
		InputStream inputStream = null;
		try {
			if(StringUtils.isBlank(relativePath)){
				inputStream = ClassLoaderUtil.getStream(USERCONFIGFILE_DEFAULT);
				loadConfigFromInputStream(inputStream);
			}else{
				String[] configFilenames = relativePath.split(",");
				for (int i = 0; i < configFilenames.length; i++) {
					inputStream = ClassLoaderUtil.getStream(configFilenames[i]);
					loadConfigFromInputStream(inputStream);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ���������м�����Ŀ������Ϣ
	 * @param inputStream configFile
	 * @return
	 */
	private static void loadConfigFromInputStream(InputStream inputStream){
		
		ProjectConfig projectConfig = null;
		DbProvider dbProvider;
		OutputModel outputModel;		
		String key, value;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		try {				
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setEntityResolver(new CodegenDtdResolver());
			Document doc = builder.parse(inputStream); 
			Element root = doc.getDocumentElement();
			NodeList projectNodeList = root.getElementsByTagName("project"); 
			for(int j=0; j<projectNodeList.getLength(); j++){
				Element projectNode = (Element)(projectNodeList.item(j));
				String projectName = projectNode.getAttribute("name");
				String isEnabled = projectNode.getAttribute("isEnabled");
				if(StringUtils.isNotBlank(isEnabled)&&isEnabled.equalsIgnoreCase("false")){
					continue; //�������ѽ��õ�����
				}
				
				if(projectConfigMap.containsKey(projectName)){
					throw new Exception("�����ļ��д�����ͬ����Ŀ���ƣ�"+projectName);
				}
				
				if(projectNode.hasAttribute("extends")){
					String extendsProject = projectNode.getAttribute("extends");
					if(projectConfigMap.containsKey(extendsProject)==false){
						//����Ǽ̳�ϵͳĬ�����ã�����أ��������쳣
						if(DEFAULT_PROJECT_NAME.equals(extendsProject)){
							inputStream = ClassLoaderUtil.getStream(CODEGEN_DEFAULT);
							loadConfigFromInputStream(inputStream);
						}else{
							throw new Exception("�����ļ�����Ŀ��"+projectName
									+"�����̳е���Ŀ��"+extendsProject+"�������ڻ��ѽ���");
						}
					}
					projectConfig = projectConfigMap.get(extendsProject).deepClone();
				}else{				
					projectConfig = new ProjectConfig();
				}
				
				projectConfig.setProjectName(projectName);				
				projectConfig.setProjectLabel(projectNode.getAttribute("label"));
				projectConfig.setOutputEncoding(projectNode.getAttribute("outputEncoding"));
				String isDefault = projectNode.getAttribute("isDefault");
				if(StringUtils.isNotBlank(isDefault)){
					projectConfig.setDefaultProject(isDefault.equalsIgnoreCase("true"));
				}
				
				NodeList childList = projectNode.getChildNodes();
				Map<String,TemplateModel> templateModelMap = new LinkedHashMap<String,TemplateModel>();				
				for (int i = 0; i < childList.getLength(); i++) {    
					Node child = childList.item(i);    
					if (child instanceof Element)    { 	
						Element childElement = (Element) child;
						String tagName = childElement.getTagName();
						if(tagName.equals("dbProvider")){
							dbProvider= parseForDbProvider(childElement);
							projectConfig.setDbProvider(dbProvider);
						}else if(tagName.equals("dataModel")){
							key = childElement.getAttribute("name");
							value = childElement.getTextContent().trim();
							projectConfig.getDataModelMap().put(key, value);
						}else if(tagName.equals("buildConfigHandler")){
							BuildConfigHandler bcHandler = parseForDataModelHandler(childElement);
							if(bcHandler!=null){
								projectConfig.getBuildConfigHandlers().add(bcHandler);
							}
						}else if(tagName.equals("template")){
							key = childElement.getAttribute("name");
							InOutType type = InOutType.valueOf(childElement.getAttribute("type").toUpperCase());
							value = childElement.getTextContent().trim();
							TemplateModel templateModel = new TemplateModel();
							templateModel.setName(key);
							templateModel.setType(type);
							templateModel.setTemplate(value);
							templateModelMap.put(key, templateModel);
						}else if(tagName.equals("output")){
							key = childElement.getAttribute("name");
							outputModel = new OutputModel(key);
							if(StringUtils.isNotBlank(childElement.getTextContent())){
								outputModel.setOutput(childElement.getTextContent().trim());
								outputModel.setType(InOutType.valueOf(childElement.getAttribute("type").toUpperCase()));
							}else{
								outputModel.setType(InOutType.TEXT);
							}
							
							//�����ģ��������ģ��ģ�ͣ��ж�����˳���ı�>�ļ�>���� 
							TemplateModel templateModel;
							if(childElement.hasAttribute("templateText")){ //�ı�ģ��
								templateModel = new TemplateModel();
								templateModel.setName(key);
								templateModel.setType(InOutType.TEXT);
								templateModel.setTemplate(childElement.getAttribute("templateText"));
							}else if(childElement.hasAttribute("templateFile")){ //�ļ�ģ��
								templateModel = new TemplateModel();
								templateModel.setName(key);
								templateModel.setType(InOutType.FILE);
								templateModel.setTemplate(childElement.getAttribute("templateFile"));
							}else if(childElement.hasAttribute("templateName")){ //����ģ��
								templateModel = templateModelMap.get(childElement.getAttribute("templateName"));
							}else{
								templateModel = new TemplateModel();
								templateModel.setName(key);
								templateModel.setType(InOutType.FILE); //Ĭ��Ϊ�ļ�ģ������
								templateModel.setTemplate(key);	//Ĭ��ģ���ļ����ƺϳɹ��򣺵�ǰӦ����·��+"template/" + ��Ŀ���� + "/" + �������+".ftl"
							}
							outputModel.setTemplateModel(templateModel);
							if(childElement.hasAttribute("disabled")){
								outputModel.setDisabled(Boolean.parseBoolean(childElement.getAttribute("disabled")));
							}
							
							projectConfig.getOutputMap().put(outputModel.getName(), outputModel);
						}
					} 
				}
				
				projectConfigMap.put(projectConfig.getProjectName(), projectConfig);
				if(firstProjectConfig==null){
					firstProjectConfig = projectConfig;
				}
			}
			

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	/**
	 * ��DOMģ���н������ģ��
	 * @param element
	 * @return
	 *//*
	private static OutputModel parseForOutputModel(Element element) {
		String outputName = element.getAttribute("name");
		
		NodeList childNodes  = element.getChildNodes();
		OutputModel outputModel = new OutputModel(outputName);
		for (int i = 0; i < childNodes.getLength(); i++) {    
			Node child = childNodes.item(i);    
			if (child instanceof Element){ 
				Element childElement = (Element) child;
				String tagName = childElement.getTagName();
				Text textNode = (Text) childElement.getFirstChild(); 
				if(tagName.equals("template")){
					String text = textNode.getData().trim();
					outputModel.setTemplate(text);
					outputModel.setTemplateType(InOutType.valueOf(childElement.getAttribute("type")));
				}else if(tagName.equals("output")){
					if(StringUtils.isNotBlank(textNode.getData())){
						outputModel.setOutput(textNode.getData().trim());
						outputModel.setOutputType(InOutType.valueOf(childElement.getAttribute("type")));
					}else{
						outputModel.setOutputType(InOutType.TEXT);
					}
				}
			}
		}
		
		return outputModel;
	}*/

	/**
	 * ��DOMģ���н��������õ����ݿ���Ϣ�ṩ��
	 * @param element
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("rawtypes")
	private static DbProvider parseForDbProvider(Element element) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		DbProvider dbProvider=null;

		String dbProviderClsName = element.getAttribute("class");	
		NodeList jdbcConfigNodeList = element.getElementsByTagName("jdbcConfig");
		if(jdbcConfigNodeList!=null&&jdbcConfigNodeList.getLength()>0){
			Element jdbcConfigElement = (Element)jdbcConfigNodeList.item(0);
			JdbcConfig jdbcConfig = parseForJdbcConfig(jdbcConfigElement);
			Class[] ctorArgs1 = new Class[]{JdbcConfig.class};
			Constructor con = Class.forName(dbProviderClsName).getConstructor(ctorArgs1);
			dbProvider = (DbProvider) con.newInstance(jdbcConfig);
		}else{
			dbProvider = (DbProvider) Class.forName(dbProviderClsName).newInstance();
		}
		
		List<ColumnHandler> columnHandlers = parseForColumnHandlers(element.getElementsByTagName("columnHandler"));
		if(columnHandlers!=null){
			dbProvider.setColumnHandlers(columnHandlers);
		}
		
		NodeList splitorForLabelFromCommentNodeList = element.getElementsByTagName("splitorForLabelFromComment");
		if(splitorForLabelFromCommentNodeList!=null
				&&splitorForLabelFromCommentNodeList.getLength()>0){
			Element splitorForLabelFromCommentElement = (Element)splitorForLabelFromCommentNodeList.item(0);
			dbProvider.setSplitorForLabelFromComment(splitorForLabelFromCommentElement.getTextContent().trim());
		}
		
		return dbProvider;
	}
	
	private static JdbcConfig parseForJdbcConfig(Element element){
		NodeList childNodes = element.getChildNodes(); 
		JdbcConfig jdbcConfig = new JdbcConfig() ;
		for (int i = 0; i < childNodes.getLength(); i++) {    
			Node child = childNodes.item(i);    
			if (child instanceof Element){ 
				Element childElement = (Element) child;
				String tagName = childElement.getTagName();
				Text textNode = (Text) childElement.getFirstChild(); 
				String text = textNode.getData().trim();
				if(tagName.equals("driver")){
					jdbcConfig.setDriver(text);						
				}else if(tagName.equals("url")){					
					jdbcConfig.setUrl(text);						
				}else if(tagName.equals("user")){					
					jdbcConfig.setUser(text);						
				}else if(tagName.equals("password")){					
					jdbcConfig.setPassword(text);						
				}
			}			
		}
		return jdbcConfig;
	}
	
	/**
	 * ��DOMģ���н��������õ���ģ�ʹ�����
	 * @param nodeList
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static List<ColumnHandler> parseForColumnHandlers(NodeList nodeList) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<ColumnHandler> columnHandlers = new ArrayList<ColumnHandler>();
		if(nodeList==null) return columnHandlers;
		
		ColumnHandler columnHandler;
		for (int i = 0; i < nodeList.getLength(); i++) {    
			Node node = nodeList.item(i);    
			if (node instanceof Element){ 
				String columnHandlerClsName = ((Element)node).getAttribute("class");
				columnHandler = (ColumnHandler) Class.forName(columnHandlerClsName).newInstance();
				columnHandlers.add(columnHandler);
			}
		}
		
		return columnHandlers;
	}
	
	private static BuildConfigHandler parseForDataModelHandler(Element element) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String dataModelHandlerClsName = element.getAttribute("class");
		BuildConfigHandler columnHandler = (BuildConfigHandler) Class.forName(dataModelHandlerClsName).newInstance();
		return columnHandler;
	}
}
