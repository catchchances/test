package com.kkdz.test.proxy.staticproxy;

import com.kkdz.test.proxy.UserDao;
import com.kkdz.test.proxy.UserDaoImpl;

public class Test {
	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		UserDaoProxy proxy = new UserDaoProxy(userDao);
		proxy.save();
	}

}
