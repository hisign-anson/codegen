package com.xhh.codegen.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ���ģ��
 * @author ������
 *
 */
public class OutputModel  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private TemplateModel templateModel;
	private InOutType type;
	private String output;
	private boolean disabled = false;
	
	/**
	 * ��һ���������ʵ����һ�����ģ��
	 * @param name �������
	 */
	public OutputModel(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * @return ȡ���������
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name �����������
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	/**
	 * @return ��ȡ�����������ģ��ģ��
	 */
	public TemplateModel getTemplateModel() {
		return templateModel;
	}

	/**
	 * @param templateModel ���ò����������ģ��ģ��
	 */
	public void setTemplateModel(TemplateModel templateModel) {
		this.templateModel = templateModel;
	}

	/**
	 * @return ��ȡ�������
	 */
	public InOutType getType() {
		return type;
	}

	/**
	 * @param type �����������
	 */
	public void setType(InOutType type) {
		this.type = type;
	}

	/**
	 * @return ȡ����Ҫ������ļ������type=textʱ����ִ�й���������ȡ�õ��Ǳ������������ı�����
	 */
	public String getOutput() {
		return output;
	}
	/**
	 * @param output ������Ҫ������ļ�
	 */
	public void setOutput(String output) {
		this.output = output;
	}
	
	
	/**
	 * @return �Ƿ���ã�Ĭ��Ϊfalse��
	 */
	public boolean isDisabled() {
		return disabled;
	}
	/**
	 * @param disabled �����Ƿ���ã�Ĭ��Ϊfalse��
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * ʹ�����л���ʽ��ȿ�¡���ģ��
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    public OutputModel deepClone() throws IOException, ClassNotFoundException {
    	OutputModel dc = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream bis = new ObjectInputStream(bais);
        dc = (OutputModel)bis.readObject();
        return dc;
    }
	
}
