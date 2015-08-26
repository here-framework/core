/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
**/

package com.here.framework.core.file.fdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.csource.common.FdfsException;

import com.here.framework.core.config.FastDfsConfig;
import com.here.framework.log.HLogger;

/**
* Global variables
* @author Happy Fish / YuQing
* @version Version 1.11
*/
public class FdfsClientConfig
{
	public String httpAddress;
	public int g_connect_timeout; //millisecond
	public int g_network_timeout; //millisecond
	public String g_charset;
	public int g_tracker_http_port;
	public boolean g_anti_steal_token;  //if anti-steal token
	public String g_secret_key;   //generage token secret key
	public TrackerGroup g_tracker_group;
	
	private FdfsSocketPool socketPool=new FdfsSocketPool();
	
	public static final int DEFAULT_CONNECT_TIMEOUT = 5;  //second
	public static final int DEFAULT_NETWORK_TIMEOUT = 30; //second
	
	private String default_group=null;
	public FdfsClientConfig(FastDfsConfig fdfsConfig)
	{
		try {
			this.init(fdfsConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void releaseSocket(Socket socket){
		socketPool.releaseSocket(socket);
	}
	
/**
* load global variables
* @param conf_filename config filename
*/
	public void init(FastDfsConfig fdfsConfig) throws FileNotFoundException, IOException, FdfsException
	{
		this.httpAddress=fdfsConfig.getHttpAddress();
		
		socketPool.setConnectTimeout(fdfsConfig.getConnect_timeout());
		socketPool.setNetworkTimeout(fdfsConfig.getNetwork_timeout());
		
		
		default_group=fdfsConfig.getDefault_group();
		//IniFileReader iniReader;
  		String[] szTrackerServers;
			String[] parts;
			
  		//iniReader = new IniFileReader(conf_filename);

			g_connect_timeout = fdfsConfig.getConnect_timeout();//iniReader.getIntValue("connect_timeout", DEFAULT_CONNECT_TIMEOUT);
  		if (g_connect_timeout < 0)
  		{
  			g_connect_timeout = DEFAULT_CONNECT_TIMEOUT;
  		}
  		g_connect_timeout *= 1000; //millisecond
  		
  		g_network_timeout = fdfsConfig.getNetwork_timeout();//iniReader.getIntValue("network_timeout", DEFAULT_NETWORK_TIMEOUT);
  		if (g_network_timeout < 0)
  		{
  			g_network_timeout = DEFAULT_NETWORK_TIMEOUT;
  		}
  		g_network_timeout *= 1000; //millisecond

  		g_charset = fdfsConfig.getCharset();//iniReader.getStrValue("charset");
  		if (g_charset == null || g_charset.length() == 0)
  		{
  			g_charset = "ISO8859-1";
  		}
  		
  		szTrackerServers = fdfsConfig.getTracker_servers_String().toArray(new String[0]);//iniReader.getValues("tracker_server");
  		if (szTrackerServers == null || szTrackerServers.length==0)
  		{
  			throw new FdfsException("item \"tracker_server\" in tracker_server item config error");
  		}
  		
  		InetSocketAddress[] tracker_servers = new InetSocketAddress[szTrackerServers.length];
  		for (int i=0; i<szTrackerServers.length; i++)
  		{
  			parts = szTrackerServers[i].split("\\:", 2);
  			if (parts.length != 2)
  			{
  				throw new FdfsException("the value of item \"tracker_server\" is invalid, the correct format is host:port");
  			}
  			
  			tracker_servers[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
  		}
  		g_tracker_group = new TrackerGroup(tracker_servers);
  		
  		g_tracker_http_port = fdfsConfig.getHttp_tracker_http_port();//iniReader.getIntValue("http.tracker_http_port", 80);
  		g_anti_steal_token = fdfsConfig.getHttp_anti_steal_token();//iniReader.getBoolValue("http.anti_steal_token", false);
  		if (g_anti_steal_token)
  		{
  			g_secret_key = fdfsConfig.getHttp_secret_key();//iniReader.getStrValue("http.secret_key");
  		}
	}
	
/**
* construct Socket object
* @param ip_addr ip address or hostname
* @param port port number
* @return connected Socket object
*/
	public Socket getSocket(String ip_addr, int port) throws IOException
	{
//		Socket sock = new Socket();
//		sock.setSoTimeout(g_network_timeout);
//		sock.connect(new InetSocketAddress(ip_addr, port), this.g_connect_timeout);
//		return sock;
		
		return getSocket(new InetSocketAddress(ip_addr, port));
	}
	
	
	
/**
* construct Socket object
* @param addr InetSocketAddress object, including ip address and port
* @return connected Socket object
*/
	public Socket getSocket(InetSocketAddress addr) throws IOException
	{
//		Socket sock = new Socket();
//		sock.setSoTimeout(this.g_network_timeout);
//		sock.connect(addr, this.g_connect_timeout);
//		return sock;
		
		Socket sock=socketPool.getSocket(addr.getHostName(),addr.getPort());
		int max=socketPool.getMaxSocketPoolSize();
		for(int i=0;i<max;i++){
			try{
				if(ProtoCommon.activeTest(sock)){
					break;
				}
			}catch(Exception e){
				try{
					sock.close();
				}catch(Exception e1){
					HLogger.getInstance(this.getClass()).error(e1);
				}
				HLogger.getInstance(this.getClass()).error(e);
			}
			
			System.out.println("-------for-----"+i);
			sock=socketPool.getSocket(addr.getHostName(),addr.getPort());
		}
		return sock;
		
	}
	
	public int getG_connect_timeout()
	{
		return g_connect_timeout;
	}
	
	public void setG_connect_timeout(int connect_timeout)
	{
		this.g_connect_timeout = connect_timeout;
	}
	
	public int getG_network_timeout()
	{
		return g_network_timeout;
	}
	
	public void setG_network_timeout(int network_timeout)
	{
		this.g_network_timeout = network_timeout;
	}
	
	public String getG_charset()
	{
		return g_charset;
	}
	
	public void setG_charset(String charset)
	{
		this.g_charset = charset;
	}
	
	public int getG_tracker_http_port()
	{
		return g_tracker_http_port;
	}
	
	public void setG_tracker_http_port(int tracker_http_port)
	{
		this.g_tracker_http_port = tracker_http_port;
	}
	
	public boolean getG_anti_steal_token()
	{
		return g_anti_steal_token;
	}
	
	public boolean isG_anti_steal_token()
	{
		return g_anti_steal_token;
	}
	
	public void setG_anti_steal_token(boolean anti_steal_token)
	{
		this.g_anti_steal_token = anti_steal_token;
	}
	
	public String getG_secret_key()
	{
		return g_secret_key;
	}
	
	public void setG_secret_key(String secret_key)
	{
		this.g_secret_key = secret_key;
	}
	
	public TrackerGroup getG_tracker_group()
	{
		return g_tracker_group;
	}
	
	public void setG_tracker_group(TrackerGroup tracker_group)
	{
		this.g_tracker_group = tracker_group;
	}



	public FdfsSocketPool getSocketPool() {
		return socketPool;
	}



	public void setSocketPool(FdfsSocketPool socketPool) {
		this.socketPool = socketPool;
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
