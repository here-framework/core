package com.here.framework.core.web.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
/**
 * http 请求和响应包装
 * @author koujp
 *
 */
public class HereHttpWrapper {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private HereHttpServletRequest wrapperRequest;
	private HereHttpServletResponse wrapperResponse;
	public HereHttpWrapper(HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;
	}
	private class HereHttpServletRequest extends HttpServletRequestWrapper{
		private HereHttpSession hsession=null;
		public HereHttpServletRequest(){
			super(request);
			
		}
		@Override
		public HttpSession getSession(){
			 return getSession(true);
		}
		@Override
		public HttpSession getSession(boolean create){
			if(null==hsession){
				HereHttpSessionCreator sessionCreator =new HereHttpSessionCreator(request);
				hsession=sessionCreator.getSession(create);
			}
			Cookie cookie=null;
			if(null!=hsession){
				cookie=hsession.getCookie();
			}
			
			if(null!=cookie){
				response.addCookie(hsession.getCookie());
			}
			return hsession;
		}
	}
	private class HereHttpServletResponse extends HttpServletResponseWrapper{
		public HereHttpServletResponse(){
			super(response);
		}
	}
	public HttpServletRequest getHereHttpServletRequest(){
		if(null==wrapperRequest){
			wrapperRequest=new HereHttpServletRequest();
		}
		return wrapperRequest;
	}
	public HereHttpServletResponse getHereHttpServletResponse(){
		if(null==wrapperResponse){
			wrapperResponse=new HereHttpServletResponse();
		}
		return wrapperResponse;
	}
}
