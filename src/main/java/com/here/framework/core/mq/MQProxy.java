package com.here.framework.core.mq;

import javax.jms.JMSException;
import javax.jms.QueueConnection;

public interface MQProxy {

	public abstract QueueConnection createQueueConnection() throws JMSException;

	public abstract QueueSendSession createQueueSession() throws JMSException;

}