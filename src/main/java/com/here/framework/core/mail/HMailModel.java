package com.here.framework.core.mail;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataSource;

/**
 * 邮件模型
 * @author koujp
 *
 */
public class HMailModel {
	/**
	 * 附件
	 *
	 */
	public static class Attachment{
		private String attachName;
		private DataSource dataSource;
		public Attachment(String attachName,DataSource dataSource){
			this.attachName = attachName;
			this.dataSource = dataSource;
		}
		public String getAttachName() {
			return attachName;
		}
		public DataSource getDataSource() {
			return dataSource;
		}
	}
	
	private String from;
	private String to;
	private String subject;
	private boolean html = true;
	private String content;
	private Charset charset = Charset.forName("utf-8");
	
	private List<Attachment> attachments = new ArrayList<HMailModel.Attachment>();
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public boolean isHtml() {
		return html;
	}
	public void setHtml(boolean html) {
		this.html = html;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Charset getCharset() {
		return charset;
	}
	public void setCharset(Charset charset) {
		this.charset = charset;
	}
	public void addAttachment(Attachment attachment){
		this.attachments.add(attachment);
	}
	public Attachment[] getAttachments(){
		return this.attachments.toArray(new Attachment[0]);
	}
	
}
