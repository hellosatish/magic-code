package com.hellosatish.generator.engine.rendering.renderers;

import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hellosatish.generator.engine.constants.FilePathConstants;
import com.hellosatish.generator.engine.rendering.Renderer;
import com.hellosatish.generator.engine.rendering.metadata.ConfigurationRendererMetadata;
import com.hellosatish.generator.engine.rendering.response.RendererResponse;
import com.hellosatish.generator.engine.util.ProjectFilesHelper;
import com.hellosatish.generator.engine.util.ProjectPathContext;
import com.hellosatish.generator.engine.util.TemplateResolver;
import com.samskivert.mustache.Mustache;

/**
 * 
 * @author satish-s
 *  <pre>
 *  This class doesn't requires any writer as input. it ignores the
 *  writer if supplied This class creates own writers for all the
 *  required configuration files based on the meta data supplied.
 *  </pre>
 */
public class ConfigurationFilesRenderer implements Renderer {
	private ConfigurationRendererMetadata metadata;

	private static final Logger LOGGER = LogManager.getLogger(ClassRenderer.class);

	public ConfigurationFilesRenderer(Writer writer,ConfigurationRendererMetadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public RendererResponse call() throws Exception {
		LOGGER.debug("Compiling and rendering template for configuration files");


		ProjectPathContext pathContext = metadata.getPathContext();
		
		if (metadata.isActuatorEnabled()) {
			generateActuatorConfigurations( pathContext);
		}
		if (metadata.isCloudconfigEnabled()) {
			generateCloudConfigFiles( pathContext);
		}
		if (metadata.isLoadBalancingEnabled()) {
			generateLoadBalancingFiles( pathContext);
		}
		if (metadata.isSecurityEnabled()) {
			generateSecurityFiles( pathContext);
		}
		if (metadata.isServiceRegistrationEnabled()) {
			generateServiceRegistationFiles( pathContext);
		}
		if(metadata.isSwaggerEnabled()){
			generateSwaggerConfigurations(pathContext);
		}
		if (metadata.isDockerEnabled()) {
			generateDockerConfigurations(pathContext);
		}
		if(metadata.getMetrics() != null){
			generateMetricsConfigurations(pathContext);
		}
		
		LOGGER.debug("Configuration files Rendering Compelted.....");
		return null;
	}
	/**
	 * 
	 * @param pathContext The path resolver for source files and packages
	 * @return true if rendering is successful
	 * @throws IOException
	 * <pre>
	 * 	Generate file named {@link FilePathConstants}.FILE_CONIG_BOOTSTRAP 
	 * 		Enable the bootstrap.properties rendering and use the BOOTSTRAP_PROPERTIES as template
	 * </pre>
	 */
	private boolean generateCloudConfigFiles(ProjectPathContext pathContext) throws IOException {
		LOGGER.debug("Generating Cloud configurations");
		String resourceFolderPath = pathContext.getMainResourcesPath();
		Writer writer = ProjectFilesHelper.getWriterForFile(resourceFolderPath, FilePathConstants.FILE_CONIG_BOOTSTRAP);

		Mustache.compiler().compile(TemplateResolver.getBootstrapTemplate()).execute(pathContext, writer);
		writer.close();
		return true;
	}

	/**
	 * 
	 * @param pathContext The path resolver for source files and packages
	 * @return true if rendering is successful
	 * @throws IOException
	 * <pre>
	 * 	 Generate any actuator related configurations if required
	 * </pre>
	 */
	private boolean generateActuatorConfigurations( ProjectPathContext pathContext) {
		LOGGER.warn("As of now no files needs to be rendered for Actuator");
		return true;
	}

	/**
	 * 
	 * @param pathContext The path resolver for source files and packages
	 * @return true if rendering is successful
	 * @throws IOException
	 * <pre>
	 * 	generate load balancing related files if required
	 * </pre>
	 */
	private boolean generateLoadBalancingFiles( ProjectPathContext pathContext) {
		// TODO write logic to generate load balancing files
		return true;
	}

	/**
	 * 
	 * @param pathContext The path resolver for source files and packages
	 * @return true if rendering is successful
	 * @throws IOException
	 * <pre>
	 * 	generate security related files
	 * </pre>
	 */
	private boolean generateSecurityFiles( ProjectPathContext pathContext) {
		return true;
	}

	/**
	 * 
	 * @param pathContext The path resolver for source files and packages
	 * @return true if rendering is successful
	 * @throws IOException
	 * <pre>
	 * 	Generate file named {@link FilePathConstants}.FILE_DCOKERFILE 
	 * 		Enable the metrics rendering only and use the CONFIG_CLASSES as template
	 * </pre>
	 */
	private boolean generateServiceRegistationFiles( ProjectPathContext pathContext) {
		// TODO write logic to generate load balancing files
		return true;
	}

	/**
	 * 
	 * @param pathContext The path resolver for source files and packages
	 * @return true if rendering is successful
	 * @throws IOException
	 * <pre>
	 * 	Generate file named {@link FilePathConstants}.FILE_SWAGGER_CONF 
	 * 		Enable the swagger configuration rendering only and use the CONFIG_CLASSES as template
	 * </pre>
	 */
	private boolean generateSwaggerConfigurations( ProjectPathContext pathContext)  throws IOException {
		LOGGER.debug("Generating swagger configurations");
		String configPackagePath = pathContext.getConfigPackagePath();
		metadata.enableSwaggerRendering();
		Writer writer = ProjectFilesHelper.getWriterForFile(configPackagePath, FilePathConstants.FILE_SWAGGER_CONF);
		Mustache.compiler().compile(TemplateResolver.getConfigClassTemplate()).execute(metadata, writer);
		writer.close();
		return true;
	}
	/**
	 * 
	 * @param pathContext The path resolver for source files and packages
	 * @return true if rendering is successful
	 * @throws IOException
	 * <pre>
	 * 	Generate file named {@link FilePathConstants}.FILE_DCOKERFILE 
	 * 		Enable the DockerFile rendering only and use the DOCKERFILE as template
	 * </pre>
	 */
	private boolean generateDockerConfigurations( ProjectPathContext pathContext)  throws IOException {
		LOGGER.debug("Generating docker configurations");
		String projectRoot = pathContext.getProjectRootPath();
		Writer writer = ProjectFilesHelper.getWriterForFile(projectRoot, FilePathConstants.FILE_DCOKERFILE);
		Mustache.compiler().compile(TemplateResolver.getDockerTemplate()).execute(metadata, writer);
		writer.close();
		return true;
	}
	
	/**
	 * 
	 * @param pathContext The path resolver for source files and packages
	 * @return true if rendering is successful
	 * @throws IOException 
	 * <pre>
	 * 		Generate file named {@link FilePathConstants}.FILE_METRICS_CONF 
	 * 		Enable the metrics rendering only and use the CONFIG_CLASSES as template
	 * </pre>
	 */
	private boolean generateMetricsConfigurations( ProjectPathContext pathContext)  throws IOException {
		LOGGER.debug("Generating Metrics configurations");
		String configPackagePath = pathContext.getConfigPackagePath();
		Writer writer = ProjectFilesHelper.getWriterForFile(configPackagePath,  FilePathConstants.FILE_METRICS_CONF);
		metadata.enableMetricsRendering();
		Mustache.compiler().compile(TemplateResolver.getConfigClassTemplate()).execute(metadata, writer);
		writer.close();
		return true;
	}
}
