package com.xhh.codegen.service;

import java.util.Map;

import com.xhh.codegen.model.OutputModel;

/**
 * һ���򵥵Ĺ����ӿ�
 * @author ������
 *
 */
public interface  Builder {	
	/**
	 * ������������������Map&lt;�����ʶ,���ģ��&gt;
	 * @return �������ģ�͵�ӳ�䡣
	 */
	Map<String, OutputModel> build();
}
