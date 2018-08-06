package com.kkdz.test.proxy.cglibproxy;

public class Test {

	public static void main(String[] args) {
		UserDaoImplWithOutInterface userDao = new UserDaoImplWithOutInterface();
		UserDaoImplWithOutInterface proxy = (UserDaoImplWithOutInterface) ProxyFactory.getProxy(userDao);
		proxy.save();
	}

}
