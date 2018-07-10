package com.hellosatish.generator.engine.rendering.renderers;

import java.io.Writer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hellosatish.generator.engine.rendering.Renderer;
import com.hellosatish.generator.engine.rendering.metadata.ControllerRendererMetadata;
import com.hellosatish.generator.engine.rendering.response.RendererResponse;
import com.hellosatish.generator.engine.util.TemplateResolver;
import com.samskivert.mustache.Mustache;

public class ControllerRenderer implements Renderer {

	private ControllerRendererMetadata metadata;
	private Writer writer;

	private static final Logger LOGGER = LogManager.getLogger(JPARepositoryRenderer.class);

	public ControllerRenderer( Writer writer, ControllerRendererMetadata metadata) {
		this.writer = writer;
		this.metadata = metadata;
	}

	@Override
	public RendererResponse call() throws Exception {
		LOGGER.debug("Compiling and rendering template");
		Mustache.compiler().compile(TemplateResolver.getControllerTemplate()).execute(metadata,writer);
		writer.close();
		LOGGER.info("Rendering Compelted.....");
		return null;
	}

}