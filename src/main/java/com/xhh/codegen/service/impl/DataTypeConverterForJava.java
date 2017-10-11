package com.xhh.codegen.service.impl;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xhh.codegen.model.ColumnModel;
import com.xhh.codegen.service.ColumnHandler;

/**
 * ���java��̻�������������ת����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class DataTypeConverterForJava implements ColumnHandler,Serializable {

	public void handle(ColumnModel columnModel) {
		String javaType = columnModel.getColumnClassName();
		if("java.math.BigDecimal".equals(javaType) ){
			if(columnModel.getScale()>0){
				javaType = "java.lang.Double";
			}else{
				if(columnModel.getPrecision()>=1 && columnModel.getPrecision()<=9){
					javaType="java.lang.Integer";
				}else{
					javaType="java.lang.Long";
				}
			}
        }else if("java.sql.Timestamp".equals(javaType)){
        	javaType="java.util.Date";
        }
//        else{
//        	type="java.lang.String";
//        }
		
		//���ݾ������ݿⷽ���е���������ȷ��java��������е���������
		String typeName = columnModel.getColumnTypeName();
		if("decimal".equalsIgnoreCase(typeName)
			||"money".equalsIgnoreCase(typeName)
			||"numeric".equalsIgnoreCase(typeName)
			||"float".equalsIgnoreCase(typeName)
			||"smallmoney".equalsIgnoreCase(typeName)){
			if(columnModel.getScale()>0){
				javaType = "java.lang.Double";
			}else{
				if(columnModel.getPrecision()>=1 && columnModel.getPrecision()<=9){
					javaType="java.lang.Integer";
				}else{
					javaType="java.lang.Long";
				}
			}
		}
				
		columnModel.setColumnClassName(javaType);
		columnModel.setColumnSimpleClassName(StringUtils.substringAfterLast(javaType, "."));//��ȫ�޶����н�ȡ������
		//���þ��������Ե������������ڵİ�����java.lang.String�������ռ�Ϊjava.lang
		columnModel.setColumnClassPackage(StringUtils.substringBeforeLast(javaType, "."));
	}

}
