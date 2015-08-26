/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
*/

package com.here.framework.core.file.fdfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
* Tracker Server Info
* @author Happy Fish / YuQing
* @version Version 1.11
*/
public class TrackerServer
{
	protected Socket sock;
	protected InetSocketAddress inetSockAddr;
	public FdfsClientConfig clientConfig;
	
/**
* Constructor
* @param sock Socket of server
* @param inetSockAddr the server info
*/
	public TrackerServer(Socket sock, InetSocketAddress inetSockAddr,FdfsClientConfig clientConfig)
	{
		this.sock = sock;
		this.inetSockAddr = inetSockAddr;
		this.clientConfig=clientConfig;
	}
	
/**
* get the connected socket
* @return the socket
*/
	public Socket getSocket() throws IOException
	{
		if (this.sock == null)
		{
			this.sock = clientConfig.getSocket(this.inetSockAddr);
		}
		
		return this.sock;
	}
	
/**
* get the server info
* @return the server info
*/
	public InetSocketAddress getInetSocketAddress()
	{
		return this.inetSockAddr;
	}
	
	public OutputStream getOutputStream() throws IOException
	{
		return this.sock.getOutputStream();
	}
	
	public InputStream getInputStream() throws IOException
	{
		return this.sock.getInputStream();
	}

	public void close() throws IOException
	{
		if (this.sock != null)
		{
			try
			{
				//ProtoCommon.destroySocket(this.sock);
				this.clientConfig.releaseSocket(this.sock);
			}
			finally
			{
				this.sock = null;
			}
		}
	}
	protected void finalize() throws Throwable
	{
		this.close();
	}
}
