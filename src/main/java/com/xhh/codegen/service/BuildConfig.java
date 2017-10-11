package com.xhh.codegen.service;

import java.util.Map;

import com.xhh.codegen.model.OutputModel;

/**
 * ���������á��ӿ�
 * @author ������
 *
 */
public interface BuildConfig {
	/**
	 * ��ȡ�����������
	 * @return
	 */
	String getOutputEncoding();
	/**
	 * ��ȡ����ģ��
	 * @return
	 */
	Map<String,Object> getDataModel();
	
	/**
	 * ��ȡ���ģ��
	 * @return
	 */
	Map<String,OutputModel> getOutputModel();
	
}
