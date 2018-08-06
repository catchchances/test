package com.kkdz.test.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * 采用这种动态代理的方式，需要被代理对象实现接口。
 * @author bbbbln
 *
 */
public class ProxyFactory {

	
	public static Object getProxy(Object target) {
		ClassLoader classLoader = target.getClass().getClassLoader();
		// 可以对该对象的特定接口的方法进行增强
		Class<?>[] interfaces = target.getClass().getInterfaces();
		return Proxy.newProxyInstance(classLoader, interfaces, new MyInvocationHandler(target));
	}

}
