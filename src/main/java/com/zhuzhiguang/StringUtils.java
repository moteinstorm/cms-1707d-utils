package com.zhuzhiguang;

/**
 * �ַ���������
 * @author zhuzg
 *
 */
public class StringUtils {
	
	/**
	 * �ж�һ���ַ����Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		
		return (str == null|| str.trim().equals(""));
	}
	
	

}
