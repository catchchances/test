package com.kkdz.test.lock;

public class WhatHappen2 {

	// 结果表明：每个线程都会进入锁，重新开始执行代码。
	public static void main(String[] args) {

		WaitResult wr = new WaitResult();
		for (int i = 0; i < 10; i++) {
			new Thread(wr).start();

		}
	}

}

class WaitResult extends Thread {
	private Object obj = new Object();

	@Override
	public void run() {

		// 因为obj会不断的让线程添加到等待队列里。所以下一个线程如果接着走while处的代码，就不会输出before...语句。
		// 如果，新的线程重新获取锁，从头开始执行，就会输出大量的before...代码
		// 结果表明：每个线程都会进入锁，重新开始执行代码。
		synchronized (obj) {
			System.out.printf("I am %s and running between synchronized block before obj.wait() \n",
					Thread.currentThread().getName());
			try {
				while (true) {
					obj.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("I am %s and running after obj.wait() \n", Thread.currentThread().getName());
		}
	}
}
