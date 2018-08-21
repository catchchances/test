package com.kkdz.test.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WhatHappen {

	public static void main(String[] args) {

		WhatHappen wh = new WhatHappen();
		{
			Object obj = new Object();
			wh.whenWait(obj);// 这个方法内的线程会wait状态，不再执行。
			new Thread(new Runnable() {// 用这个线程去"救"它。
				@Override
				public void run() {
					synchronized (obj) {
						obj.notifyAll();
					}
				}
			}).start();
		}
		{
			Lock lock = new ReentrantLock();
			Condition condition = lock.newCondition();
			wh.whenAwait(lock, condition);// 这个方法内的线程会await状态，不再执行。
			new Thread(new Runnable() {// 用这个线程去"救"它。

				@Override
				public void run() {
					try {
						lock.lock();
						condition.signalAll();
					} finally {
						lock.unlock();
					}
				}
			}).start();

		}
	}

	/**
	 * 该线程会一直阻塞在obj.await()位置，不再继续往下执行。除非另外线程调用obj.notify(all)。
	 * 
	 * @param obj
	 */
	private void whenWait(Object obj) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {

				synchronized (obj) {
					System.out.println("I am running between synchronized block");
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("I am running after obj.wait()");
				}
			}
		});
		t1.start();
	}

	/**
	 * 该线程会一直阻塞在condition.await()位置，不再继续往下执行。除非另外一个线程调用condition.signal(all)。
	 */
	private void whenAwait(Lock lock, Condition condition) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					lock.lock();
					System.out.println("I am running between lock");
					condition.await();
					System.out.println("I am after condition.await()");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		});
		t.start();
	}

}
