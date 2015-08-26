package com.here.framework.core.mq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueSession;
/**
 * 队列监听器管理
 * @author koujp
 *
 */
public class QueueListenerManager {
	private MQProxy mQProxy;
	
	private Map<Class<? extends HMessageListener>,Object> initPool = new ConcurrentHashMap<Class<? extends HMessageListener>, Object>();
	public QueueListenerManager(MQProxy activeMQProxy){
		this.mQProxy=activeMQProxy;
	}
	public synchronized boolean initQueueListener(String queueName,HMessageListener messageListener) throws JMSException{
		
		if(null != initPool.get(messageListener.getClass())){
			return false;
		}
		
		QueueSession queueSession = mQProxy.createQueueSession();
		
		Queue queue=queueSession.createQueue(queueName);
		
		MessageConsumer consumer=queueSession.createConsumer(queue);
		
		consumer.setMessageListener(messageListener);
		
		initPool.put(messageListener.getClass(), true);
		
		return true;
	}
}
