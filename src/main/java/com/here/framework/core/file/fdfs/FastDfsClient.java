package com.here.framework.core.file.fdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.csource.common.NameValuePair;

import com.here.framework.log.HLogger;

/**
 * fastdfs  文件客户端
 * 
 *FastDfsClient  为比较重量的客户端，使用的时候仅需创建一个实例或几个，避免每次调用都创建这个对象
 *每个客户端拥有一个socket池
 * @author koujp
 *
 */
public class FastDfsClient {
	
	private FdfsClientConfig clientConfig;
	public FastDfsClient(FdfsClientConfig clientConfig){
		this.clientConfig=clientConfig;
	}
	public String[] upload(File file,String ext_name) throws Exception{
		FileInputStream in=new FileInputStream(file);
		try{
			return this.upload(in, file.length(), ext_name);
		}finally{
			if(null!=in){
				in.close();
			}
		}
	}
	public String[] upload(File file,String ext_name,Map<String,String> meta_map) throws Exception{
		FileInputStream in=new FileInputStream(file);
		try{
			return this.upload(in, file.length(), ext_name, meta_map);
		}finally{
			if(null!=in){
				in.close();
			}
		}
		
	}
	public String[] upload(InputStream fileIn,long fileLength,String ext_name) throws Exception{
		return upload(fileIn,fileLength,ext_name,null);
	}
	public String[] upload(InputStream fileIn,long fileLength,String ext_name,Map<String,String> meta_map) throws Exception{
		
		String[] pairs=null;
		TrackerServer ts=null;
		StorageServer ss=null;
		try{
			List<NameValuePair> meta_list=new ArrayList<NameValuePair>();
			if(null!=meta_map){
				for(Map.Entry<String, String> entry:meta_map.entrySet()){
					meta_list.add(new NameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			
			
			TrackerGroup tg = new TrackerGroup(clientConfig);
			TrackerClient tc = new TrackerClient(tg);
			
			ts = tc.getConnection();
			
			ss = tc.getStoreStorage(ts);
			
			StorageClient sc = new StorageClient1(ts, ss);
			
			pairs=sc.upload_file(fileIn, fileLength,ext_name, meta_list.toArray(new NameValuePair[0]));
			
			
			
		}catch(Exception e){
			HLogger.getInstance(FastDfsClient.class).error(e);
			throw new Exception(e);
		}finally{
			if(null!=ts){
				ts.close();
			}
			if(null!=ss){
				ss.close();
			}
		}
		return pairs;
	}
	/**
	 * 删除文件
	 * @param file_name
	 * @return
	 * @throws Exception
	 */
	public int delete(String file_name) throws Exception{
		int result=0;
		TrackerServer ts=null;
		StorageServer ss=null;
		try{
			
			TrackerGroup tg = new TrackerGroup(clientConfig);
			TrackerClient tc = new TrackerClient(tg);
			
			ts = tc.getConnection();
			
			ss = tc.getStoreStorage(ts);
			
			StorageClient sc = new StorageClient1(ts, ss);
			
			result=sc.delete_file(clientConfig.getDefault_group(), file_name);
			
		}catch(Exception e){
			HLogger.getInstance(FastDfsClient.class).error(e);
			throw new Exception(e);
		}finally{
			if(null!=ts){
				ts.close();
			}
			if(null!=ss){
				ss.close();
			}
		}
		return result;
	}
	public FdfsClientConfig getClientConfig() {
		return clientConfig;
	}
	public void setClientConfig(FdfsClientConfig clientConfig) {
		this.clientConfig = clientConfig;
	}
}
