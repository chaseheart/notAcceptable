/**
 * 
 */
package com.isolver.common.util;

/**
 *字符串转换
 * @author 陈昶宇
 * @createDate 2018/12/17 10:41:10 
 * 
 */
public class StrUtil {
	
	/**
	 * 添加百分号
	 * @param str 需要加百分号的字符串
	 * @return 处理完的字符串
	 */
	public static String strWithVague(String str) {
		str = str.replace(",", "\\,");
		str = str.replace("'", "\\'");
		str = str.replace(".", "\\.");
		str = str.replace("%", "\\%");
		return "%" + str + "%";
	}

}
