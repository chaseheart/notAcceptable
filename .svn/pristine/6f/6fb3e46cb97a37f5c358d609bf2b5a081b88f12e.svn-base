package com.isolver.common.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 从springContext中获取所有ControllerMapping 并过滤出含有/weixin的url，这些url的请求会经过TransferFilter
 */
@Component
public class FilterUrlMapping {

	@Autowired
	ApplicationContext applicationContext;

	// @Bean
	public Set<String> allUrlMappings() {
		Set<String> result = new HashSet<String>();
		RequestMappingHandlerMapping rmhp = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();

		for (RequestMappingInfo info : map.keySet()) {
			// getMatchingPatterns优化
			result.add(info.getPatternsCondition().toString().replace("[", "").replace("]", ""));
		}
		return result;
	}

	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new XSSFilter());// 添加过滤器
		Set<String> allSaveUrlPattern = allUrlMappings();// emsBasePrivilegeDao.findAllSaveUrlPattern();

		if (CollectionUtils.isEmpty(allSaveUrlPattern)) {
			return filterRegistration;
		}

//		String pattern = "/weixin/.*";// save
//		Set<String> urlPatterns = new LinkedHashSet<String>();
//
//		for (String saveUrlPattern : allSaveUrlPattern) {
//			if (Pattern.matches(pattern, saveUrlPattern)) {
//				urlPatterns.add(saveUrlPattern);
//			}
//		}
		filterRegistration.setUrlPatterns(allSaveUrlPattern);

		filterRegistration.setName("XSSFilter");//
		return filterRegistration;
	}

}
