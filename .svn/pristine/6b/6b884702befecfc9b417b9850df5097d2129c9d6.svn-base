package com.isolver.common.config;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

/**
 * request的包装类
 * @author IS1907006
 *
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

	private Charset charSet;
	
	public XSSRequestWrapper(HttpServletRequest servletRequest) throws IOException {
		super(servletRequest);
	}

	/**
	 * getParameterValues包装
	 */
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	/**
	 * getParameter包装
	 */
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	/**
	 * getHeader包装
	 */
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}

	/**
	 * XSS转义
	 * @param value
	 * @return
	 */
	private String cleanXSS(String value) {
		// 。这里特意 加多个空格.... 方便 csdn显示 ,如 < ==> & lt;
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;");
		value = value.replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("\\\"", "&#34;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}
	
	
	/**
	 * RequestBody用
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public String getRequestPostStr(HttpServletRequest request) throws IOException {
		String charSetStr = request.getCharacterEncoding();
		if (charSetStr == null) {
			charSetStr = "UTF-8";
		}
		charSet = Charset.forName(charSetStr);

		return StreamUtils.copyToString(request.getInputStream(), charSet);
	}
}
