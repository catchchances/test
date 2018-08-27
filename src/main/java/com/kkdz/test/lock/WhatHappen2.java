package com.kkdz.test.lock;

public class WhatHappen2 {

	//不是一个好的例子
	public static void main(String[] args) {

		Object obj = new Object();
		WaitAction wa1 = new WaitAction(obj);
		WaitAction wa2 = new WaitAction(obj);
		NotifyAction na1 = new NotifyAction(obj);
		NotifyAction na2 = new NotifyAction(obj);
		wa1.start();
		wa2.start();
		na1.start();
		na2.start();
	}

}

class WaitAction extends Thread {
	private Object obj;

	public WaitAction(Object obj) {
		this.obj = obj;
	}

	@Override
	public void run() {

		synchronized (obj) {
			System.out.printf("I am %s and running between synchronized block before obj.wait() \n",
					Thread.currentThread().getName());
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("I am %s and running after obj.wait() \n", Thread.currentThread().getName());
		}
	}

}

class NotifyAction extends Thread {
	private Object obj;

	public NotifyAction(Object obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		synchronized (obj) {
			System.out.printf("I am %s and before obj.notifyAll() \n", Thread.currentThread().getName());
			obj.notifyAll();
			System.out.printf("I am %s and after obj.notifyAll() \n", Thread.currentThread().getName());
		}
	}

}
