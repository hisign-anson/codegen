package com.xhh.codegen.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xhh.codegen.model.OutputModel;
import com.xhh.codegen.service.BuildConfigHandler;
import com.xhh.codegen.service.DbProvider;



/**
 * ��Ŀ����ģ��
 * @author ������
 *
 */
public class ProjectConfig  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String projectName;
	private String projectLabel;
	private String outputEncoding = "UTF-8";
	private DbProvider dbProvider;
	private Map<String, String> dataModelMap = new LinkedHashMap<String,String>();
	private Map<String, OutputModel> outputMap = new LinkedHashMap<String,OutputModel>();
	private boolean defaultProject;
	/**
	 * ����ģ�ʹ�����
	 */
	private List<BuildConfigHandler> buildConfigHandlers = new ArrayList<BuildConfigHandler>();
	
	
	/**
	 * @return ȡ����Ŀ����
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName ������Ŀ����
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return ȡ����Ŀ��ǩ������������
	 */
	public String getProjectLabel() {
		return projectLabel;
	}

	/**
	 * @param projectLabel ������Ŀ��ǩ������������
	 */
	public void setProjectLabel(String projectLabel) {
		this.projectLabel = projectLabel;
	}

	/**
	 * @return ȡ��������뷽ʽ
	 */
	public String getOutputEncoding() {
		return outputEncoding;
	}

	/**
	 * @param outputEncoding ����������뷽ʽ
	 */
	public void setOutputEncoding(String outputEncoding) {
		this.outputEncoding = outputEncoding;
	}

	/**
	 * @return ȡ�����ݿ���Ϣ�ṩ��
	 */
	public DbProvider getDbProvider() {
		return dbProvider;
	}

	/**
	 * @param dbProvider �������ݿ���Ϣ�ṩ��
	 */
	public void setDbProvider(DbProvider dbProvider) {
		this.dbProvider = dbProvider;
	}

	/**
	 * @return ȡ������ģ��ӳ�伯��
	 */
	public Map<String, String> getDataModelMap() {
		return dataModelMap;
	}

	/**
	 * @param dataModelMap ��������ģ��ӳ�伯��
	 */
	public void setDataModelMap(Map<String, String> dataModelMap) {
		this.dataModelMap = dataModelMap;
	}

	/**
	 * @return ȡ�����ӳ�伯��
	 */
	public Map<String, OutputModel> getOutputMap() {
		return outputMap;
	}

	/**
	 * @param outputMap �������ӳ�伯��
	 */
	public void setOutputMap(Map<String, OutputModel> outputMap) {
		this.outputMap = outputMap;
	}

	/**
	 * @return �Ƿ�ΪĬ����Ŀ
	 */
	public boolean isDefaultProject() {
		return defaultProject;
	}

	/**
	 * @param defaultProject �����Ƿ�ΪĬ����Ŀ
	 */
	public void setDefaultProject(boolean defaultProject) {
		this.defaultProject = defaultProject;
	}
	

	/**
	 * @return ��ȡ�������ô�����
	 */
	public List<BuildConfigHandler> getBuildConfigHandlers() {
		return buildConfigHandlers;
	}

	/**
	 * @param buildConfigHandlers ���ù������ô�����
	 */
	public void setBuildConfigHandlers(List<BuildConfigHandler> buildConfigHandlers) {
		this.buildConfigHandlers = buildConfigHandlers;
	}

	/**
	 * ʹ�����л���ʽ��ȿ�¡��Ŀ����ģ��
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    public ProjectConfig deepClone() throws IOException, ClassNotFoundException {
    	ProjectConfig dc = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream bis = new ObjectInputStream(bais);
        dc = (ProjectConfig)bis.readObject();
        return dc;
    }
}
