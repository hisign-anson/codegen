package com.xhh.codegen.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * ��Ȩģ��
 * @author ������
 *
 */
public class Copyright implements Serializable{
	private static final long serialVersionUID = -7202315051356733125L;
	private String description="";
	private String author=System.getProperty("user.name");
	private String createDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	private String authorEmail="360138308@qq.com";
	private String modifier=author;
	private String modifyDate=createDate;
	private String modifierEmail=authorEmail;
	private String company="BCSSOFT";
	private String version="1.0";
	
	/**
	 * ����һ����Ȩ��Ϣʵ��
	 */
	public Copyright() {
		if(StringUtils.isBlank(author)){
			this.author = "codegen";
		}
	}
	/**
	 * ��һ���������ƹ���һ����Ȩ��Ϣʵ��
	 * @param author ��������
	 */
	public Copyright(String author) {
		super();
		this.author = author;
	}
	/**
	 * @return ȡ�ð汾������Ϣ
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description ���ð汾������Ϣ
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return ȡ��������Ϣ
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author ����������Ϣ
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return ȡ�ô�������
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate ���ô�������
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return ȡ�����ߵ�Email
	 */
	public String getAuthorEmail() {
		return authorEmail;
	}
	/**
	 * @param authorEmail �������ߵ�Email
	 */
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	/**
	 * @return ȡ���޸�����Ϣ
	 */
	public String getModifier() {
		return modifier;
	}
	/**
	 * @param modifier �����޸�����Ϣ
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
	 * @return ȡ���޸�����
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate �����޸�����
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return ȡ���޸��˵�Email
	 */
	public String getModifierEmail() {
		return modifierEmail;
	}
	/**
	 * @param modifierEmail �����޸��˵�Email
	 */
	public void setModifierEmail(String modifierEmail) {
		this.modifierEmail = modifierEmail;
	}
	/**
	 * @return ȡ�ù�˾��Ϣ
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company ���ù�˾��Ϣ
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return ȡ�ð汾��
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version ���ð汾��
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Copyright [author=");
		builder.append(author);
		builder.append(", authorEmail=");
		builder.append(authorEmail);
		builder.append(", company=");
		builder.append(company);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", description=");
		builder.append(description);
		builder.append(", modifier=");
		builder.append(modifier);
		builder.append(", modifierEmail=");
		builder.append(modifierEmail);
		builder.append(", modifyDate=");
		builder.append(modifyDate);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
	
	
}
