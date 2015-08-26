package com.here.framework.core.mq;

import java.io.Serializable;

import javax.jms.BytesMessage;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

/**
 * 队列发送回话
 * @author koujp
 *
 */
public class QueueSendSession implements QueueSession{
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	public QueueSendSession(QueueConnection queueConnection) throws JMSException{
		this.queueConnection = queueConnection;
		this.initQueueSession();
	}
	public void initQueueSession() throws JMSException{
		queueConnection.start();
		queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	public TextMessage createTextMessage() throws JMSException{
		return queueSession.createTextMessage();
	}
	public TextMessage createTextMessage(String text) throws JMSException{
		return queueSession.createTextMessage(text);
	}
	public MapMessage createMapMessage() throws JMSException{
		return queueSession.createMapMessage();
	}
	public ObjectMessage createObjectMessage() throws JMSException{
		return queueSession.createObjectMessage();
	}
	public ObjectMessage createObjectMessage(Serializable obj) throws JMSException{
		return queueSession.createObjectMessage(obj);
	}
	public BytesMessage createBytesMessage() throws JMSException{
		return queueSession.createBytesMessage();
	}
	public void sendMessage(String queueName,Message message) throws JMSException{
		
		Queue queue=queueSession.createQueue(queueName);
		
		MessageProducer producer=queueSession.createProducer(queue);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		producer.setPriority(9);
		
		producer.send(message);
	}
	@Override
	public void close() throws JMSException {
		queueSession.close();
		queueConnection.close();
		
	}
	@Override
	public void commit() throws JMSException {
		queueSession.commit();
		
	}
	@Override
	public MessageConsumer createConsumer(Destination arg0) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createConsumer(arg0);
	}
	@Override
	public MessageConsumer createConsumer(Destination arg0, String arg1)
			throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createConsumer(arg0, arg1);
	}
	@Override
	public MessageConsumer createConsumer(Destination arg0, String arg1,
			boolean arg2) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createConsumer(arg0, arg1, arg2);
	}
	@Override
	public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1)
			throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createDurableSubscriber(arg0, arg1);
	}
	@Override
	public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1,
			String arg2, boolean arg3) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createDurableSubscriber(arg0, arg1, arg2, arg3);
	}
	@Override
	public Message createMessage() throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createMessage();
	}
	@Override
	public MessageProducer createProducer(Destination arg0) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createProducer(arg0);
	}
	@Override
	public StreamMessage createStreamMessage() throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createStreamMessage();
	}
	@Override
	public TemporaryTopic createTemporaryTopic() throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createTemporaryTopic();
	}
	@Override
	public Topic createTopic(String arg0) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createTopic(arg0);
	}
	@Override
	public int getAcknowledgeMode() throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.getAcknowledgeMode();
	}
	@Override
	public MessageListener getMessageListener() throws JMSException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean getTransacted() throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.getTransacted();
	}
	@Override
	public void recover() throws JMSException {
		queueSession.recover();
		
	}
	@Override
	public void rollback() throws JMSException {
		queueSession.rollback();
		
	}
	@Override
	public void run() {
		queueSession.run();
		
	}
	@Override
	public void setMessageListener(MessageListener messageListener) throws JMSException {
		queueSession.setMessageListener(messageListener);
		
	}
	@Override
	public void unsubscribe(String arg0) throws JMSException {
		queueSession.unsubscribe(arg0);
		
	}
	@Override
	public QueueBrowser createBrowser(Queue arg0) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createBrowser(arg0);
	}
	@Override
	public QueueBrowser createBrowser(Queue queue, String arg1)
			throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createBrowser(queue, arg1);
	}
	@Override
	public Queue createQueue(String queueName) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createQueue(queueName);
	}
	@Override
	public QueueReceiver createReceiver(Queue queue) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createReceiver(queue);
	}
	@Override
	public QueueReceiver createReceiver(Queue queue, String arg1)
			throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createReceiver(queue,arg1);
	}
	@Override
	public QueueSender createSender(Queue queue) throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createSender(queue);
	}
	@Override
	public TemporaryQueue createTemporaryQueue() throws JMSException {
		// TODO Auto-generated method stub
		return queueSession.createTemporaryQueue();
	}
}
