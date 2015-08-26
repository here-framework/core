package com.here.framework.core.mq.activemq;

import javax.jms.JMSException;
import javax.jms.QueueConnection;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;

import com.here.framework.core.config.MQConfig;
import com.here.framework.core.mq.MQProxy;
import com.here.framework.core.mq.QueueSendSession;
/**
 * 
 * @author koujp
 * 
 * MQ代理
 *
 */
public class ActiveMQProxy implements MQProxy {
	
	private PooledConnectionFactory pooledConnectionFactory=null;
	public ActiveMQProxy(MQConfig mqConfig) throws JMSException{
		this.initConnectionFactory(mqConfig);
	}
	private void initConnectionFactory(MQConfig mqConfig) throws JMSException{
		
		ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(mqConfig.getBrokerURL());
		connectionFactory.setUserName(mqConfig.getUsername());
		connectionFactory.setPassword(mqConfig.getPassword());
		
		pooledConnectionFactory=new PooledConnectionFactory(connectionFactory);
		
		pooledConnectionFactory.setMaxConnections(mqConfig.getMaxConnections());
		
		pooledConnectionFactory.setMaximumActiveSessionPerConnection(mqConfig.getMaximumActiveSessionPerConnection());
		
		
	}
	/* (non-Javadoc)
	 * @see com.here.framework.core.mq.activemq.MQProxy#createQueueConnection()
	 */
	@Override
	public QueueConnection createQueueConnection() throws JMSException{
		
		QueueConnection queueConnection=pooledConnectionFactory.createQueueConnection();
		return queueConnection;
	}
	@Override
	public QueueSendSession createQueueSession() throws JMSException{
		QueueConnection queueConnection = createQueueConnection();
		return new QueueSendSession(queueConnection);
	}
}
