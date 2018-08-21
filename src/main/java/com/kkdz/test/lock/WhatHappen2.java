package com.kkdz.test.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WhatHappen2 {

	public static void main(String[] args) {

		WhatHappen2 wh = new WhatHappen2();
		{
			Object obj = new Object();
			wh.whenWait(obj);// 这个方法内的线程会wait状态，不再执行。
			Runnable rbWithSynchronized = new Runnable() {// 用这个线程去"救"它。
				@Override
				public void run() {
					synchronized (obj) {
						obj.notifyAll();
					}
				}
			};
			new Thread(rbWithSynchronized).start();
			new Thread(rbWithSynchronized).start();
		}
//		{
//			Lock lock = new ReentrantLock();
//			Condition condition = lock.newCondition();
//			wh.whenAwait(lock, condition);// 这个方法内的线程会await状态，不再执行。
//			Runnable rbWithCondition = new Runnable() {// 用这个线程去"救"它。
//
//				@Override
//				public void run() {
//					try {
//						lock.lock();
//						condition.signalAll();
//					} finally {
//						lock.unlock();
//					}
//				}
//			};
//			new Thread(rbWithCondition).start();
//			new Thread(rbWithCondition).start();
//
//		}
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
		});
		t1.start();
	}

	/**
	 * 该线程会一直阻塞在condition.await()位置，不再继续往下执行。除非另外一个线程调用condition.signal(all)。
	 */
	private void whenAwait(Lock lock, Condition condition) {
		Thread t = new Thread(new Runnable() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				try {
					lock.lock();
					System.out.printf("I am %s running between lock block before condition.await() \n",
							Thread.currentThread().getName());
					condition.await();
					System.out.printf("I am %s after condition.await() \n", Thread.currentThread().getName());
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
