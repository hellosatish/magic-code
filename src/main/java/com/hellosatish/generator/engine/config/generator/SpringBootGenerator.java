package com.hellosatish.generator.engine.config.generator;

import static com.hellosatish.generator.engine.util.ProjectFilesHelper.createBaseStructureForJavaProject;
import static com.hellosatish.generator.engine.util.ProjectFilesHelper.createDirectories;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hellosatish.generator.engine.config.MagicCodeConfigurations;
import com.hellosatish.generator.engine.constants.FilePathConstants;
import com.hellosatish.generator.engine.rendering.TemplateRendrer;
import com.hellosatish.generator.engine.rendering.metadata.ClassRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.ConfigurationRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.ControllerRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.JpaRepositoryRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.PojoEntityRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.PomRendererMetadata;
import com.hellosatish.generator.engine.util.ProjectFilesHelper;
import com.hellosatish.generator.engine.util.ProjectPathContext;
import com.hellosatish.generator.metadata.ProjectGenerationMetadata;
import com.hellosatish.generator.metadata.extractors.MetadataExtractor;
/**
 * 
 * @author satish-s
 *
 */
@Component
public class SpringBootGenerator {

	
	private @Autowired TemplateRendrer renderer;
	private @Autowired MagicCodeConfigurations configurations;
	
	public boolean generate(ProjectGenerationMetadata project) throws IOException{
		boolean succeeded = false;
		String projectsWorkspace = configurations.getWorkspacelocation();
		
		//Populate ProjectPathContext object 
		String projectName = StringUtils.cleanPath(project.getProjectName());
		ProjectPathContext pathContext = new ProjectPathContext(projectName,projectsWorkspace+projectName, project.getBasePackage());

		succeeded = createDirectories(projectsWorkspace, projectName);
		
		//Create Base structure for project
		succeeded = createBaseStructureForJavaProject( pathContext);
		

		//Create pom.xml
		PomRendererMetadata pomRendererMetadata = MetadataExtractor.extractPomMetadata(project);
			Writer pomWriter = ProjectFilesHelper.getWriterForFile(pathContext.getProjectRootPath(), FilePathConstants.FILE_POM);
			renderer.sumitForRendering(pomWriter,pomRendererMetadata);
		
		//Render main class
		Writer mainFilewriter = ProjectFilesHelper.getWriterForFile(pathContext.getBasePackagePath(), StringUtils.capitalize(project.getProjectName())+"Application.java");
			ClassRendererMetadata mainClassMd = MetadataExtractor.extractDataForMainClass(project);
			renderer.sumitForRendering(mainFilewriter,mainClassMd);

		//create configuration related files based on the config section of metadata received
		ConfigurationRendererMetadata configMd = MetadataExtractor.extractConfigurationMetadata(project, pathContext);
			renderer.sumitForRendering(null, configMd);
			
		//render DTO's
		List<PojoEntityRendererMetadata> pojoentities = MetadataExtractor.extractPojoMetadata(project);	
		ProjectFilesHelper.createDirectory(pathContext.getDtoPackagePath());
		pojoentities.parallelStream().forEach( md -> {
			md.setPackageName(pathContext.getDtoPackage());
			Writer sourceWriter = ProjectFilesHelper.getWriterForFile( pathContext.getDtoPackagePath(), md.getClassName()+FilePathConstants.EXTN_JAVA);
			renderer.sumitForRendering(sourceWriter,md);
		});

		//render Db entities
		List<PojoEntityRendererMetadata> dbentities = MetadataExtractor.extractEntityMetadata(project);	
		ProjectFilesHelper.createDirectory(pathContext.getDbEntitiesPackagePath());
		dbentities.parallelStream().forEach( md -> {
			md.setPackageName(pathContext.getDbEntitiesPackage());
			Writer sourceWriter = ProjectFilesHelper.getWriterForFile( pathContext.getDbEntitiesPackagePath(), md.getClassName()+FilePathConstants.EXTN_JAVA);
			renderer.sumitForRendering(sourceWriter,md);
		});
		
		//create repositories directory and JPA repository classes
		List<JpaRepositoryRendererMetadata>  jpaRepos = MetadataExtractor.extractJpaRepositoryMetadata(project, pathContext);
		ProjectFilesHelper.createDirectory(pathContext.getDbRepositoryPackagePath());
		jpaRepos.parallelStream().forEach( jpaRepoMd -> {
			Writer sourceWriter = ProjectFilesHelper.getWriterForFile( pathContext.getDbRepositoryPackagePath(), jpaRepoMd.getRepositoryName()+FilePathConstants.SUFFIX_JPA_REPO+FilePathConstants.EXTN_JAVA);
			renderer.sumitForRendering(sourceWriter,jpaRepoMd);
		});
		
		//render controllers
		List<ControllerRendererMetadata> controllers = MetadataExtractor.extractControllerMetadata(project, pathContext);
		ProjectFilesHelper.createDirectory( pathContext.getControllersPackagePath());
		controllers.parallelStream().forEach( controllerMd -> {
			Writer sourceWriter = ProjectFilesHelper.getWriterForFile( pathContext.getControllersPackagePath(),StringUtils.capitalize( controllerMd.getClassName())+FilePathConstants.SUFFIX_CONTROLLER+FilePathConstants.EXTN_JAVA);
			renderer.sumitForRendering(sourceWriter,controllerMd);
		});
			
		return succeeded;
	}
}
