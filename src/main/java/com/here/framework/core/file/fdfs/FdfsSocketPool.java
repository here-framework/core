package com.here.framework.core.file.fdfs;

import java.util.Map;
import java.util.Queue;

import com.here.framework.log.HLogger;
import com.here.framework.socket.SocketPool;
/**
 * fdfs  socketpool
 * @author koujp
 *
 */
public class FdfsSocketPool extends SocketPool {
	@Override
	protected void finalize() throws Throwable{
		for(Map.Entry<SocketKey,Queue<SocketInfo>> entry:socketCache.entrySet()){
			Queue<SocketInfo> socketInfos=entry.getValue();
			for(SocketInfo socketInfo:socketInfos){
				try{
					socketInfo.getSocket().close();
					ProtoCommon.destroySocket(socketInfo.getSocket());
				}catch(Exception e){
					HLogger.getInstance(SocketPool.class).error(e);
				}
			}
		}
	}
}
