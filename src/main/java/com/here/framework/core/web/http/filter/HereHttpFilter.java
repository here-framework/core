package com.here.framework.core.web.http.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.here.framework.core.web.http.HereHttpWrapper;
/**
 * http  过滤器
 * @author koujp
 *
 */
public class HereHttpFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			chain.doFilter(request, response);
		}else{
			HereHttpWrapper httpWrapper=new HereHttpWrapper((HttpServletRequest)request, (HttpServletResponse)response);
			chain.doFilter(httpWrapper.getHereHttpServletRequest(), httpWrapper.getHereHttpServletResponse());
		}
	}

	@Override
	public void destroy() {

	}

}
