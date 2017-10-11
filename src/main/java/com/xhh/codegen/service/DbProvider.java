package com.xhh.codegen.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xhh.codegen.model.ColumnModel;
import com.xhh.codegen.model.ForeignKeyModel;
import com.xhh.codegen.model.JdbcConfig;
import com.xhh.codegen.model.PrimaryKeyModel;
import com.xhh.codegen.model.TableMetaData;
import com.xhh.codegen.model.TableModel;
import com.xhh.codegen.service.impl.DataTypeConverterForJava;
import com.xhh.codegen.utils.IgnoreCaseMap;
import com.xhh.codegen.utils.JdbcUtil;

/**
 * ���ݿ���Ϣ�ṩ�ߡ��ó��������JDBCʵ���������ݿⷽ���޹صķ������������ݿⷽ����صĲ������ɸ�����ʵ�֡�
 * @author ������ 
 */
public abstract class DbProvider implements Serializable{
	private static final long serialVersionUID = -7312979095006311626L;
	private Connection conn;
	private JdbcConfig jdbcConfig;
	private String catalog = null;
	private String schema = null;
	private String tableNamePatterns = null;
	private List<ColumnHandler> columnHandlers = new ArrayList<ColumnHandler>();
	private Map<String,Map<String,String>> columnComments;
	private Map<String,String> tableComments;
	private List<String> tableNames;
	private Map<String, TableMetaData> tableMetaDatas;
	/**
	 * �ӱ��У�ע������ȡ���У���ǩ���õķָ�����Ĭ��ֵΪһ���ո��
	 */
	private String splitorForLabelFromComment = " ";

	/**
	 * �������ݿ����ӹ���һ�����ݿ���Ϣ�ṩ��
	 * @param conn ���ݿ�����
	 */
	public DbProvider(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * ����jdbc����ģ�͹���һ�����ݿ���Ϣ�ṩ��
	 * @param jdbcConfig jdbc����ģ��
	 */
	public DbProvider(JdbcConfig jdbcConfig) {
		super();
		this.jdbcConfig = jdbcConfig;
	}
	
	/**
	 * @return ��ȡһ�����ݿ�����
	 */
	protected Connection getConn() {
		if(conn==null){
			if(jdbcConfig==null){
				try {
					throw new Exception(this.getClass().getName()+"jdbcConfig��conn����ͬʱΪnull");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			conn = JdbcUtil.getConn(jdbcConfig);
		}
		return conn;
	}

	/**
	 * @return ��ȡ��ǰ�����ݿ����(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public String getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog ���õ�ǰ�����ݿ����(һ��Ϊ���ݿ���������Ϊ��)
	 */
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	/**
	 * @return ��ȡ��ǰ�����ݿ�����(һ��Ϊ�û���������Ϊ��)
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema ���õ�ǰ�����ݿ�����(һ��Ϊ�û���������Ϊ��)
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	/**
	 * @return ��ȡ����ģʽ��֧�ֶ������ģʽ����Ӣ�Ķ��ŷָ�
	 */
	public String getTableNamePatterns() {
		return tableNamePatterns;
	}

	/**
	 * @param tableNamePatterns ���ñ���ģʽ
	 */
	public void setTableNamePatterns(String tableNamePatterns) {
		this.tableNamePatterns = tableNamePatterns;
	}

	/**
	 * @return ȡ��һ����ģ�ʹ�����
	 */
	public List<ColumnHandler> getColumnHandlers() {
		return columnHandlers;
	}

	/**
	 * @param columnHandlers ����һ����ģ�ʹ�����
	 */
	public void setColumnHandlers(List<ColumnHandler> columnHandlers) {
		this.columnHandlers = columnHandlers;
	}
	
	/**
	 * ��ȡ��catalog��schema��Χ�ڵ����б����ơ�
	 * @return
	 */
	public List<String> getTableNames(){
		if(tableNames!=null){
			return tableNames;
		}
		tableNames = new ArrayList<String>();
		ResultSet rs = null;
		try{
			String tnps = getTableNamePatterns();
			if(StringUtils.isBlank(tnps)){
				rs = getConn().getMetaData().getTables(catalog, schema, null, null);
				while(rs.next()){
					tableNames.add(rs.getString("TABLE_NAME"));
				}
			}else{
				for (String tnp : tnps.split(",")) {
					rs = getConn().getMetaData().getTables(catalog, schema, tnp, null);
					while(rs.next()){
						tableNames.add(rs.getString("TABLE_NAME"));
					}					
				}
			}			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtil.safelyClose(rs);
		}
		return tableNames;
	}
	/**
	 * ȡ�ñ��Ԫ����
	 * @return
	 */
	public Map<String, TableMetaData> getTableMetaData(){
		if(tableMetaDatas!=null&&tableMetaDatas.size()>0){
			return tableMetaDatas;
		}
		tableMetaDatas = new IgnoreCaseMap<String, TableMetaData>();

		String tnps = getTableNamePatterns();
		if(StringUtils.isBlank(tnps)){
			tableMetaDatas.putAll(getTableMetaData(null));
		}else{
			for (String tnp : tnps.split(",")) {
				tableMetaDatas.putAll(getTableMetaData(tnp));
			}
		}
		
		return tableMetaDatas;
	}

	/**
	 * ȡ�ñ��Ԫ����
	 * @param tableNamePattern ������ƥ���ַ���
	 * @return
	 */
	private Map<String, TableMetaData> getTableMetaData(String tableNamePattern){
		ResultSet rs = null;
		try{
			rs = getConn().getMetaData().getTables(catalog, schema, tableNamePattern, null);
			List<String> columnNameList = new ArrayList<String>();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnNameList.add(rsmd.getColumnName(i));
			}
			
			TableMetaData tmd;
			while(rs.next()){
				tmd = new TableMetaData();
				if(columnNameList.contains("TABLE_CAT")){
					tmd.setTableCat(rs.getString("TABLE_CAT"));
				}
				if(columnNameList.contains("TABLE_SCHEM")){
					tmd.setTableSchem(rs.getString("TABLE_SCHEM"));
				}
				if(columnNameList.contains("TABLE_NAME")){
					tmd.setTableName(rs.getString("TABLE_NAME"));
				}
				if(columnNameList.contains("TABLE_TYPE")){
					tmd.setTableType(rs.getString("TABLE_TYPE"));
				}
				if(columnNameList.contains("REMARKS")){
					tmd.setRemarks(rs.getString("REMARKS"));
				}
				if(columnNameList.contains("TYPE_CAT")){
					tmd.setTypeCat(rs.getString("TYPE_CAT"));
				}
				if(columnNameList.contains("TYPE_SCHEM")){
					tmd.setTypeSchem(rs.getString("TYPE_SCHEM"));
				}
				if(columnNameList.contains("TYPE_NAME")){
					tmd.setTypeName(rs.getString("TYPE_NAME"));
				}
				if(columnNameList.contains("SELF_REFERENCING_COL_NAME")){
					tmd.setSelfReferencingColName(rs.getString("SELF_REFERENCING_COL_NAME"));
				}
				if(columnNameList.contains("REF_GENERATION")){
					tmd.setRefGeneration(rs.getString("REF_GENERATION"));
				}
				
				tableMetaDatas.put(tmd.getTableName(),tmd);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtil.safelyClose(rs);
		}
		return tableMetaDatas;
	}
	
	/**
	 * ������Ԫ���ݻ���
	 */
	public void clearTableMetaDataCache(){
		if(tableMetaDatas!=null){
			tableMetaDatas.clear();
		}
	}
	
	/**
	 * ȡ�����б�ı�ע�ͣ���ͬ�����ݿⷽ���в�ͬ��ʵ�ַ�ʽ��
	 * @return
	 */
	protected abstract Map<String,String> doGetTableComments();
	
	/**
	 * һ���Ի�ȡ���б�ı�ע�ͣ���ͬ�����ݿⷽ���в�ͬ��ʵ�ַ�ʽ��
	 * @return
	 */
	public Map<String,String> getTableComments(){
		if(tableComments==null){
			tableComments = new IgnoreCaseMap<String, String>(doGetTableComments());
		}
		return tableComments;
	}
	
	/**
	 * ��ȡָ��������е�������
	 * @return
	 */
	public List<String> getColumnNames(String tableName){
		List<String> colList = new ArrayList<String>();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = getConn().createStatement();
			String sql = "select * from " + tableName;
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++){
				colList.add(rsmd.getColumnName(i));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtil.safelyClose(rs, stmt);
		}
		return colList;
	}
	
	/**
	 * ��ȡָ����������ע�͡����صļ��ϵ�Ԫ�ظ�ʽΪ������(COLUMN_NAME)��ע��(COMMENTS)��
	 * @return
	 */
	protected abstract Map<String, String> doGetColumnComments(String tableName);
	
	/**
	 * һ���Ի�ȡָ�����������ע�͡����صļ��ϵ�Ԫ�ظ�ʽΪ������(COLUMN_NAME)��ע��(COMMENTS)��
	 * @return 
	 */
	protected Map<String, String> getColumnComments(String tableName){
		if(columnComments==null){
			columnComments = new IgnoreCaseMap<String, Map<String,String>>();			
		}
		Map<String, String> resultMap =columnComments.get(tableName);
		if(resultMap==null){
			resultMap = new IgnoreCaseMap<String, String>(doGetColumnComments(tableName));
			columnComments.put(tableName,resultMap);
		}
		return resultMap;
	}
	
	/**
	 * ��ȡָ���������ģ�ͼ���
	 * @param tableName ������
	 * @return
	 */
	protected List<PrimaryKeyModel> getPrimaryKeys(String tableName){
		ResultSet rs=null;
		List<PrimaryKeyModel> pkModelList = new ArrayList<PrimaryKeyModel>();
		PrimaryKeyModel pkModel ;
		try {
			rs = getConn().getMetaData().getPrimaryKeys(this.catalog, this.schema , tableName);
			while(rs.next()){
				pkModel = new PrimaryKeyModel();
				pkModel.setTableCat(rs.getString("TABLE_CAT"));
				pkModel.setTableSchem(rs.getString("TABLE_SCHEM"));
				pkModel.setTableName(rs.getString("TABLE_NAME"));
				pkModel.setColumnName(rs.getString("COLUMN_NAME"));
				pkModel.setKeySeq(rs.getShort("KEY_SEQ"));
				pkModel.setPkName(rs.getString("PK_NAME"));
				
				pkModelList.add(pkModel); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.safelyClose(rs);
		}
		return pkModelList;
	}
	/**
	 * ��ȡָ�������������룩ģ�ͼ���
	 * @param tableName ������
	 * @return
	 */
	protected List<ForeignKeyModel> getImportedKeys(String tableName){
		ResultSet rs=null;
		List<ForeignKeyModel> fkModelList = new ArrayList<ForeignKeyModel>();
		ForeignKeyModel fkModel ;
		try {
			rs = getConn().getMetaData().getImportedKeys(catalog, schema, tableName);
			while(rs.next()){
				fkModel = new ForeignKeyModel();
				fkModel.setPkTableCat(rs.getString("PKTABLE_CAT"));
				fkModel.setPkTableSchem(rs.getString("PKTABLE_SCHEM"));
				fkModel.setPkTableName(rs.getString("PKTABLE_NAME"));
				fkModel.setPkColumnName(rs.getString("PKCOLUMN_NAME"));
				fkModel.setFkTableCat(rs.getString("FKTABLE_CAT"));
				fkModel.setFkTableSchem(rs.getString("FKTABLE_SCHEM"));
				fkModel.setFkTableName(rs.getString("FKTABLE_NAME"));
				fkModel.setFkColumnName(rs.getString("FKCOLUMN_NAME"));
				fkModel.setKeySeq(rs.getShort("KEY_SEQ"));
				fkModel.setUpdateRule(rs.getShort("UPDATE_RULE"));
				fkModel.setDeleteRule(rs.getShort("DELETE_RULE"));
				fkModel.setPkName(rs.getString("PK_NAME"));
				fkModel.setFkName(rs.getString("FK_NAME"));
				fkModel.setDeferrability(rs.getShort("DEFERRABILITY"));
				fkModelList.add(fkModel); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.safelyClose(rs);
		}
		return fkModelList;
	}
	
	/**
	 * ��ȡָ����������������ģ�ͼ���
	 * @param tableName ������
	 * @return
	 */
	protected List<ForeignKeyModel> getExportedKeys(String tableName){
		ResultSet rs=null;
		List<ForeignKeyModel> fkModelList = new ArrayList<ForeignKeyModel>();
		ForeignKeyModel fkModel ;
		try {
			rs = getConn().getMetaData().getExportedKeys(catalog, schema, tableName);
			while(rs.next()){
				fkModel = new ForeignKeyModel();
				fkModel.setPkTableCat(rs.getString("PKTABLE_CAT"));
				fkModel.setPkTableSchem(rs.getString("PKTABLE_SCHEM"));
				fkModel.setPkTableName(rs.getString("PKTABLE_NAME"));
				fkModel.setPkColumnName(rs.getString("PKCOLUMN_NAME"));
				fkModel.setFkTableCat(rs.getString("FKTABLE_CAT"));
				fkModel.setFkTableSchem(rs.getString("FKTABLE_SCHEM"));
				fkModel.setFkTableName(rs.getString("FKTABLE_NAME"));
				fkModel.setFkColumnName(rs.getString("FKCOLUMN_NAME"));
				fkModel.setKeySeq(rs.getShort("KEY_SEQ"));
				fkModel.setUpdateRule(rs.getShort("UPDATE_RULE"));
				fkModel.setDeleteRule(rs.getShort("DELETE_RULE"));
				fkModel.setPkName(rs.getString("PK_NAME"));
				fkModel.setFkName(rs.getString("FK_NAME"));
				fkModel.setDeferrability(rs.getShort("DEFERRABILITY"));
				fkModelList.add(fkModel); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.safelyClose(rs);
		}
		return fkModelList;
	}
	
	/**
	 * ���ݱ�����ȡ��ע�ͣ����δȡ��ע�ͣ��򷵻ؿ��ַ���
	 * @param tableName ������
	 * @return
	 */
	public String getTableComment(String tableName){
		return getTableComment(tableName,"");
	}
	/**
	 * ���ݱ�����ȡ��ע�ͣ����δȡ��ע�ͣ��򷵻�ָ����Ĭ��ֵ
	 * @param tableName ������
	 * @param defaultValue ָ����Ĭ�ϱ�ע��
	 * @return
	 */
	public String getTableComment(String tableName, String defaultValue){
		String comment = getTableComments().get(tableName);
		return StringUtils.defaultString(comment, defaultValue);
	}
	
	/**
	 * @return �ӱ��У�ע������ȡ���У���ǩ���õķָ�����Ĭ��ֵΪһ���ո��
	 */
	public String getSplitorForLabelFromComment() {
		return splitorForLabelFromComment;
	}

	/**
	 * @param splitorForLabelFromComment ���ôӱ��У�ע������ȡ���У���ǩ���õķָ�����Ĭ��ֵΪһ���ո��
	 */
	public void setSplitorForLabelFromComment(String splitorForLabelFromComment) {
		this.splitorForLabelFromComment = splitorForLabelFromComment;
	}

	/**
	 * �ӱ�ע���� ��ȡ��ı�ǩ�������δ�ܳɹ���ȡ���򷵻�ָ���ı�ע�͡�
	 * <br>��ע��Լ������ı�ǩ�� + �ָ���  + ��ı�ע��Ϣ��
	 * <br>��splitorForLabelFromComment����Ϊ��ֵ���򷵻�������ע��
	 * @param tabComment ָ���ı�ע��
	 * @return
	 */
	public String getTableLabelFromComment(String tabComment){
		String label = tabComment;
		if(StringUtils.isNotBlank(splitorForLabelFromComment)){
			label = StringUtils.substringBefore(tabComment, splitorForLabelFromComment);//����ָ���ַ������ո�֮ǰ�������ַ�
		}	
		return StringUtils.defaultString(label);
	}
	/**
	 * ����ָ����ָ���е�ע�ͣ����δȡ��ע�ͣ��򷵻�ָ���������ơ�
	 * �ò��������ע��������л��з��źͻس�����ͳһ�滻�ɿո����
	 * @param tableName ����
	 * @param columnName ����
	 * @return
	 */
	public String getColumnComment(String tableName, String columnName){
		String comment = columnName;
		Map<String,String> ccmap = getColumnComments(tableName);
		if(ccmap!=null){
			comment = StringUtils.defaultString(ccmap.get(columnName),columnName)
						.replace("\n", " ").replace("\r", " "); //�ѻ��з��ͻس���ͳһ�滻�ɿո�
		}
		return comment;
	}
	/**
	 * ����ע���л�ȡ�б�ǩ���е����ı����������δ�ܻ�ȡ�ɹ����򷵻�ָ������ע�͡�
	 * <br>��ע��Լ�����б�ǩ  + �ָ���  + �б�ע��Ϣ
	 * <br>��splitorForLabelFromComment����Ϊ��ֵ���򷵻�������ע��
	 * @param columnComment ��ע��
	 * @return
	 */
	public String getColumnLabelFromComment(String columnComment){
		if(StringUtils.isNotBlank(splitorForLabelFromComment)){
			return StringUtils.substringBefore(columnComment, splitorForLabelFromComment);//����ָ���ַ���֮ǰ�������ַ�
		}else{
			return StringUtils.defaultString(columnComment);
		}
	}
	/**
	 * ����ע���л�ȡ�б�ע��Ϣ�����δ�ܻ�ȡ�ɹ����򷵻ؿ��ַ�����
	 * <br>��ע��Լ�����б�ǩ  + �ָ���  + �еı�ע��Ϣ
	 * <br>��splitorForLabelFromComment����Ϊ��ֵ���򷵻�������ע��
	 * @param columnComment ��ע��
	 * @return
	 */
	public String getColumnRemarkFromComment(String columnComment){
		if(StringUtils.isNotBlank(splitorForLabelFromComment)){
			return StringUtils.substringAfter(columnComment, splitorForLabelFromComment);//����ָ���ַ������ո�֮��������ַ�
		}else{
			return StringUtils.defaultString(columnComment);
		}
	}

	/**
	 * �ж��������Ƿ�Ϊ�����Է��ؾ��ȣ�Precision���������ͣ���oracle�Ĵ��ֶ�����Blob��Clob��
	 * @param columnType
	 * @return
	 */
	protected boolean isPrecisionUnknowType(int columnType){
		return columnType==java.sql.Types.CLOB||columnType==java.sql.Types.BLOB;
	}
	
	/**
	 * ����ָ���ı�������һ����ģ��
	 * @param tableName ������
	 * @return
	 * @throws SQLException
	 */
	public TableModel createTableModel(String tableName){
		TableModel table = new TableModel();
		
		//���ñ�����Ԫ����
		table.setTableName(tableName);
		table.setSchema(getSchema());
		table.setCatalog(getCatalog());		
		table.setPrimaryKeyList(getPrimaryKeys(table.getTableName()));
		table.setImportedKeyList(getImportedKeys(table.getTableName()));
		table.setExportedKeyList(getExportedKeys(table.getTableName()));
		table.setTabComment(getTableComment(table.getTableName(),table.getTableName()));
		table.setTableLabel(getTableLabelFromComment(table.getTabComment()));
		if(getTableMetaData().containsKey(tableName)){
			table.setTableType(getTableMetaData().get(tableName).getTableType());
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = getConn().createStatement();
			String sql = "select * from " + tableName;
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData(); 
			ColumnModel col = null;
			for(int i=1;i<=rsmd.getColumnCount();i++){
				col = new ColumnModel();
				table.getColumnList().add(col);
				
				col.setColumnName(rsmd.getColumnName(i));
				col.setFieldName(rsmd.getColumnName(i));
				col.setColComment(getColumnComment(tableName, col.getColumnName()));
				col.setColumnLabel(getColumnLabelFromComment(col.getColComment()));
				col.setColRemark(getColumnRemarkFromComment(col.getColComment()));
				//����sql��������
				col.setColumnType(rsmd.getColumnType(i));
				//����sql�������͵�����
				col.setColumnTypeName(rsmd.getColumnTypeName(i));
				//�����е�sql����������java��������ж�Ӧ�ľ�����������
				col.setColumnClassName(rsmd.getColumnClassName(i));
								
				//ȡ������ʾ�������
				col.setColumnDisplaySize(rsmd.getColumnDisplaySize(i));
				//�����е����ݱ��
				col.setScale(rsmd.getScale(i));
				//�жϸ����Ƿ�Ϊ������
				col.setAutoIncrement(rsmd.isAutoIncrement(i));
				//�ж��Ƿ�Ϊ���������ֶ�
				col.setCurrency(rsmd.isCurrency(i));
				//�ж��ֶ��Ƿ�Ϊֻ��״̬
				col.setReadonly(rsmd.isReadOnly(i));
				//�жϸ��ֶ��Ƿ������Ϊ������������������where�����
				col.setSearchable(rsmd.isSearchable(i));
				//�ж��������Ƿ�Ϊδ֪���ͣ���oracle�Ĵ��ֶ�����Blob��Clob��
				if(!isPrecisionUnknowType(rsmd.getColumnType(i))){
					//�������δ֪���ͣ��������е����ݾ���
				    col.setPrecision(rsmd.getPrecision(i));
				}
				//�жϸ�������շ�
				if(rsmd.isNullable(i)==ResultSetMetaData.columnNoNulls){
					col.setNullable(false);
				}else{
					col.setNullable(true);
				}
				//�ж��Ƿ�Ϊ������
				if(table.isPrimaryKey(col.getColumnName())){
					col.setPrimaryKey(true);
				}
				//�ж��Ƿ�Ϊ����У�����������ļ���
				if(table.isImportedKey(col.getColumnName())){
					col.setImportedKey(true);
				}
				//�ж��Ƿ�Ϊ����У�����������գ�
				if(table.isExportedKey(col.getColumnName())){
					col.setExportedKey(true);
				}
				
				//�������������ı�ģ�ͣ���������ģ�ʹ������ﻹ���Ը��ݱ�ģ�͵���������������߼��ж�
				col.setTableModel(table);
				
				//���û�������κε���ģ�ʹ���������Ĭ��ʹ��һ��java��������ת������������ģ��
				if(columnHandlers==null||columnHandlers.size()==0){					
					new DataTypeConverterForJava().handle(col);
				}else{
					for (ColumnHandler columnHandler : columnHandlers) {
						columnHandler.handle(col);
					}
				}
			}
			System.out.println("�ɹ�������ģ��"+table);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtil.safelyClose(rs, stmt);
		}
		return table;
	}
}
