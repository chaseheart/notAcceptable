package com.isolver.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
/**
 * mvc配置
 * @author IS1907006
 *
 */
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	// 配置开放的url
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/unusualPerformence").setViewName("unusualPerformence");
		registry.addViewController("/error").setViewName("error");
		registry.addViewController("/noRole").setViewName("noRole");
	}
}