/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
*/

package com.here.framework.core.file.fdfs;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
* Storage Server Info
* @author Happy Fish / YuQing
* @version Version 1.11
*/
public class StorageServer extends TrackerServer
{
	protected int store_path_index = 0;
	
	//private FdfsClientConfig clientConfig;
	
/**
* Constructor
* @param ip_addr the ip address of storage server
* @param port the port of storage server
* @param store_path the store path index on the storage server
*/
	public StorageServer(String ip_addr, int port, int store_path,FdfsClientConfig clientConfig) throws IOException
	{		
		super(clientConfig.getSocket(ip_addr, port), new InetSocketAddress(ip_addr, port),clientConfig);
		this.store_path_index = store_path;
		
		
	}

/**
* Constructor
* @param ip_addr the ip address of storage server
* @param port the port of storage server
* @param store_path the store path index on the storage server
*/
	public StorageServer(String ip_addr, int port, byte store_path,FdfsClientConfig clientConfig) throws IOException
	{
		super(clientConfig.getSocket(ip_addr, port), new InetSocketAddress(ip_addr, port),clientConfig);
		if (store_path < 0)
		{
			this.store_path_index = 256 + store_path;
		}
		else
		{
			this.store_path_index = store_path;
		}
	}
	
/**
* @return the store path index on the storage server
*/
	public int getStorePathIndex()
	{
		return this.store_path_index;
	}
}
