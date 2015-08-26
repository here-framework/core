package com.here.framework.core.config;

import com.here.framework.config.BaseConfig;
/**
 * Redis閰嶇疆
 * @author koujp
 *
 */
public class RedisConfig extends BaseConfig {
	private String name;
	private String host;
	private int port = 6379;
	private String username;
	private String password;
	private int maxTotal;
	private int maxIdle;
	private int minIdle;
	private boolean isTestOnBorrow;
	private int timeOut;
	private boolean isDefault;
	
	public RedisConfig(){}
	public RedisConfig(String host,int port,String password)
	{
		this.host = host;
		this.port = port;
		this.password = password;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public boolean isTestOnBorrow() {
		return isTestOnBorrow;
	}
	public void setTestOnBorrow(boolean isTestOnBorrow) {
		this.isTestOnBorrow = isTestOnBorrow;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
