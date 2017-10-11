package com.xhh.codegen.model;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.CycleRecoverable;

/**
 * ��ģ��
 * @author ������
 *
 */
@SuppressWarnings("restriction")
public class TableModel  implements CycleRecoverable{
	/**
	 * ����
	 */
	private String tableName;

	/**
	 * ���������
	 */
	private String catalog;
	/**
	 * ������ģʽ
	 */
	private String schema;
	/**
	 * �����͡����͵������� "TABLE"��"VIEW"��"SYSTEM TABLE"��"GLOBAL TEMPORARY"��"LOCAL TEMPORARY"��"ALIAS" �� "SYNONYM"��
	 */
	private String tableType;

	/**
	 * ��ע��(����)
	 */
	private String tabComment;
	/**
	 * ���ǩ Ҳ���Ǳ�����ı���
	 */
	private String tableLabel;

	/**
	 * ��ģ�ͼ���
	 */
	private List<ColumnModel> columnList=new ArrayList<ColumnModel>();

	/**
	 * ����ģ�ͼ���
	 */
	private List<PrimaryKeyModel> primaryKeyList=new ArrayList<PrimaryKeyModel>();

	/**
	 * ���ģ�ͼ��ϣ����룩
	 */
	private List<ForeignKeyModel> importedKeyList=new ArrayList<ForeignKeyModel>();
	/**
	 * ���ģ�ͼ��ϣ�������
	 */
	private List<ForeignKeyModel> exportedKeyList=new ArrayList<ForeignKeyModel>();


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
	 * @return ȡ�ñ��Ŀ¼����(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public String getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog ���ñ��Ŀ¼����(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	/**
	 * @return ȡ�ñ�ļܹ�����(һ��Ϊ��������ߣ�����Ϊ��)
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema ���ñ�ļܹ�����(һ��Ϊ��������ߣ�����Ϊ��)
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * @return ȡ�ñ����͡����͵������� "TABLE"��"VIEW"��"SYSTEM TABLE"��"GLOBAL TEMPORARY"��"LOCAL TEMPORARY"��"ALIAS" �� "SYNONYM"��
	 */
	public String getTableType() {
		return tableType;
	}

	/**
	 * @param tableType ���ñ�����͡����͵������� "TABLE"��"VIEW"��"SYSTEM TABLE"��"GLOBAL TEMPORARY"��"LOCAL TEMPORARY"��"ALIAS" �� "SYNONYM"��
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	/**
	 * @return ȡ�ñ��ע��
	 */
	public String getTabComment() {
		return tabComment;
	}

	/**
	 * @param tabComment ���ñ��ע��
	 */
	public void setTabComment(String tabComment) {
		this.tabComment = tabComment;
	}

	/**
	 * @return ȡ�ñ����ʾ��ǩ
	 */
	public String getTableLabel() {
		return tableLabel;
	}

	/**
	 * @param tableLabel ���ñ����ʾ��ǩ
	 */
	public void setTableLabel(String tableLabel) {
		this.tableLabel = tableLabel;
	}

	/**
	 * @return ȡ�øñ��°�����������ģ��
	 */
	public List<ColumnModel> getColumnList() {
		return columnList;
	}

	/**
	 * @param columnList ���øñ��°�����������ģ��
	 */
	public void setColumnList(List<ColumnModel> columnList) {
		this.columnList = columnList;
	}

	/**
	 * @return ȡ�øñ��°�������������ģ��
	 */
	public List<PrimaryKeyModel> getPrimaryKeyList() {
		return primaryKeyList;
	}

	/**
	 * @param primaryKeyList ���øñ��°�������������ģ��
	 */
	public void setPrimaryKeyList(List<PrimaryKeyModel> primaryKeyList) {
		this.primaryKeyList = primaryKeyList;
	}

	/**
	 * @return ȡ�øñ�����ģ�ͼ��ϣ����룩
	 */
	public List<ForeignKeyModel> getImportedKeyList() {
		return importedKeyList;
	}

	/**
	 * @param importedKeyList ���øñ�����ģ�ͼ��ϣ����룩
	 */
	public void setImportedKeyList(List<ForeignKeyModel> importedKeyList) {
		this.importedKeyList = importedKeyList;
	}

	/**
	 * @return ȡ�øñ�����ģ�ͼ��ϣ�������
	 */
	public List<ForeignKeyModel> getExportedKeyList() {
		return exportedKeyList;
	}

	/**
	 * @param exportedKeyList ���øñ�����ģ�ͼ��ϣ�������
	 */
	public void setExportedKeyList(List<ForeignKeyModel> exportedKeyList) {
		this.exportedKeyList = exportedKeyList;
	}

	/**
	 * ��������ȡ����ģ��
	 * @param name ������
	 * @return ���ص���ģ��
	 */
	public ColumnModel getColumnByName(String name){
		for(ColumnModel cm : columnList){
			if(cm.getColumnName().equalsIgnoreCase(name)) return cm;
		}
		return null;
	}

	/**
	 * �ж��Ƿ�������
	 * @return ������򷵻�true�����򷵻�false
	 */
	public boolean hasPrimaryKey(){
		return primaryKeyList!=null&&primaryKeyList.size()>0;
	}
	/**
	 * �ж�ָ�������Ƿ�Ϊ����
	 * @param columnName Ҫ�жϵ�������
	 * @return ���ָ����Ϊ�����򷵻�true�����򷵻�false
	 */
	public boolean isPrimaryKey(String columnName){
		boolean result = false;
		for(PrimaryKeyModel pkModel : primaryKeyList){
			if(pkModel.getColumnName().equalsIgnoreCase(columnName)){
				result = true; break;
			}
		}
		return result;
	}

	/**
	 * �жϸñ��Ƿ����������(����������ļ�)
	 * @return ������������򷵻�true�����򷵻�false
	 */
	public boolean hasImportedKey(){
		return importedKeyList!=null&&importedKeyList.size()>0;
	}
	/**
	 * �жϸñ�ָ�������Ƿ�Ϊ�������(����������ļ�)
	 * @param columnName ������
	 * @return ����ñ�ָ���������������򷵻�true�����򷵻�false
	 */
	public boolean isImportedKey(String columnName){
		boolean result = false;
		for(ForeignKeyModel fkModel : importedKeyList){
			if(fkModel.getFkColumnName().equalsIgnoreCase(columnName)){
				result = true; break;
			}
		}
		return result;
	}

	/**
	 * �жϸñ��Ƿ��б���������յļ�
	 * @return ������ڱ���������յļ��򷵻�true�����򷵻�false
	 */
	public boolean hasExportedKey(){
		return exportedKeyList!=null&&exportedKeyList.size()>0;
	}
	/**
	 * �жϸñ�ָ�������Ƿ�Ϊ�������������������յļ���
	 * @param columnName ������
	 * @return ����ñ�ָ���б�������������Ϊ����򷵻�true�����򷵻�false
	 */
	public boolean isExportedKey(String columnName){
		boolean result = false;
		for(ForeignKeyModel fkModel : exportedKeyList){
			if(fkModel.getFkColumnName().equalsIgnoreCase(columnName)){
				result = true; break;
			}
		}
		return result;
	}

	/**
	 * ʵ�ָýӿ��Է�ֹ�����л����¡ʱ������ѭ����������
	 */
	public Object onCycleDetected(Context arg0) {
		TableModel temp = new TableModel();

		temp.setTableName(tableName);

		return temp;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TableModel)) {
			return false;
		}
		TableModel other = (TableModel) obj;
		if (tableName == null) {
			if (other.tableName != null) {
				return false;
			}
		} else if (!tableName.equals(other.tableName)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TableModel [catalog=");
		builder.append(catalog);
		builder.append(", columnList=");
		builder.append(columnList);
		builder.append(", importedKeyList=");
		builder.append(importedKeyList);
		builder.append(", exportedKeyList=");
		builder.append(exportedKeyList);
		builder.append(", primaryKeyList=");
		builder.append(primaryKeyList);
		builder.append(", schema=");
		builder.append(schema);
		builder.append(", tabComment=");
		builder.append(tabComment);
		builder.append(", tableLabel=");
		builder.append(tableLabel);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append("]");
		return builder.toString();
	}


}
