package com.xhh.codegen.model;

import java.io.Serializable;

/**
 * ���ģ��
 * @author ������
 *
 */
public class ForeignKeyModel implements Serializable{
	private static final long serialVersionUID = -660141926084273632L;
	private String pkTableCat;
	private String pkTableSchem;
	private String pkTableName;
	private String pkColumnName;
	private String fkTableCat;
	private String fkTableSchem;
	private String fkTableName;
	private String fkColumnName;
	private short keySeq;
	private short updateRule;
	private short deleteRule;
	private String fkName;
	private String pkName;
	private short Deferrability;
	/**
	 * @return ȡ������������(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public String getPkTableCat() {
		return pkTableCat;
	}
	/**
	 * @param pkTableCat ��������������(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public void setPkTableCat(String pkTableCat) {
		this.pkTableCat = pkTableCat;
	}
	/**
	 * @return ȡ��������ļܹ�(һ��Ϊ���������ƣ�����Ϊ��)
	 */
	public String getPkTableSchem() {
		return pkTableSchem;
	}
	/**
	 * @param pkTableSchem ����������ļܹ�(һ��Ϊ���������ƣ�����Ϊ��)
	 */
	public void setPkTableSchem(String pkTableSchem) {
		this.pkTableSchem = pkTableSchem;
	}
	/**
	 * @return ȡ�������������
	 */
	public String getPkTableName() {
		return pkTableName;
	}
	/**
	 * @param pkTableName ���������������
	 */
	public void setPkTableName(String pkTableName) {
		this.pkTableName = pkTableName;
	}
	/**
	 * @return ȡ�ø��������Ӧ������������
	 */
	public String getPkColumnName() {
		return pkColumnName;
	}
	/**
	 * @param pkColumnName ���ø��������Ӧ������������
	 */
	public void setPkColumnName(String pkColumnName) {
		this.pkColumnName = pkColumnName;
	}
	/**
	 * @return ȡ�ø�������ڱ�����(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public String getFkTableCat() {
		return fkTableCat;
	}
	/**
	 * @param fkTableCat ���ø�������ڱ�����(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public void setFkTableCat(String fkTableCat) {
		this.fkTableCat = fkTableCat;
	}
	/**
	 * @return ȡ�ø�������ڱ�ļܹ�(һ��Ϊ�û���������Ϊ��)
	 */
	public String getFkTableSchem() {
		return fkTableSchem;
	}
	/**
	 * @param fkTableSchem ���ø�������ڱ�ļܹ�(һ��Ϊ�û���������Ϊ��)
	 */
	public void setFkTableSchem(String fkTableSchem) {
		this.fkTableSchem = fkTableSchem;
	}
	/**
	 * @return ȡ�ø��������Ӧ�ı���
	 */
	public String getFkTableName() {
		return fkTableName;
	}
	/**
	 * @param fkTableName ���ø��������Ӧ�ı���
	 */
	public void setFkTableName(String fkTableName) {
		this.fkTableName = fkTableName;
	}
	/**
	 * @return ȡ������е�����
	 */
	public String getFkColumnName() {
		return fkColumnName;
	}
	/**
	 * @param fkColumnName ��������е�����
	 */
	public void setFkColumnName(String fkColumnName) {
		this.fkColumnName = fkColumnName;
	}
	/**
	 * @return ȡ�øü��ڼ������е�˳�򣨶������ʱ��
	 */
	public short getKeySeq() {
		return keySeq;
	}
	/**
	 * @param keySeq ���øü��ڼ������е�˳�򣨶������ʱ��
	 */
	public void setKeySeq(short keySeq) {
		this.keySeq = keySeq;
	}
	/**
	 * @return ȡ�øü��ĸ��¹���
	 */
	public short getUpdateRule() {
		return updateRule;
	}
	/**
	 * @param updateRule ���øü��ĸ��¹���
	 */
	public void setUpdateRule(short updateRule) {
		this.updateRule = updateRule;
	}
	/**
	 * @return ȡ�øü���ɾ������
	 */
	public short getDeleteRule() {
		return deleteRule;
	}
	/**
	 * @param deleteRule ���øü���ɾ������
	 */
	public void setDeleteRule(short deleteRule) {
		this.deleteRule = deleteRule;
	}
	/**
	 * @return ȡ�������
	 */
	public String getFkName() {
		return fkName;
	}
	/**
	 * @param fkName ���������
	 */
	public void setFkName(String fkName) {
		this.fkName = fkName;
	}
	/**
	 * @return ȡ��������
	 */
	public String getPkName() {
		return pkName;
	}
	/**
	 * @param pkName ����������
	 */
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	/**
	 * @return ȡ��ָʾ�Ƿ���ӳ�Լ����顣
	 */
	public short getDeferrability() {
		return Deferrability;
	}
	/**
	 * @param deferrability �����Ƿ���ӳ�Լ����顣
	 */
	public void setDeferrability(short deferrability) {
		Deferrability = deferrability;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ForeignKeyModel [Deferrability=");
		builder.append(Deferrability);
		builder.append(", deleteRule=");
		builder.append(deleteRule);
		builder.append(", fkColumnName=");
		builder.append(fkColumnName);
		builder.append(", fkName=");
		builder.append(fkName);
		builder.append(", fkTableCat=");
		builder.append(fkTableCat);
		builder.append(", fkTableName=");
		builder.append(fkTableName);
		builder.append(", fkTableSchem=");
		builder.append(fkTableSchem);
		builder.append(", keySeq=");
		builder.append(keySeq);
		builder.append(", pkColumnName=");
		builder.append(pkColumnName);
		builder.append(", pkName=");
		builder.append(pkName);
		builder.append(", pkTableCat=");
		builder.append(pkTableCat);
		builder.append(", pkTableName=");
		builder.append(pkTableName);
		builder.append(", pkTableSchem=");
		builder.append(pkTableSchem);
		builder.append(", updateRule=");
		builder.append(updateRule);
		builder.append("]");
		return builder.toString();
	}
	
	
}
