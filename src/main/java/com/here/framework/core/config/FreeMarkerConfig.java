package com.here.framework.core.config;

import java.util.Properties;
/**
 * freemarker配置类
 * @author koujp
 *
 */
public class FreeMarkerConfig {
	private String templateLoaderPath;
	private Properties freemarkerSettings;
	public String getTemplateLoaderPath() {
		return templateLoaderPath;
	}
	public void setTemplateLoaderPath(String templateLoaderPath) {
		this.templateLoaderPath = templateLoaderPath;
	}
	public Properties getFreemarkerSettings() {
		return freemarkerSettings;
	}
	public void setFreemarkerSettings(Properties freemarkerSettings) {
		this.freemarkerSettings = freemarkerSettings;
	}
}
