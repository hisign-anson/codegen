package com.xhh.codegen.model;

import java.io.Serializable;

/**
 * JDBC����ģ��
 * @author ������
 *
 */
public class JdbcConfig implements Serializable{
	private static final long serialVersionUID = -4079723733219143609L;
	private String driver;
	private String url;
	private String user;
	private String password;
	
	/**
	 * @return ȡ��JDBC������
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * @param driver ����JDBC������
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	/**
	 * @return ȡ��JDBC�����ַ���
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url ����JDBC�����ַ���
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return ȡ��JDBC���ӵ��û���������ǰ���ݿ����ӵ�����
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user ����JDBC���ӵ��û���������ǰ���ݿ����ӵ�����
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return ȡ��JDBC���ӵ�����
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password ����JDBC���ӵ�����
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
