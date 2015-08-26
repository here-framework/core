package com.here.framework.core.config;

import java.util.Properties;

/**
 * 邮件配置
 * @author koujp
 *
 */
public class EmailConfig {
	private String host;
	private int port = 25;
	private String protocol = "smtp";
	private String username;
	private String password;
	private String defaultEncoding = "utf-8";
	private Properties mailProperties =  new Properties();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDefaultEncoding() {
		return defaultEncoding;
	}
	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}
	public Properties getMailProperties() {
		return mailProperties;
	}
	public void setMailProperties(Properties mailProperties) {
		this.mailProperties = mailProperties;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}
