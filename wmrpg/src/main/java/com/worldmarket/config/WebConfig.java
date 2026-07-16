package com.worldmarket.config;

import java.nio.file.Path;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path uploadDirectory = Path.of("uploads", "profile-pictures").toAbsolutePath();
		registry.addResourceHandler("/uploads/profile-pictures/**")
			.addResourceLocations(uploadDirectory.toUri().toString());
	}
}
