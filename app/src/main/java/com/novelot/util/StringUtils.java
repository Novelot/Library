package com.novelot.util;

/**
 * 字符串工具
 * 
 * @author 刘云龙
 *
 */
public class StringUtils {

	/**
	 * 剔除字符串末尾的字符(2个)
	 * 
	 * @param str
	 * @param suffix1
	 * @param suffix2
	 * @return
	 */
	public static String trim(String str, String suffix1, String suffix2) {
		while (str.endsWith(suffix1) || str.endsWith(suffix2)) {
			str = str.substring(0, str.length() - 1);
		}

		return str;
	}
}
