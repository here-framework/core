
package org.csource.fastdfs.test;

import java.io.*;
import java.net.*;
import java.util.*;

import org.csource.common.*;
import org.csource.fastdfs.*;

public class Test1
{
  public static void main(String args[])
  {
  	try
  	{
		FdfsClientConfig.init("/disk/source/virtual_space/virtualspace_core/src/main/java/fdfs_client.conf");
		System.out.println("network_timeout=" + FdfsClientConfig.g_network_timeout + "ms");
		System.out.println("charset=" + FdfsClientConfig.g_charset);
  		
		TrackerGroup tg = new TrackerGroup(new InetSocketAddress[]{
				new InetSocketAddress("101.200.233.161", 22122)
				});
		TrackerClient tc = new TrackerClient(tg);
		
		TrackerServer ts = tc.getConnection();
		if (ts == null)
		{
			System.out.println("getConnection return null");
			return;
		}
		StorageServer[] storageServers = tc.getStoreStorages(ts, null);
		for(StorageServer s:storageServers){
			System.out.println(s.getInetSocketAddress()+"---");
		}
		StorageServer ss = tc.getStoreStorage(ts);
		if (ss == null)
		{
			System.out.println("getStoreStorage return null");
		}
		
		StorageClient1 sc1 = new StorageClient1(ts, ss);
		
		NameValuePair[] meta_list = null;  //new NameValuePair[0];
		String item = "/disk/picture/temp/upload.jpg";
		String fileid = sc1.upload_file1(item, "jpg", meta_list); //�����쳣
		
		System.out.println("Upload local file "+item+" ok, fileid="+fileid);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	}
}
