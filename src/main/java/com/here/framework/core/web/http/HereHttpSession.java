package com.here.framework.core.web.http;

import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.here.framework.config.ConfigFactory;
import com.here.framework.core.config.HSessionConfig;
/**
 * HttpSession 实现
 * @author koujp
 *
 */
public class HereHttpSession implements HttpSession {
	public static final String SESSION_IDENTIFY="HSessionId";
	private long creationTime;
	private String id;
	private long lastAccessedTime;
	private int maxInactiveInterval;
	private boolean blnNew;
	private transient ServletContext servletContext;
	private transient Cookie cookie;
	
	private transient HttpSession localSession;
	
	private HSessionCacheProvider sessionCache=null;
	public static HereHttpSession create(Cookie cookie){
		HereHttpSession session=create(cookie.getValue());
		session.cookie=cookie;
		session.sessionCache.setMaxage(cookie.getMaxAge());
		session.sessionCache.updateMaxage();
		return session;
	}
	private static HereHttpSession create(String id){
		
		
		long createTime=System.currentTimeMillis();
		HereHttpSession session=new HereHttpSession();
		session.setId(id);
		session.setCreationTime(createTime);
		session.setLastAccessedTime(createTime);
		
		session.sessionCache=ConfigFactory.getConfig(HSessionConfig.class).getSessionCacheProvider();
		session.sessionCache.setSessionId(id);
		
		return session;
	}
	@Override
	public long getCreationTime() {
		return creationTime;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public long getLastAccessedTime() {
		return lastAccessedTime;
	}

	@Override
	public ServletContext getServletContext() {
		return servletContext;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	}
	@Override
	public void setMaxInactiveInterval(int interval) {
		maxInactiveInterval=interval;
	}

	@Override
	public int getMaxInactiveInterval() {
		return maxInactiveInterval;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return localSession.getSessionContext();
	}

	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return sessionCache.getAttribute(name);
	}

	@Override
	public Object getValue(String name) {
		return getAttribute(name);
	}
	private class EnumerationImpl<T> implements Enumeration<T>{
		Iterator<T> iterator=null;
		public EnumerationImpl(Iterator<T> iterator){
			this.iterator=iterator;
		}
		@Override
		public boolean hasMoreElements() {
			return iterator.hasNext();
		}
		@Override
		public T nextElement() {
			return iterator.next();
		}
	}
	@Override
	public Enumeration<String> getAttributeNames() {
		
		Iterator<String> iterator=sessionCache.attributeNames().iterator();
		 return new EnumerationImpl<String>(iterator);
	}

	@Override
	public String[] getValueNames() {
		return sessionCache.attributeNames().toArray(new String[0]);
	}

	@Override
	public void setAttribute(String name, Object value) {
		sessionCache.setAttribute(name, value);

	}

	@Override
	public void putValue(String name, Object value) {
		setAttribute(name,value);
	}

	@Override
	public void removeAttribute(String name) {
		sessionCache.removeAttribute(name);
	}

	@Override
	public void removeValue(String name) {
		removeAttribute(name);

	}

	@Override
	public void invalidate() {
		sessionCache.invalidate();

	}

	@Override
	public boolean isNew() {
		return blnNew;
	}
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setLastAccessedTime(long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
	public Cookie getCookie() {
		return cookie;
	}
	public HttpSession getLocalSession() {
		return localSession;
	}
	public void setLocalSession(HttpSession localSession) {
		this.localSession = localSession;
	}

}
