package com.kkdz.test.multi_thread.thread_or_runnable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrRunnable {
	// 两种方式实现数据共享,反驳网上大量的错误说法。
	public static void main(String[] args) {
		// 用继承Thread的方式实现线程共享数据
		{
			MyJob0 myJob = new MyJob0();
			new Thread(myJob).start();
			new Thread(myJob).start();
			new Thread(myJob).start();
		}
		// 用实现Runnable的方式实现线程共享数据
		{
			MyJob1 myJob = new MyJob1();
			new Thread(myJob).start();
			new Thread(myJob).start();
			new Thread(myJob).start();
		}
	}
}

// 虽然这种方式可以实现多线程，但它有一个缺点就是：如果MyJob0要继承其它类的话，就没法玩了。所以实现上会用MyJob1的方式，implements来实现
class MyJob0 extends Thread {
	private int shareInt = 5;
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		lock.lock();
		shareInt--;
		System.out.println("from thread:" + shareInt);
		lock.unlock();
	}
}

class MyJob1 implements Runnable {
	private int shareInt = 5;
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		lock.lock();
		shareInt--;
		System.out.println("from runnable:" + shareInt);
		lock.unlock();
	}
}
