package com.kkdz.test.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	// 以下是模板方法,对被代理对象的方法进行增强
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("动态代理开始。。。");
		Object result = method.invoke(target, args);
		System.out.println("动态代码结束");
		return result;
	}

	// // 获取代理对象
	// public Object getProxy() {
	// ClassLoader classLoader = target.getClass().getClassLoader();
	// Class<?>[] interfaces = target.getClass().getInterfaces();
	// return Proxy.newProxyInstance(classLoader, interfaces, this);
	// }

}
