package com.here.framework.core.config;

/**
 * 消息配置
 * @author koujp
 *
 */
public class MQConfig{
	private String brokerURL;
	private int maxConnections;
	private int maximumActiveSessionPerConnection;
	private String username;
	private String password;
	public String getBrokerURL() {
		return brokerURL;
	}
	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}
	public int getMaxConnections() {
		return maxConnections;
	}
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
	public int getMaximumActiveSessionPerConnection() {
		return maximumActiveSessionPerConnection;
	}
	public void setMaximumActiveSessionPerConnection(
			int maximumActiveSessionPerConnection) {
		this.maximumActiveSessionPerConnection = maximumActiveSessionPerConnection;
	}
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
}
