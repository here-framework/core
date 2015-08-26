package com.here.framework.core.web.http;

import java.io.Serializable;
import java.util.List;
/**
 * session存取与读写提供
 * @author koujp
 *
 */
public interface HSessionCacheProvider extends Serializable{
	public String getSessionId();
	public void setSessionId(String sessionId);
	public long getMaxage();
	/**
	 * 设置有效时间seconds
	 * @param maxage
	 */
	public void setMaxage(int maxage);
	public void updateMaxage();
	
	
	public Object setAttribute(String name,Object value);
	public Object getAttribute(String name);
	
	public Object removeAttribute(String name);
	public void invalidate();
	
	public List<String> attributeNames();
}
