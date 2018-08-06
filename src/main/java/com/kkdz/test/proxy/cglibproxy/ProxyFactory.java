package com.kkdz.test.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;

public class ProxyFactory {

	/**
	 * Enhancer类和MethodInterceptor接口是整个包的核心所在！ </br>
	 * Enhancer就是“增强”的意思嘛！主要用于生成动态子类以启用方法拦截,什么意思？这样子讲！cglib类代理的基本思想就是对被代理类生成一个新的类（proxy），该类是继承自被代理类的，并对被代理类方法执行前后执行一些操作，这些操作的通常就是一些回调操作，可以是MethodInterceptor，LazyLoader,CallbackFilter,其中MethodInterceptor是最常用的。
	 * </br>
	 * 所有被Enhancer关联的对象默认都是实现Factory接口的，该接口提供了一组可以设置对象回调类型的方法，你可以通过调用setUseFactory(false)取消此特性！
	 * </br>
	 * 需要注意的是，cglib是无法代理final修饰的方法的，因为这是java语言规范决定的！ </br>
	 * </br>
	 * MethodInterceptor是一个提供环绕通知的通用回调接口！Aop中有这样的术语，那就是前置通知，后置通知，环绕通知，非常好理解，就是一个在方法执行前的通知，一个方法执行后的通知，另外一个就是方法执行前后都通知。
	 * </br>
	 * 该接口只有一个intercept()方法： </br>
	 * public Object intercept(Object obj, java.lang.reflect.Method method,
	 * Object[] args, MethodProxy proxy) throws Throwable; </br>
	 * 所有对被代理类方法的执行都会跳转到这个方法上面来，而原来的方法则通过反射得到的Method对象或者MethodProxy对象进行调用。
	 * 
	 * 
	 * @param target
	 * @return
	 */
	public static Object getProxy(Object target) {

		Enhancer enhancer = new Enhancer();

		enhancer.setSuperclass(target.getClass());

		enhancer.setCallback(new MyMethodInterceptor());

		return enhancer.create();

	}

}
