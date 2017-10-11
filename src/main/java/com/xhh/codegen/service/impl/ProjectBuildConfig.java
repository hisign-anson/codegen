package com.xhh.codegen.service.impl;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.xhh.codegen.model.Copyright;
import com.xhh.codegen.model.InOutType;
import com.xhh.codegen.model.OutputModel;
import com.xhh.codegen.model.TableModel;
import com.xhh.codegen.service.BuildConfig;
import com.xhh.codegen.service.BuildConfigHandler;
import com.xhh.codegen.utils.BuilderHelper;
import com.xhh.codegen.utils.ClassLoaderUtil;
import com.xhh.codegen.utils.FilenameUtil;
import com.xhh.codegen.utils.ProjectConfig;

/**
 * �����Ŀ�Ĺ���������Ϣ
 * @author ������
 *
 */
public class ProjectBuildConfig implements BuildConfig {

	/**
	 * ����ģ�ͼ�����Ȩ��Ϣʵ��
	 */
	public static final String DMK_COPYRIGHT = "copyright";
	/**
	 * ����ģ�ͼ���ģ������
	 */
	public static final String DMK_MODULE_NAME = "moduleName";
	/**
	 * ����ģ�ͼ�����������
	 */
	public static final String DMK_GROUP_NAME = "groupName";
	/**
	 * ����ģ�ͼ������ǩ
	 */
	public static final String DMK_TABLE_LABEL = "tableLabel";
	/**
	 * ����ģ�ͼ�����ģ��ʵ��
	 */
	public static final String DMK_TABLE = "table";
	/**
	 * ����ģ�ͼ�������
	 */
	public static final String DMK_TABLE_NAME = "tableName";
	/**
	 * ����ģ�ͼ��������������
	 */
	public static final String DMK_OUTPUT_ENCODING = "outputEncoding";
	/**
	 * ����ģ�ͼ�����Ŀ��ǩ
	 */
	public static final String DMK_PROJECT_LABEL = "projectLabel";
	/**
	 * ����ģ�ͼ�����Ŀ����
	 */
	public static final String DMK_PROJECT_NAME = "projectName";
	/**
	 * ����ģ�ͼ���Ĭ�ϵ�ģ���ļ���
	 */
	public final static String DMK_TEMPLATEDIRECTORY = "templateDirectory";
	/**
	 * ����ģ�ͼ���Ĭ�ϵ�����ļ���
	 */
	public final static String DMK_OUTPUTDIRECTORY = "outputDirectory";

	private ProjectConfig projectConfig;
	private Map<String,Object> dataModelMap = null;
	private Map<String,OutputModel> outputModelMap = null;
	private String moduleName;
	private String tableName;
	private String tableLabel;
	private String groupName;
	private Copyright copyright = new Copyright();

	private boolean hasParseDataModel = false;//�Ƿ��ѽ���������ģ��
	private boolean hasParseOutputModel = false;//�Ƿ��ѽ��������ģ��

	/**
	 * ����һ����Ŀ����ģ��ʵ����һ����Ŀ����������Ϣ
	 * @param projectConfig ��Ŀ����ģ��
	 */
	public ProjectBuildConfig(ProjectConfig projectConfig) {
		super();
		this.projectConfig = projectConfig;
		initConfig();
	}

	/**
	 * ��ʼ��������Ϣ
	 */
	public void initConfig() {
		doInitConfig();
	}

	/**
	 * @return ��ȡ��ǰ����Ŀ����ģ��
	 */
	public ProjectConfig getProjectConfig() {
		return projectConfig;
	}

	/**
	 * @param projectConfig ���õ�ǰ����Ŀ����ģ��
	 */
	public void setProjectConfig(ProjectConfig projectConfig) {
		this.projectConfig = projectConfig;
		initConfig();
	}

	/**
	 * @return ��ȡ��ǰ������ģ������
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName ���õ�ǰ������ģ������
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return ��ȡ��ǰ�����ı�����
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName ���õ�ǰ�����ı�����
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return ��ȡ��ǰ�����ı��ǩ
	 */
	public String getTableLabel() {
		return tableLabel;
	}

	/**
	 * @param tableLabel ���õ�ǰ�����ı��ǩ
	 */
	public void setTableLabel(String tableLabel) {
		this.tableLabel = tableLabel;
	}

	/**
	 * @return ��ȡ��ǰ����������������
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName ���õ�ǰ����������������
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return ��ȡ��ǰ�����İ�Ȩ��Ϣ
	 */
	public Copyright getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright ���õ�ǰ�����İ�Ȩ��Ϣ
	 */
	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
	}


	/**
	 * ��������ģ��
	 * @param dataModelMap
	 */
	public void setDataModel(Map<String, Object> dataModelMap) {
		this.dataModelMap = dataModelMap;
	}

	/**
	 * �������ģ��
	 * @param outputModelMap
	 */
	public void setOutputModel(Map<String, OutputModel> outputModelMap) {
		this.outputModelMap = outputModelMap;
	}

	public boolean isHasParseDataModel() {
		return hasParseDataModel;
	}

	public void setHasParseDataModel(boolean hasParseDataModel) {
		this.hasParseDataModel = hasParseDataModel;
	}

	public boolean isHasParseOutputModel() {
		return hasParseOutputModel;
	}

	public void setHasParseOutputModel(boolean hasParseOutputModel) {
		this.hasParseOutputModel = hasParseOutputModel;
	}

	public Map<String, Object> getDataModel() {
		if(dataModelMap==null){
			dataModelMap = new LinkedHashMap<String, Object>();
		}
		return dataModelMap;
	}

	public Map<String, Object> parseDataModel() {
		if(hasParseDataModel){
			return dataModelMap;
		}

		doBeforeParseDataModel();

		getDataModel();//ȷ����ʵ����
		dataModelMap.put(DMK_PROJECT_NAME, projectConfig.getProjectName());
		dataModelMap.put(DMK_PROJECT_LABEL, projectConfig.getProjectLabel());
		dataModelMap.put(DMK_OUTPUT_ENCODING, projectConfig.getOutputEncoding());
		dataModelMap.put(DMK_OUTPUTDIRECTORY, getDefaultOutputDirectory());

		//������ģ��
		if(StringUtils.isBlank(getTableName())){
			//throw new Exception(this.getClass().getName()+"����tableName��ֵ������Ϊ�գ�");
		}else{
			dataModelMap.put(DMK_TABLE_NAME, getTableName());
			TableModel tm = projectConfig.getDbProvider().createTableModel(getTableName());
			if(StringUtils.isNotBlank(this.tableLabel)){
				tm.setTableLabel(this.tableLabel);
			}else{
				this.tableLabel = tm.getTableLabel();
			}
			dataModelMap.put(DMK_TABLE, tm);

			if(StringUtils.isBlank(this.groupName)&&this.tableName.contains("_")){
				this.groupName = StringUtils.substringBefore(this.tableName, "_");
			}
			if(StringUtils.isBlank(this.moduleName)){
				if(this.tableName.contains("_")){
					this.moduleName = StringUtils.substringAfter(this.tableName, "_");
				}else{
					this.moduleName = this.tableName;
				}
			}
			dataModelMap.put(DMK_TABLE_LABEL, this.tableLabel);
			dataModelMap.put(DMK_GROUP_NAME, this.groupName);
			dataModelMap.put(DMK_MODULE_NAME, this.moduleName);
		}

		//���ð�Ȩ��Ϣ
		dataModelMap.put(DMK_COPYRIGHT, this.copyright);



		//׷����Ŀ���õġ�����ģ�͡���Ϣ
		String key,value;
		for(Entry<String, String> entry: projectConfig.getDataModelMap().entrySet()){
			key = entry.getKey();
			value = entry.getValue();
			//System.out.println("��ʼ��������ģ��="+key);
			if(StringUtils.isNotBlank(value)&&value.contains("${")){
				value = BuilderHelper.parseBuildParams(dataModelMap, value); //�������й����������ַ���
			}
			dataModelMap.put(key, value);
		}

		hasParseDataModel = true;
		doAfterParseDataModel();

		return dataModelMap;
	}

	public String getOutputEncoding() {
		return projectConfig.getOutputEncoding();
	}

	public Map<String, OutputModel> getOutputModel() {
		if(outputModelMap==null){
			outputModelMap = new LinkedHashMap<String, OutputModel>();
		}
		return outputModelMap;
	}

	public Map<String, OutputModel> parseOutputModel() {
		if(hasParseOutputModel){
			return outputModelMap;
		}

		//ȷ���Ѿ���ȡ����ģ��
		if(hasParseDataModel==false){
			parseDataModel();
		}

		doBeforeParseOutputModel();

		getOutputModel();//ȷ����ʵ����
		String templateFilename,outputFilename;
		OutputModel outputModel;
		for(Entry<String,OutputModel> entry: projectConfig.getOutputMap().entrySet()){
			outputModel = entry.getValue();

			templateFilename = outputModel.getTemplateModel().getTemplate();
			if(templateFilename.contains("${")
					//���ģ�����ı����ͣ��������ﲻ��Ҫ���������������ʱ���ٽ��ͣ�
					//��ֹ��ν��ͺ�ת���ʧЧ�����⣬Ʃ�磺${'$'}{rootMap}ֻ�ܽ���һ��
					&&outputModel.getTemplateModel().getType()==InOutType.FILE){
				templateFilename = BuilderHelper.parseBuildParams(dataModelMap, templateFilename); //�������й���������ģ��
			}

			if(entry.getValue().getTemplateModel().getType()==InOutType.FILE){
				templateFilename = formatTemplateFilename(templateFilename);
			}

			outputFilename = outputModel.getOutput();
			if(StringUtils.isNotBlank(outputFilename)){
				if(outputFilename.contains("${")){
					outputFilename = BuilderHelper.parseBuildParams(dataModelMap, outputFilename); //�������й������������·��
				}
				//������Ǿ���·�������������Ĭ�����·�������·��
				if(outputFilename.contains(":")==false){
					String outputDirectory = dataModelMap.get(DMK_OUTPUTDIRECTORY).toString().replace("\\", "/");
					if(outputDirectory.endsWith("/")==false){
						outputDirectory += "/";
					}
					outputFilename = outputDirectory + outputFilename;
					outputFilename = FilenameUtil.normalize(outputFilename);
				}
			}
			try {
				outputModel = outputModel.deepClone();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			outputModel.getTemplateModel().setTemplate(templateFilename);
			outputModel.setOutput(outputFilename);
			outputModelMap.put(outputModel.getName(), outputModel);
		}
		hasParseOutputModel=true;
		doAfterParseOutputModel();

		return outputModelMap;
	}

	/**
	 * ȡ��ϵͳĬ�ϵ����Ŀ¼Ϊ��System.getProperty("user.dir")
	 * @return
	 */
	private String getDefaultOutputDirectory(){
		String defaultPath = System.getProperty("user.dir");
		return defaultPath;
	}

	/**
	 * ��ʽ��ģ���ļ���Ϊ�������ļ�URL���������δ����·������Ĭ��Ϊ���·���µ� ��template/��+��Ŀ����
	 * @return
	 * @throws IOException
	 */
	private String formatTemplateFilename(String filename){
		filename = filename.replace("\\", "/");
		if(filename.contains("/")==false){
			if(dataModelMap.containsKey(DMK_TEMPLATEDIRECTORY)){
				filename = dataModelMap.get(DMK_TEMPLATEDIRECTORY)+"/"+filename;
			}else{
				filename = "template/" + projectConfig.getProjectName()+"/"+filename;
			}
		}

		if(filename.contains(".")==false
				|| StringUtils.substringAfterLast(filename, ".").contains("/")){
			filename += ".ftl";
		}

		if(filename.contains(":")==false ){
			URL url = ClassLoaderUtil.getResource(filename);
			if(url==null){
				try {
					throw new IOException(String.format("ģ���ļ�%s�����ڣ�",filename));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				filename = url.getFile();
			}
		}

		filename = FilenameUtil.normalize(filename);
		return filename;
	}

	/**
	 * ��ʼ������ʱ����
	 */
	private void doInitConfig(){
		for (BuildConfigHandler handler : projectConfig.getBuildConfigHandlers()) {
			handler.initConfig(this);
		}
	}

	/**
	 * ����ģ�ʹ���ǰ�����Զ��Ѽ��ص���Ŀ������Ϣ�����ٴ���
	 */
	private void doBeforeParseDataModel(){
		for (BuildConfigHandler handler : projectConfig.getBuildConfigHandlers()) {
			handler.beforeParseDataModel(this);
		}
	}

	/**
	 * ����ģ�ʹ���󣬿��Զ�����װ�õ�����ģ�ͼ������ٴ���
	 */
	private void doAfterParseDataModel(){
		for (BuildConfigHandler handler : projectConfig.getBuildConfigHandlers()) {
			handler.afterParseDataModel(this);
		}
	}

	/**
	 * ��ȡ���ģ��ǰ����
	 */
	private void doBeforeParseOutputModel(){
		for (BuildConfigHandler handler : projectConfig.getBuildConfigHandlers()) {
			handler.beforeParseOutputModel(this);
		}
	}

	/**
	 * ��ȡ���ģ�ͺ���
	 */
	private void doAfterParseOutputModel(){
		for (BuildConfigHandler handler : projectConfig.getBuildConfigHandlers()) {
			handler.afterParseOutputModel(this);
		}
	}
}
