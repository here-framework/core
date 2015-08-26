package com.here.framework.core.config;

import java.util.ArrayList;
import java.util.List;
/**
 * FastDfs分布式文件配置
 * @author koujp
 *
 */
public class FastDfsConfig{
	public static class Address{
		private String host;
		private int port;
		public Address(){
			
		}
		public Address(String host,int port){
			this.host=host;
			this.port=port;
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
		public String toString(){
			return this.host+":"+this.port;
		}
	}
	private String httpAddress;
	private String default_group="group1";
	/**
	 * 连接建立时间
	 * in second
	 */
	private int connect_timeout;
	/**
	 * 超时时间
	 * in second
	 */
	private int network_timeout;
	private String charset;
	private int http_tracker_http_port;
	private boolean http_anti_steal_token;
	private String http_secret_key;
	private List<Address> tracker_servers=new ArrayList<FastDfsConfig.Address>();
	public int getConnect_timeout() {
		return connect_timeout;
	}
	public void setConnect_timeout(int connect_timeout) {
		this.connect_timeout = connect_timeout;
	}
	public int getNetwork_timeout() {
		return network_timeout;
	}
	public void setNetwork_timeout(int network_timeout) {
		this.network_timeout = network_timeout;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public int getHttp_tracker_http_port() {
		return http_tracker_http_port;
	}
	public void setHttp_tracker_http_port(int http_tracker_http_port) {
		this.http_tracker_http_port = http_tracker_http_port;
	}
	public boolean getHttp_anti_steal_token() {
		return http_anti_steal_token;
	}
	public void setHttp_anti_steal_token(boolean http_anti_steal_token) {
		this.http_anti_steal_token = http_anti_steal_token;
	}
	public String getHttp_secret_key() {
		return http_secret_key;
	}
	public void setHttp_secret_key(String http_secret_key) {
		this.http_secret_key = http_secret_key;
	}
	public List<Address> getTracker_servers() {
		return tracker_servers;
	}
	public List<String> getTracker_servers_String() {
		List<String> servers=new ArrayList<String>();
		for(Address addr:tracker_servers){
			servers.add(addr.toString());
		}
		return servers;
	}
	public void setTracker_servers(List<Address> tracker_servers) {
		this.tracker_servers = tracker_servers;
	}
	public String getDefault_group() {
		return default_group;
	}
	public void setDefault_group(String default_group) {
		this.default_group = default_group;
	}
	public String getHttpAddress() {
		return httpAddress;
	}
	public void setHttpAddress(String httpAddress) {
		this.httpAddress = httpAddress;
	}
}
