package com.kkdz.test.proxy.staticproxy;

import com.kkdz.test.proxy.UserDao;

/**
 * 静态代理，需要目标代理类和被代理类实现同一个接口。
 * 
 * @author bbbbln
 *
 */
public class UserDaoProxy implements UserDao {

	private UserDao target;

	public UserDaoProxy(UserDao target) {
		this.target = target;
	}

	public void save() {

		System.out.println("静态代理开始。。。");
		target.save();
		System.out.println("静态代理结束。");

	}

}
