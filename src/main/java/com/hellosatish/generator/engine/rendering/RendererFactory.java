package com.hellosatish.generator.engine.rendering;

import java.io.Writer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hellosatish.generator.engine.rendering.metadata.ClassRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.ConfigurationRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.ControllerRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.GeneratorMetadata;
import com.hellosatish.generator.engine.rendering.metadata.JpaRepositoryRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.PojoEntityRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.PomRendererMetadata;
import com.hellosatish.generator.engine.rendering.renderers.ClassRenderer;
import com.hellosatish.generator.engine.rendering.renderers.ConfigurationFilesRenderer;
import com.hellosatish.generator.engine.rendering.renderers.ControllerRenderer;
import com.hellosatish.generator.engine.rendering.renderers.JPARepositoryRenderer;
import com.hellosatish.generator.engine.rendering.renderers.PojoEntityRenderer;
import com.hellosatish.generator.engine.rendering.renderers.PomRenderer;
/**
 * 
 * @author satish-s
 * <p>
 *  Factory class which shall return New instance of Renderer based on the type of metadata received.
 *  </p>
 *  
 */
@Component
public class RendererFactory {

	private static final Logger LOGGER = LogManager.getLogger(RendererFactory.class);

	public Renderer getRenderer(Writer writer, GeneratorMetadata metadata) {
		LOGGER.debug(" Recieved metadata  :" + metadata);

		if (metadata instanceof PojoEntityRendererMetadata) {
			LOGGER.debug("Returning PojoRenderer");
			return new PojoEntityRenderer(writer, (PojoEntityRendererMetadata) metadata);
		} else if (metadata instanceof PomRendererMetadata) {
			LOGGER.debug("Returning PomRenderer");
			return new PomRenderer(writer,(PomRendererMetadata) metadata);
		}else if (metadata instanceof ControllerRendererMetadata ){
			LOGGER.debug("Returning ControllerRendererMetadata");
			return new ControllerRenderer(writer,(ControllerRendererMetadata) metadata);
		} else if (metadata instanceof ClassRendererMetadata ){ //TODO find to separate form sb class logic
			LOGGER.debug("Returning ClassRenderer");
			return new ClassRenderer(writer,(ClassRendererMetadata) metadata);
		}else if (metadata instanceof ConfigurationRendererMetadata ){
			LOGGER.debug("Returning ConfigurationFilesRenderer");
			return new ConfigurationFilesRenderer(writer,(ConfigurationRendererMetadata) metadata);
		}else if (metadata instanceof PojoEntityRendererMetadata ){
			LOGGER.debug("Returning PojoEntityRendererMetadata");
			return new PojoEntityRenderer(writer,(PojoEntityRendererMetadata) metadata);
		}else if (metadata instanceof JpaRepositoryRendererMetadata ){
			LOGGER.debug("Returning JpaRepositoryRendererMetadata");
			return new JPARepositoryRenderer(writer,(JpaRepositoryRendererMetadata) metadata);
		}
		
		else {
			LOGGER.error("Invalid instance of metadata object. The rendering will fail");
			return null;
		}
	}

}
