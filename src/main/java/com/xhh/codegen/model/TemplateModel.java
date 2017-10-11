package com.xhh.codegen.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ģ��ģ��
 * @author ������
 *
 */
public class TemplateModel implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String name;
	private InOutType type;
	private String template;
	
	
	/**
	 * @return ��ȡģ������
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name ����ģ������
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return ��ȡģ������
	 */
	public InOutType getType() {
		return type;
	}
	/**
	 * @param type ����ģ������
	 */
	public void setType(InOutType type) {
		this.type = type;
	}
	/**
	 * @return ��ȡģ�����ݻ�ģ��·�������ģ������Ϊtext�����ص������ݣ����򷵻�ģ���ļ�·��
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template ����ģ�����ݻ����ڵ��ļ�·�������ģ������Ϊtext�������õ������ݣ�����Ϊ�ļ�·��
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	
	/**
	 * ʹ�����л���ʽ��ȿ�¡ģ�����ģ��
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    public TemplateModel deepClone() throws IOException, ClassNotFoundException {
    	TemplateModel dc = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream bis = new ObjectInputStream(bais);
        dc = (TemplateModel)bis.readObject();
        return dc;
    }

	
}
