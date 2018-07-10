package com.nurosoft.generator.config;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import com.nurosoft.generator.constants.FilePathConstants;

@Configuration
public class MustacheConfiguration {

	private static final Logger logger = LogManager.getLogger(MustacheConfiguration.class);
		@Bean
		public MustacheFactory configureMustacheFactory(ApplicationContext context) throws IOException{
			Resource resource = context.getResource(FilePathConstants.DIR_TEMPLATES_MUSTACHE);
			logger.info("Templates Directory path {}"+ resource.getFile().getAbsolutePath());
			return new DefaultMustacheFactory(resource.getFile());
		}
	
}
