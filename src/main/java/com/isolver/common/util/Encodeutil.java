package com.isolver.common.util;

import java.security.MessageDigest;

public class Encodeutil {
	
	/**
	 * md5加密
	 * @param data
	 * @return
	 */
	public static String MD5Util(String data) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		}catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		char[] chars = data.toCharArray();
		byte[] bytes = new byte[chars.length];
		for(int i=0;i<chars.length;i++) {
			bytes[i] = (byte)chars[i];
		}
		byte[] md5byte = md5.digest(bytes);
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0;i<md5byte.length;i++) {
			int  val = ((int) md5byte[i] & 0xff);
			if(val<16) {
				stringBuffer.append("0");
			}else {
				stringBuffer.append(Integer.toHexString(val));
			}
		}
		return stringBuffer.toString();
	}
}
