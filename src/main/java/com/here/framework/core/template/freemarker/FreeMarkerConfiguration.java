package com.here.framework.core.template.freemarker;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.here.framework.core.config.FreeMarkerConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemaker配置文件
 * @author koujp
 *
 */
public class FreeMarkerConfiguration extends FreeMarkerConfigurationFactory{
	private Configuration configuration;
	public FreeMarkerConfiguration(FreeMarkerConfig config) throws IOException, TemplateException{
		initConfiguration(config);
	}
	private void initConfiguration(FreeMarkerConfig config) throws IOException, TemplateException {
		 BeanUtils.copyProperties(config, this);
		 configuration = this.createConfiguration();
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public Template getTemplate(String templateName) throws IOException{
		return configuration.getTemplate(templateName);
	}
	/**
	 * 根据模板名称和模型执行获取最终html
	 * @param templateName 模板名称
	 * @param model 模型
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String process(String templateName,Object model) throws IOException, TemplateException{
		Template template = getTemplate(templateName);
		return FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
	}
	public String processText(String text,Object model) throws IOException, TemplateException{
		String name = "template_"+System.currentTimeMillis();
		Template template = new Template(name, text, configuration);
		return FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
	}
}
