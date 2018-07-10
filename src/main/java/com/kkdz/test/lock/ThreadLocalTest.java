package com.kkdz.test.lock;

public class ThreadLocalTest {
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();

	public void set() {
		longLocal.set(Thread.currentThread().getId());
	}

	public long getLong() {
		return longLocal.get();
	}

	public static void main(String[] args) throws InterruptedException {
		final ThreadLocalTest test = new ThreadLocalTest();
		test.set();
		Thread thread1 = new Thread() {
			public void run() {
				test.set();
				System.out.println("线程一:" + test.getLong());
			};
		};
		thread1.start();
		thread1.join();
		System.out.println("main线程:" + test.getLong());
		System.out.println("没有发生值的覆盖，两个线程保存的值是不同的");
	}
}
