package com.here.framework.core.template;


import java.net.URL;

import freemarker.cache.URLTemplateLoader;
/**
 * email模板文件加载
 * @author koujp
 *
 */
public class HTemplateLoader extends URLTemplateLoader {

	 public HTemplateLoader(ClassLoader loader, String path){
	        setFields(loader, path);
	 }
    protected URL getURL(String name){
        return loader.getResource(path + name);
    }
    private void setFields(ClassLoader loader, String path){
	    if(loader == null)
	        throw new IllegalArgumentException("loaderClass == null");
	    if(path == null){
	        throw new IllegalArgumentException("path == null");
	    } else{
	        this.loader = loader;
	        this.path = canonicalizePrefix(path);
	        return;
	    }
    }
    private ClassLoader loader;
    private String path;
}

