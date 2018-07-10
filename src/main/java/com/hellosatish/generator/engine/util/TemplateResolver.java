package com.hellosatish.generator.engine.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * @author satish-s
 * 
 *         <pre>
 *	 This class shall return the {@link Reader} instances for the template files.
 *	 Which shall be fed in to Mustache Compiler
 *         </pre>
 */
public final class TemplateResolver {

	private TemplateResolver(){}
	
	private static final String POM = "/templates/mustache/pom.mustache";
	private static final String CLASS = "/templates/mustache/class.mustache";
	private static final String DOCKERFILE = "/templates/mustache/Dockerfile.mustache";
	private static final String CONFIG_CLASSES = "/templates/mustache/config_classes.mustache";
	private static final String POJO_ENTITY = "/templates/mustache/pojo_entity.mustache";
	private static final String CONTROLLER = "/templates/mustache/controller.mustache";
	private static final String JPA_REPOSITORY = "/templates/mustache/jpa_repository.mustache";
	

	private static final String BOOTSTRAP_PROPERTIES = "/templates/mustache/bootstrap.mustache";

	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateResolver.class);

	public static final Reader getPOMTemplate() {
		try {
			return new FileReader(new ClassPathResource(POM).getFile());
		} catch (IOException e) {
			LOGGER.error("Error while accessing template : {}", e.getMessage());
			return null;
		}
	}

	public static final Reader getClassTemplate() {
		try {
			return new FileReader(new ClassPathResource(CLASS).getFile());
		} catch (IOException e) {
			LOGGER.error("Error while accessing template : {}", e.getMessage());
			return null;
		}
	}

	public static final Reader getPojoEntityTemplate() {
		try {
			return new FileReader(new ClassPathResource(POJO_ENTITY).getFile());
		} catch (IOException e) {
			LOGGER.error("Error while accessing template : {}", e.getMessage());
			return null;
		}
	}

	public static final Reader getControllerTemplate() {
		try {
			return new FileReader(new ClassPathResource(CONTROLLER).getFile());
		} catch (IOException e) {
			LOGGER.error("Error while accessing template : {}", e.getMessage());
			return null;
		}
	}

	public static final Reader getJPATemplate() {
		try {
			return new FileReader(new ClassPathResource(JPA_REPOSITORY).getFile());
		} catch (IOException e) {
			LOGGER.error("Error while accessing template : {}", e.getMessage());
			return null;
		}
	}

	public static final Reader getBootstrapTemplate() {
		try {
			return new FileReader(new ClassPathResource(BOOTSTRAP_PROPERTIES).getFile());
		} catch (IOException e) {
			LOGGER.error("Error while accessing template : {}", e.getMessage());
			return null;
		}
	}
	
	public static final Reader getConfigClassTemplate() {
		try {
			return new FileReader(new ClassPathResource(CONFIG_CLASSES).getFile());
		} catch (IOException e) {
			LOGGER.error("Error while accessing template : {}", e.getMessage());
			return null;
		}
	}
	
	public static final Reader getDockerTemplate() {
		try {
			return new FileReader(new ClassPathResource(DOCKERFILE).getFile());
		} catch (IOException e) {
			LOGGER.error("Error while accessing template : {}", e.getMessage());
			return null;
		}
	}
}
