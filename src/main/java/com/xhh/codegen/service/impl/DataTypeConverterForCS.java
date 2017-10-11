package com.xhh.codegen.service.impl;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xhh.codegen.model.ColumnModel;
import com.xhh.codegen.service.ColumnHandler;

/**
 * ���C#��̻�������������ת����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class DataTypeConverterForCS implements ColumnHandler,Serializable {

	public void handle(ColumnModel columnModel) {
		String javaType = columnModel.getColumnClassName();
		String typeName = columnModel.getColumnTypeName();
		String className = typeName;
		String simpleClassName = typeName;
		
		//����javaTypeȷ��C#��������е���������
		if("java.math.BigDecimal".equals(javaType) ){
			if(columnModel.getScale()>0){
				className = "System.Decimal";
				simpleClassName = "decimal";
			}else{
				className = "System.Int64";
				simpleClassName = "long";
			}
        }else if("java.lang.Boolean".equals(javaType)){
        	className = "System.Boolean";
			simpleClassName = "bool";
        }else if("java.lang.Integer".equals(javaType)){
        	className = "System.Int32";
			simpleClassName = "int";
        }else if("java.lang.Long".equals(javaType)){
        	className = "System.Int64";
			simpleClassName = "long";
        }else if("java.lang.Float".equals(javaType)){
        	className = "System.Single";
			simpleClassName = "float";
        }else if("java.lang.Double".equals(javaType)){
        	className = "System.Decimal";
			simpleClassName = "decimal";
        }else if("java.sql.Date".equals(javaType)
        		||"java.sql.Time".equals(javaType)
        		||"java.sql.Timestamp".equals(javaType)){
        	className = "System.DateTime";
			simpleClassName = "DateTime";
        }else{
        	className = "System.String";
			simpleClassName = "string";
        }
		
		//���ݾ������ݿⷽ���е���������(sqlTypeName)ȷ��C#��������е���������
		if("int".equalsIgnoreCase(typeName)){
			className = "System.Int32";
			simpleClassName = "int";
		}else if("bit".equalsIgnoreCase(typeName)){
			className = "System.Boolean";
			simpleClassName = "bool";
		}else if("decimal".equalsIgnoreCase(typeName)){
			className = "System.Decimal";
			simpleClassName = "decimal";
		}else if("float".equalsIgnoreCase(typeName)){
			className = "System.Single";
			simpleClassName = "float";
		}else if("smallint".equalsIgnoreCase(typeName)){
			className = "System.Short";
			simpleClassName = "short";
		}
		
		//��floatͳһΪdecimal
		if("float".equals(simpleClassName)){
			className = "System.Decimal";
			simpleClassName = "decimal";
		}
		//�þ���ı�������������͸���Ĭ�ϵ�java���͡�
		columnModel.setColumnClassName(className);
		//���þ��������Ե������������ڵİ�����C#��Ҳ�������ռ䡣��System.String�������ռ�ΪSystem
		columnModel.setColumnClassPackage(StringUtils.substringBeforeLast(className, "."));
		//���þ��������Ե��������͵ļ�����
		columnModel.setColumnSimpleClassName(simpleClassName);
	}

}
