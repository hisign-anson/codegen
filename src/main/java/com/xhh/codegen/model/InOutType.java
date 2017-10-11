package com.xhh.codegen.model;

/**
 * �����������
 * @author ������
 *
 */
public enum InOutType {
	/**
	 * file
	 */
	FILE("file","�ļ�"),
	/**
	 * text
	 */
	TEXT("text","�ı�");

	/**
	 * ��һ��ֵ�ͱ�ǩʵ����һ�������������ö��
	 * @param value
	 * @param label
	 */
	private InOutType(String value, String label) {
		this.value = value;
		this.label = label;
	}

	private final String value;
	private final String label;

	/**
	 * ȡ��ö�ٵ�ֵ
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * ȡ��ö�ٵı�ǩ
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * ����ö��ֵ��ȡ��Ӧ����ʾ��ǩ
	 * @param value
	 * @return
	 */
	public static String getLabelByValue(String value){
		InOutType[] types = InOutType.values();
		for (int i = 0; i < types.length; i++) {
			if(types[i].value.equals(value))
				return types[i].getLabel();
		}
		return "";
	}
}
