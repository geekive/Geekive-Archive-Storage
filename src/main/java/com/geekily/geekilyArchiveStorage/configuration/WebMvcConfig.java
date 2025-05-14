package com.geekily.geekilyArchiveStorage.configuration;

import org.springframework.stereotype.Component;



import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.geekily.geekilyArchiveStorage.common.PropertyUtil;

@Component
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/depot/**")
			.addResourceLocations("file:///" + PropertyUtil.getProperty("upload.path"));
	}	
}
