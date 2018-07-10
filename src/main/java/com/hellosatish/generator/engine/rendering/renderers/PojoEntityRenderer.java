package com.hellosatish.generator.engine.rendering.renderers;

import java.io.Writer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hellosatish.generator.engine.rendering.Renderer;
import com.hellosatish.generator.engine.rendering.metadata.PojoEntityRendererMetadata;
import com.hellosatish.generator.engine.rendering.response.RendererResponse;
import com.hellosatish.generator.engine.util.TemplateResolver;
import com.samskivert.mustache.Mustache;

public final class PojoEntityRenderer implements Renderer {

	private PojoEntityRendererMetadata metadata;
	private Writer writer;

	private static final Logger LOGGER = LogManager.getLogger(PojoEntityRenderer.class);

	public PojoEntityRenderer( Writer writer, PojoEntityRendererMetadata metadata) {
		this.writer = writer;
		this.metadata = metadata;
	}

	@Override
	public RendererResponse call() throws Exception {
		LOGGER.debug("Compiling and rendering template");
		Mustache.compiler().compile(TemplateResolver.getPojoEntityTemplate()).execute(metadata, writer);
		writer.close();
		LOGGER.info("Rendering Compelted.....");
		return null;
	}

}
