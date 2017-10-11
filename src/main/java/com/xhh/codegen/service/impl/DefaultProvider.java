package com.xhh.codegen.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.xhh.codegen.model.JdbcConfig;
import com.xhh.codegen.model.TableMetaData;
import com.xhh.codegen.service.DbProvider;
import com.xhh.codegen.utils.JdbcUtil;

/**
 * һ��Ĭ�ϵ����ݿ���Ϣ�ṩ�ߣ�������Ҫʹ��JDBC�ķ�ʽ�����Ի����ע�ͺͱ�ע�ͣ�
 * ��������Щ��JDBC������û���ṩ��ע�ͺͱ�ע�͵�DBMS������Ҫ������չDbProvider��ʵ���Զ����ȡ��ע�ͺͱ�ע��
 * @author ������
 *
 */
public class DefaultProvider extends DbProvider {
	private static final long serialVersionUID = 512932698031580465L;

	public DefaultProvider(Connection conn) {
		super(conn);
	}
	
	public DefaultProvider(JdbcConfig jdbcConfig) {
		super(jdbcConfig);
	}

	/**
	 * ��JDBC��ͨ��DatabaseMetaData.getColumns������ȡָ����������ע�͡�
	 * ���صļ��ϵ�Ԫ�ظ�ʽΪ��Map&lt;������,��ע��&gt;��
	 */
	@Override
	protected Map<String, String> doGetColumnComments(String tableName) {
		ResultSet rs=null;
		Map<String, String> colCommentMap = new LinkedHashMap<String, String>();
		try {
			rs = getConn().getMetaData().getColumns(this.getCatalog(), this.getSchema(), tableName.toUpperCase(),null);
			while(rs.next()){
				colCommentMap.put(rs.getString("COLUMN_NAME"), rs.getString("REMARKS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.safelyClose(rs);
		}
		return colCommentMap;
	}

	/**
	 * ��JDBC��ͨ��DatabaseMetaData.getTables������ȡָ����������ע�͡�
	 * ���صļ��ϵ�Ԫ�ظ�ʽΪ��Map&lt;������,��ע��&gt;��
	 */
	@Override
	protected Map<String, String> doGetTableComments() {
		Map<String, String> tableComments = new LinkedHashMap<String, String>();
		Map<String, TableMetaData> tmdMap = getTableMetaData();
		for (Entry<String,TableMetaData> entry : tmdMap.entrySet()) {
			tableComments.put(entry.getKey(), entry.getValue().getRemarks());
		}
		
		return tableComments;
	}

}
