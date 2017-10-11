package com.xhh.codegen.model;

import java.io.Serializable;

/**
 * ��Ԫ����
 * @author ������
 *
 */
public class TableMetaData implements Serializable{
	private static final long serialVersionUID = 335136284779416764L;
	/*TABLE_CAT String => table catalog (may be null) 
	TABLE_SCHEM String => table schema (may be null) 
	TABLE_NAME String => table name 
	TABLE_TYPE String => table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM". 
	REMARKS String => explanatory comment on the table 
	TYPE_CAT String => the types catalog (may be null) 
	TYPE_SCHEM String => the types schema (may be null) 
	TYPE_NAME String => type name (may be null) 
	SELF_REFERENCING_COL_NAME String => name of the designated "identifier" column of a typed table (may be null) 
	REF_GENERATION String => specifies how values in SELF_REFERENCING_COL_NAME are created. Values are "SYSTEM", "USER", "DERIVED". (may be null) */
	private String tableCat;
	private String tableSchem;
	private String tableName;
	private String tableType;
	private String remarks;
	private String typeCat;
	private String typeSchem;
	private String typeName;
	private String selfReferencingColName;
	private String refGeneration;
	/**
	 * @return ȡ�ñ���𣨿�Ϊ null��
	 */
	public String getTableCat() {
		return tableCat;
	}
	/**
	 * @param tableCat ���ñ���𣨿�Ϊ null��
	 */
	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}
	/**
	 * @return ȡ�ñ�ģʽ����Ϊ null��
	 */
	public String getTableSchem() {
		return tableSchem;
	}
	/**
	 * @param tableSchem ���ñ�ģʽ����Ϊ null��
	 */
	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}	
	/**
	 * @return ȡ�ñ�����
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName ���ñ�����
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return ȡ�ñ����͡����͵������� "TABLE"��"VIEW"��"SYSTEM TABLE"��"GLOBAL TEMPORARY"��"LOCAL TEMPORARY"��"ALIAS" �� "SYNONYM"��
	 */
	public String getTableType() {
		return tableType;
	}
	/**
	 * @param tableType ���ñ����͡����͵������� "TABLE"��"VIEW"��"SYSTEM TABLE"��"GLOBAL TEMPORARY"��"LOCAL TEMPORARY"��"ALIAS" �� "SYNONYM"��
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	/**
	 * @return ȡ�ñ�Ľ�����ע��
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks ���ñ�Ľ�����ע��
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return ȡ�����͵���𣨿�Ϊ null��
	 */
	public String getTypeCat() {
		return typeCat;
	}
	/**
	 * @param typeCat �������͵���𣨿�Ϊ null��
	 */
	public void setTypeCat(String typeCat) {
		this.typeCat = typeCat;
	}
	/**
	 * @return ȡ������ģʽ����Ϊ null��
	 */
	public String getTypeSchem() {
		return typeSchem;
	}
	/**
	 * @param typeSchem ��������ģʽ����Ϊ null��
	 */
	public void setTypeSchem(String typeSchem) {
		this.typeSchem = typeSchem;
	}
	/**
	 * @return ȡ���������ƣ���Ϊ null��
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName �����������ƣ���Ϊ null��
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return ȡ�������ͱ��ָ�� "identifier" �е����ƣ���Ϊ null��
	 */
	public String getSelfReferencingColName() {
		return selfReferencingColName;
	}
	/**
	 * @param selfReferencingColName ���������ͱ��ָ�� "identifier" �е����ƣ���Ϊ null��
	 */
	public void setSelfReferencingColName(String selfReferencingColName) {
		this.selfReferencingColName = selfReferencingColName;
	}
	/**
	 * @return ȡ��ָ���� SELF_REFERENCING_COL_NAME �д���ֵ�ķ�ʽ����ЩֵΪ "SYSTEM"��"USER" �� "DERIVED"��������Ϊ null��
	 */
	public String getRefGeneration() {
		return refGeneration;
	}
	/**
	 * @param refGeneration ����ָ���� SELF_REFERENCING_COL_NAME �д���ֵ�ķ�ʽ����ЩֵΪ "SYSTEM"��"USER" �� "DERIVED"��������Ϊ null��
	 */
	public void setRefGeneration(String refGeneration) {
		this.refGeneration = refGeneration;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TableMetaData [tableCat=");
		builder.append(tableCat);
		builder.append(", tableSchem=");
		builder.append(tableSchem);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", tableType=");
		builder.append(tableType);
		builder.append(", remarks=");
		builder.append(remarks);
		builder.append(", typeCat=");
		builder.append(typeCat);
		builder.append(", typeSchem=");
		builder.append(typeSchem);
		builder.append(", typeName=");
		builder.append(typeName);
		builder.append(", selfReferencingColName=");
		builder.append(selfReferencingColName);
		builder.append(", refGeneration=");
		builder.append(refGeneration);
		builder.append("]");
		return builder.toString();
	}
	
}
