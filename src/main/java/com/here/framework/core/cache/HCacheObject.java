package com.here.framework.core.cache;

import java.io.Serializable;

/**
 * cache中存取的对象
 * @author koujp
 *
 */
public class HCacheObject implements Serializable{
	private static final long serialVersionUID = -8894847234301709125L;
	private String lang;
	private String type;
	private String content;
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString(){
		return null;
	}
	
}
