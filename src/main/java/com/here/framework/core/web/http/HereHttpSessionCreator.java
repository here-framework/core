package com.here.framework.core.web.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.here.framework.config.ConfigFactory;
import com.here.framework.core.config.HSessionConfig;
import com.here.framework.dao.UIDGenerator;


/**
 * HereHttpSession 创建者
 * @author koujp
 *
 */
public class HereHttpSessionCreator {
	private HttpServletRequest request;
	public HereHttpSessionCreator(HttpServletRequest request){
		this.request=request;
	}
	private static String generateSessionId(){
		String id=UIDGenerator.getInstance().generate();
		return id;
	}
	private Cookie getCookie(String name){
		Cookie cookie=null;
		Cookie[] cookies=request.getCookies();
		if(null==name || null==cookies){
			return cookie;
		}
		for(Cookie k:cookies){
			if(name.equals(k.getName())){
				cookie=k;
				break;
			}
		}
		return cookie;
	}
	private int getCookieMaxAge(){
		return ConfigFactory.getConfig(HSessionConfig.class).getMaxAge();
	}
	private Cookie getSessionCookie(boolean create){
		Cookie cookie=getCookie(HereHttpSession.SESSION_IDENTIFY);
		if(create && null==cookie){
			cookie=new Cookie(HereHttpSession.SESSION_IDENTIFY,generateSessionId());
		}
		if(null!=cookie){
			cookie.setPath("/");
			cookie.setMaxAge(getCookieMaxAge());
		}
		
		return cookie;
	}
	public HereHttpSession getSession(boolean create){
		HereHttpSession session=getSessionFromCache(create);
		if(null!=session){
			session.setServletContext(request.getServletContext());
		}
		
		return session;
	}
	private HereHttpSession getSessionFromCache(boolean create){
		Cookie sessionCookie=getSessionCookie(create);
		HereHttpSession session=null;
		if(null==sessionCookie){
			return null;
		}else{
			session=HereHttpSession.create(sessionCookie);
		}
		
		session.setLastAccessedTime(System.currentTimeMillis());
		
		return session;
	}
}
