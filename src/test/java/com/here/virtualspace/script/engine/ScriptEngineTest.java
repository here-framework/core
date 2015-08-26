package com.here.virtualspace.script.engine;

import javax.script.Compilable;

import com.here.framework.file.FileUtil;
import com.here.framework.script.engine.HScriptEngine;

public class ScriptEngineTest {
	public static void main(String[] args){
		String script=FileUtil.contentFromClasspath("env/config/env.js");
		HScriptEngine engine=HScriptEngine.getInstance();
		engine.eval(script);
		String json=engine.getJson("ServiceConfig");
		System.out.println(json);
	}
}
