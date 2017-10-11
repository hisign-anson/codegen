package com.xhh.codegen.service;


public interface BuildConfigHandler {
	/**
	 * ��ʼ������ʱ����
	 * @param buildConfig
	 */
	void initConfig(BuildConfig buildConfig);
	/**
	 * ��ȡ����ģ��ǰ����
	 * @param buildConfig
	 */
	void beforeParseDataModel(BuildConfig buildConfig);
	
	/**
	 * ��ȡ����ģ�ͺ���
	 * @param buildConfig
	 */
	void afterParseDataModel(BuildConfig buildConfig);
	
	/**
	 * ��ȡ���ģ��ǰ����
	 * @param buildConfig
	 */
	void beforeParseOutputModel(BuildConfig buildConfig);
	
	/**
	 * ��ȡ���ģ�ͺ���
	 * @param buildConfig
	 */
	void afterParseOutputModel(BuildConfig buildConfig);
}
