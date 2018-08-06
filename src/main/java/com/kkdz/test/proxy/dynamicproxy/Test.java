package com.kkdz.test.proxy.dynamicproxy;

import com.kkdz.test.proxy.UserDao;
import com.kkdz.test.proxy.UserDaoImpl;

public class Test {
	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		UserDao proxy = (UserDao) ProxyFactory.getProxy(userDao);
		proxy.save();
	}

}
