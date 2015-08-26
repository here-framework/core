package com.here.framework.core.mail;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;

import com.here.framework.bean.init.HBeanInitException;
import com.here.framework.core.config.EmailConfig;
/**
 * MailSender manager
 * @author koujp
 *
 */
public class HMailSenderManager implements InitializingBean{
	private EmailConfig emailConfig;
	private String senderName = HMailSender.class.getName();
	private static final Map<String,HMailSender> instanceCache = new ConcurrentHashMap<String, HMailSender>();
	@Override
	public void afterPropertiesSet() throws Exception {
		if(null == instanceCache.get(senderName)){
			instanceCache.put(senderName, new HMailSender(emailConfig));
		}else{
			throw new HBeanInitException("error has exist a same senderName:"+senderName+" "+HMailSender.class.getSimpleName()+" exist!");
		}
	}
	public static HMailSender getMailSender(){
		return instanceCache.get(HMailSender.class.getName());
	}
	public static HMailSender getMailSender(String senderName){
		return instanceCache.get(senderName);
	}
}
