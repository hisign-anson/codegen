package com.xhh.codegen.model;

import com.sun.xml.internal.bind.CycleRecoverable;

/**
 * ��ģ�͡�ע��������ں�TableModel�����໥���õĹ�ϵ�����ʵ����һ���ӿ�CycleRecoverable����ֹ�����л����¡ʱ������ѭ����������
 * @author ������
 *
 */
public class ColumnModel implements CycleRecoverable{
	/**
	 * ���� ��һ����ֶ�������ͬ����Ϊ�����ӿɶ��ԣ������ƿ��Բ�ͬ���ֶ�����������һ�㲻�����»��ߣ��п�����Ҫ���滻���»��߲��ý������������ĸ��д��
	 */
    private String columnName;
    /**
     * �ֶ��� ��Ӧ���ݿ����ʵ�ֶ����ƣ�һ�㲻�ɸ���
     */
    private String fieldName;
    /**
     * ��ע��
     */
    private String colComment;
    /**
     * �б�ǩ����ע�͵ı�ǩ���֡����ڴ�ӡ�������ʾ��ָ���еĽ�����⣨���ģ�
     */
    private String columnLabel;
    /**
     * ��ע�͵ı�ע����
     */
    private String colRemark;
    /**
     * �����������������ļ���������ȫ�޶�������Ĭ�ϵı������ΪJava����String
     */
    private String columnSimpleClassName;
    
    /**
     * ��������������������ȫ�޶����ƣ�Ĭ�ϵı������ΪJava���磺java.lang.String��
     */
    private String columnClassName;
    
    /**
     * ���������������������ڵİ�(�����ռ�)��Ĭ�ϵı������ΪJava���磺java.lang
     */
    private String columnClassPackage;
    
    /**
     * �е� SQL ���͡�
     */
    private int columnType;
    
    /**
     * �е� SQL��������
     */
    private String columnTypeName;
    
    /**
     * ��ȡָ���е�ָ���п�������ֵ�����ݣ���ָ��󾫶ȡ������ַ������ݣ���ָ�ַ������ȡ�
     * ��������ʱ����������ͣ���ָ String ��ʾ��ʽ���ַ������ȣ��ٶ�Ϊ��������С�����������
     * ���ڶ����������ݣ���ָ�ֽڳ��ȡ����� ROWID �������ͣ���ָ�ֽڳ��ȡ�
     * �������д�С�����õ��������ͣ��򷵻� 0��
     */
    private int precision=0;
    /**
     * �е�С�����ұߵ�λ�����������Ȳ����õ��������ͣ�Ĭ��Ϊ 0��
     */
    private  int scale=0;
    
    /**
     * �е�����׼��ȣ����ַ�Ϊ��λ��
     */
    private int columnDisplaySize;
    /**
     * ��ʶ�����Ƿ�Ϊ����
     */
    private boolean primaryKey=false;
    /**
     * ��ʶ�����Ƿ�����У�����������ļ���
     */
    private boolean importedKey=false;
    /**
     * ��ʶ�����Ƿ�����У�����������յļ���
     */
    private boolean exportedKey=false;
    /**
     * ��ʶ���е�ֵ�ܷ�Ϊ��
     */
    private boolean nullable=true;
    /**
     * ��ʶ�����Ƿ�Ϊ������
     */
    private boolean autoIncrement = false;
    /**
     * ��ʶ�����Ƿ�Ϊ��������
     */
    private boolean currency = false;
    /**
     * ��ʶ�����Ƿ�Ϊֻ����
     */
    private boolean readonly = false;
    /**
     * ��ʶ�����ܷ���Ϊ�����У�������where������
     */
    private boolean searchable = true;
    
    private TableModel tableModel;
    
    /**
     * @return ȡ�������ƣ�������һ����ֶ�������ͬ����Ϊ�����ӿɶ��ԣ������ƿ��Բ�ͬ���ֶ�����
     * ������һ�㲻�����»��ߣ��п�����Ҫ���滻���»��߲��ý������������ĸ��д
     */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName Ҫ���õ������ƣ�������һ����ֶ�������ͬ����Ϊ�����ӿɶ��ԣ������ƿ��Բ�ͬ���ֶ�����
     * ������һ�㲻�����»��ߣ��п�����Ҫ���滻���»��߲��ý������������ĸ��д
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	/**
	 * @return ȡ�ö�Ӧ���ݿ���ֶ����ƣ�һ�㲻�ɸ���
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName ���ö�Ӧ���ݿ���ֶ�����
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return ȡ����ע��
	 */
	public String getColComment() {
		return colComment;
	}

	/**
	 * @param colComment Ҫ���õ���ע��
	 */
	public void setColComment(String colComment) {
		this.colComment = colComment;
	}
	
	
	/**
	 * @return ȡ���б�ע��Ϣ
	 */
	public String getColRemark() {
		return colRemark;
	}

	/**
	 * @param colRemark Ҫ���õ��б�ע��Ϣ
	 */
	public void setColRemark(String colRemark) {
		this.colRemark = colRemark;
	}

	/**
	 * @return ȡ�������������������ļ���������ȫ�޶��������Java�е�String
	 */
	public String getColumnSimpleClassName() {
		return columnSimpleClassName;
	}

	/**
	 * @param columnSimpleClassName Ҫ���õ������������������ļ���������ȫ�޶��������java�е�String
	 */
	public void setColumnSimpleClassName(String columnSimpleClassName) {
		this.columnSimpleClassName = columnSimpleClassName;
	}

	/**
	 * @return ȡ����������������������ȫ�޶����ƣ���java�е�java.lang.String��
	 */
	public String getColumnClassName() {
		return columnClassName;
	}

	/**
	 * @param columnClassName Ҫ���õ���������������������ȫ�޶����ƣ���java�е�java.lang.String��
	 */
	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}

	/**
	 * @return ȡ�����������������������ڵİ�(�����ռ�)����java�е�java.lang
	 */
	public String getColumnClassPackage() {
		return columnClassPackage;
	}

	/**
	 * @param columnClassPackage Ҫ���õ����������������������ڵİ�(�����ռ�)����java�е�java.lang
	 */
	public void setColumnClassPackage(String columnClassPackage) {
		this.columnClassPackage = columnClassPackage;
	}

	/**
	 * @return ��ȡָ���е�ָ���п�
	 * <br>������ֵ�����ݣ���ָ��󾫶ȡ������ַ������ݣ���ָ�ַ������ȡ�
     * <br>��������ʱ����������ͣ���ָ String ��ʾ��ʽ���ַ������ȣ��ٶ�Ϊ��������С�����������
     * <br>���ڶ����������ݣ���ָ�ֽڳ��ȡ�
     * <br>���� ROWID �������ͣ���ָ�ֽڳ��ȡ�
     * <br>�������д�С�����õ��������ͣ��򷵻� 0��
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * @param precision Ҫ���õ�ָ���е�ָ���п�
	 * <br>������ֵ�����ݣ���ָ��󾫶ȡ������ַ������ݣ���ָ�ַ������ȡ�
     * <br>��������ʱ����������ͣ���ָ String ��ʾ��ʽ���ַ������ȣ��ٶ�Ϊ��������С�����������
     * <br>���ڶ����������ݣ���ָ�ֽڳ��ȡ�
     * <br>���� ROWID �������ͣ���ָ�ֽڳ��ȡ�
     * <br>�������д�С�����õ��������ͣ���Ϊ 0��
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/**
	 * @return ��ȡ�е�С�����ұߵ�λ�����������Ȳ����õ��������ͣ�Ĭ��Ϊ 0��
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * @param scale Ҫ���õ��е�С�����ұߵ�λ�����������Ȳ����õ��������ͣ�Ĭ��Ϊ 0��
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}

	/**
	 * @return �жϸ����Ƿ�Ϊ������
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey ���ø���Ϊ������
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return �жϸ����Ƿ�Ϊ���������������ļ���
	 */
	public boolean isImportedKey() {
		return importedKey;
	}

	/**
	 * @param importedKey ���ø���Ϊ����У�����������ļ���
	 */
	public void setImportedKey(boolean importedKey) {
		this.importedKey = importedKey;
	}

	/**
	 * @return �жϸ����Ƿ����������ã�����������յļ���
	 */
	public boolean isExportedKey() {
		return exportedKey;
	}

	/**
	 * @param exportedKey ���ø��б����������ã�����������յļ���
	 */
	public void setExportedKey(boolean exportedKey) {
		this.exportedKey = exportedKey;
	}

	/**
	 * @return �жϸ����Ƿ������
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * @param nullable ���ø����ܷ�Ϊ��
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * ȡ����java.sql.Types���������
	 * @return ��ȡָ���е� SQL ���͡�
	 */
	public int getColumnType() {
		return columnType;
	}

	/**
	 * ������java.sql.Types���������
	 * @param columnType the columnType to set
	 */
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}

	/**
	 * @return ��ȡָ���е� ��java.sql.Types��������͵����ơ�
	 */
	public String getColumnTypeName() {
		return columnTypeName;
	}

	/**
	 * @param columnTypeName ������java.sql.Types��������͵�����
	 */
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}

	/**
	 * @return ������Ϊָ���п�ȵ�����׼�ַ���
	 */
	public int getColumnDisplaySize() {
		return columnDisplaySize;
	}

	/**
	 * @param columnDisplaySize ָ���п�ȵ�����׼�ַ���
	 */
	public void setColumnDisplaySize(int columnDisplaySize) {
		this.columnDisplaySize = columnDisplaySize;
	}

	/**
	 * @return ȡ���б�ǩ����ע�͵ı�ǩ���֡����ڴ�ӡ�������ʾ��ָ���еĽ������
	 */
	public String getColumnLabel() {
		return columnLabel;
	}

	/**
	 * @param columnLabel Ҫ���õ��б�ǩ����ע�͵ı�ǩ���֡����ڴ�ӡ�������ʾ��ָ���еĽ������
	 */
	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}
	
	
	/**
	 * @return �ж��Ƿ�Ϊ�Զ�������
	 */
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * @param autoIncrement ���ø����Ƿ�Ϊ�Զ�������
	 */
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	/**
	 * @return �жϸ����Ƿ�Ϊ��������
	 */
	public boolean isCurrency() {
		return currency;
	}

	/**
	 * @param currency ���ø����Ƿ�Ϊ��������
	 */
	public void setCurrency(boolean currency) {
		this.currency = currency;
	}

	/**
	 * @return �жϸ����Ƿ�Ϊֻ����
	 */
	public boolean isReadonly() {
		return readonly;
	}

	/**
	 * @param readonly ���ø����Ƿ�Ϊֻ����
	 */
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	/**
	 * @return �жϸ����ܷ���Ϊ�����У�������where������
	 */
	public boolean isSearchable() {
		return searchable;
	}

	/**
	 * @param searchable ���ø����ܷ���Ϊ�����У�������where������
	 */
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
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
		if (!(obj instanceof ColumnModel)) {
			return false;
		}
		ColumnModel other = (ColumnModel) obj;
		if (columnName == null) {
			if (other.columnName != null) {
				return false;
			}
		} else if (!columnName.equalsIgnoreCase(other.columnName)) {
			return false;
		}
		return true;
	}

	/**
	 * @return ȡ�õ�ǰ��ģ�������ı�ģ�Ͷ���
	 */
	public TableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @param tableModel ���õ�ǰ��ģ�������ı�ģ�Ͷ���
	 */
	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * ʵ�ָýӿ��Է�ֹ�����л����¡ʱ������ѭ����������
	 */
	public Object onCycleDetected(Context arg0) {
		ColumnModel temp = new ColumnModel();
        temp.setColumnName(columnName);

        return temp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ColumnModel [autoIncrement=");
		builder.append(autoIncrement);
		builder.append(", colComment=");
		builder.append(colComment);
		builder.append(", colRemark=");
		builder.append(colRemark);
		builder.append(", columnClassName=");
		builder.append(columnClassName);
		builder.append(", columnClassPackage=");
		builder.append(columnClassPackage);
		builder.append(", columnDisplaySize=");
		builder.append(columnDisplaySize);
		builder.append(", columnLabel=");
		builder.append(columnLabel);
		builder.append(", columnName=");
		builder.append(columnName);
		builder.append(", columnSimpleClassName=");
		builder.append(columnSimpleClassName);
		builder.append(", columnType=");
		builder.append(columnType);
		builder.append(", columnTypeName=");
		builder.append(columnTypeName);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", importedKey=");
		builder.append(importedKey);
		builder.append(", exportedKey=");
		builder.append(exportedKey);
		builder.append(", nullable=");
		builder.append(nullable);
		builder.append(", precision=");
		builder.append(precision);
		builder.append(", primaryKey=");
		builder.append(primaryKey);
		builder.append(", readonly=");
		builder.append(readonly);
		builder.append(", scale=");
		builder.append(scale);
		builder.append(", searchable=");
		builder.append(searchable);
		builder.append("]");
		return builder.toString();
	}

}
