package com.kkdz.test.proxy.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {
	/**
	 * 模板方法
	 */
	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("CGLIB代理开始。。。");
		Object result = proxy.invokeSuper(target, args);
		System.out.println("CGLIB代理结束。");
		return result;
	}

}
