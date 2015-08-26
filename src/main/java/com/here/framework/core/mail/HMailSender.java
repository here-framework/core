package com.here.framework.core.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.here.framework.core.config.EmailConfig;
/**
 * 邮件发送
 * @author koujp
 *
 */
public class HMailSender extends JavaMailSenderImpl {
	public HMailSender(EmailConfig emailConfig){
		init(emailConfig);
	}
	private void init(EmailConfig emailConfig){
		this.setHost(emailConfig.getHost());
		//this.setPort(emailConfig.getPort());
		//this.setProtocol(emailConfig.getProtocol());
		this.setUsername(emailConfig.getUsername());
		this.setPassword(emailConfig.getPassword());
		//this.setDefaultEncoding(emailConfig.getDefaultEncoding());
		this.setJavaMailProperties(emailConfig.getMailProperties());
	}
	/**
	 * 发送邮件
	 * @param mailModel
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void sendMail(HMailModel mailModel) throws MessagingException, UnsupportedEncodingException{
		String encoding = mailModel.getCharset().name();
		MimeMessage emailMsg = this.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(emailMsg, true, mailModel.getCharset().name());
		helper.setFrom(mailModel.getFrom());
		helper.setTo(mailModel.getTo());
		helper.setSubject(MimeUtility.encodeText(mailModel.getSubject(), encoding, "B"));
		
		for(HMailModel.Attachment attachment : mailModel.getAttachments()){
			helper.addAttachment(attachment.getAttachName(), attachment.getDataSource());;
		}
		
		helper.setText(mailModel.getContent(), mailModel.isHtml()); 
		this.send(emailMsg);
	}
}
