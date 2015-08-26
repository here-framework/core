package com.here.framework.core.config;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.here.framework.core.web.http.HSessionCacheProvider;
import com.here.framework.init.InitRuntimeException;
import com.here.framework.log.HLogger;

/**
 * session配置
 * @author koujp
 *
 */
public class HSessionConfig {
	private int maxAge=0;
	private String providerClazzName;
	
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public String getProviderClazzName() {
		return providerClazzName;
	}
	public void setProviderClazzName(String providerClazzName) {
		this.providerClazzName = providerClazzName;
	}
	@JsonIgnore
	public HSessionCacheProvider getSessionCacheProvider(){
		try {
			HSessionCacheProvider sessionProvider=(HSessionCacheProvider)Class.forName(providerClazzName).newInstance();
			return sessionProvider;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			HLogger.getInstance(this.getClass()).error(e);
		}
		throw new InitRuntimeException(providerClazzName+" class init error");
	}
}
