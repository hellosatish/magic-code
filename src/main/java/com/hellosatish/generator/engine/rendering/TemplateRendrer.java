package com.hellosatish.generator.engine.rendering;

import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hellosatish.generator.engine.config.ExceptionHandlingAsyncTaskExecutor;
import com.hellosatish.generator.engine.rendering.metadata.ConfigurationRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.GeneratorMetadata;
/**
 * 
 * @author satish-s
 * <pre>
 * This class receives the instance of {@link GeneratorMetadata}  which is the metadta for renderer
 * and {@link Writer} to the file path where the file shall be rendered.
 *  <strong> For {@link ConfigurationRendererMetadata} the writer argument is ignored</strong>
 * </pre>
 */
@Component
public class TemplateRendrer {
	
	@Autowired
	private RendererFactory rendererFactory;
	
	
	@Autowired
	private ExceptionHandlingAsyncTaskExecutor taskExecutor;
	/**
	 * 
	 * @param writer Writer to the file where the rederered file shall be written
	 * @param metadata The metadata required by the particular renderer
	 */
	public void sumitForRendering(Writer writer,GeneratorMetadata metadata){
		
		Renderer renderer = rendererFactory.getRenderer(writer, metadata);

		taskExecutor.submit(renderer);
	}
	

}
