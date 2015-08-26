package com.here.framework.core.mq;

import java.lang.reflect.Constructor;
/**
 * 队列监听器管理工厂
 * @author koujp
 *
 */
public class QueueListenerManagerInit{
	public QueueListenerManagerInit(MQProxy mQProxy) throws Exception{
		init(mQProxy,null);
	}
	public QueueListenerManagerInit(MQProxy mQProxy,String instanceName) throws Exception{
		init(mQProxy,instanceName);
	}
	public QueueListenerManager init(MQProxy mQProxy,String instanceName) throws Exception {
		QueueListenerManager instance = null;
		Constructor<QueueListenerManager> construct=null;
		if(null == instanceName){
			 construct=QueueListenerManager.class.getDeclaredConstructor(MQProxy.class);
			 construct.setAccessible(true);
			 instance = construct.newInstance(mQProxy);
		}else{
			construct=QueueListenerManager.class.getDeclaredConstructor(MQProxy.class,instanceName.getClass());
			construct.setAccessible(true);
			instance = construct.newInstance(mQProxy,instanceName);
		}
		return instance;
	}
	
}
