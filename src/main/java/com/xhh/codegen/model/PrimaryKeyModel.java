package com.xhh.codegen.model;

import java.io.Serializable;

/**
 * ����ģ��
 * @author ������
 *
 */
public class PrimaryKeyModel implements Serializable{
	private static final long serialVersionUID = -7634955376647599763L;
	private String tableCat;
	private String tableSchem;
	private String tableName;
	private String columnName;
	private short keySeq;
	private String pkName;

	/**
	 * @return ȡ�ñ�Ŀ¼(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public String getTableCat() {
		return tableCat;
	}
	/**
	 * @param tableCat ���ñ�Ŀ¼(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}
	/**
	 * @return ȡ�ñ�ܹ�(һ��Ϊ���������ƣ�����Ϊ��)
	 */
	public String getTableSchem() {
		return tableSchem;
	}
	/**
	 * @param tableSchem ���ñ�ܹ�(һ��Ϊ���������ƣ�����Ϊ��)
	 */
	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}
	/**
	 * @return ȡ���ṩ��������Ϣ�ı���
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName �����ṩ��������Ϣ�ı���
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return ȡ�ø�������Ӧ��������
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName ���ø�������Ӧ��������
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return ȡ�ø������ڼ������е���ţ��������ʱ��
	 */
	public short getKeySeq() {
		return keySeq;
	}
	/**
	 * @param keySeq ���ø������ڼ������е���ţ��������ʱ��
	 */
	public void setKeySeq(short keySeq) {
		this.keySeq = keySeq;
	}
	/**
	 * @return ȡ�ø���������
	 */
	public String getPkName() {
		return pkName;
	}
	/**
	 * @param pkName ���ø���������
	 */
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrimaryKeyModel [columnName=");
		builder.append(columnName);
		builder.append(", keySeq=");
		builder.append(keySeq);
		builder.append(", pkName=");
		builder.append(pkName);
		builder.append(", tableCat=");
		builder.append(tableCat);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", tableSchem=");
		builder.append(tableSchem);
		builder.append("]");
		return builder.toString();
	}



}
