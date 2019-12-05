package com.isolver.common.config;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
/**
 * json过滤
 * @author IS1907006
 *
 */
public class XSSMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

	private ObjectMapper mapper = new ObjectMapper();

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter htmlEscapingConverter = new XSSMappingJackson2HttpMessageConverter();
		return htmlEscapingConverter;
	}

	public XSSMappingJackson2HttpMessageConverter() {
		super();
		mapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
	}

	/**
	 * 读取RequestBody数据并转义处理
	 */
	@Override
	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		JavaType javaType = getJavaType(type, contextClass);

		// 下面的程式碼 將 @RequestBody 中的資料 做 XSS過濾
		try {
			// json字串轉為物件
			Object object = mapper.readValue(inputMessage.getBody(), javaType);

			// 物件轉字串
			String jsonString = mapper.writeValueAsString(object);

			// json字串轉物件
			object = mapper.readValue(jsonString, javaType);
			return object;
		} catch (IOException ex) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
		}

	}

}