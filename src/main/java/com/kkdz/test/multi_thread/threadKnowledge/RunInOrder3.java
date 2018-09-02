package com.kkdz.test.multi_thread.threadKnowledge;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 假如有三个线程T1,T2,T3，如何保证T2在T3之前执行完成，T1在T2之前完成？
 */
// 本意是：让t2等待。用t1来唤醒它。
public class RunInOrder3 {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition c2 = lock.newCondition();
		MT1 t1 = new MT1(lock, c2);
		MT2 t2 = new MT2(lock, c2);
		t1.start();
		t2.start();
	}
}

class MT1 extends Thread {
	private Condition c2;
	private Lock lock;

	public MT1(Lock lock, Condition c2) {
		this.c2 = c2;
		this.lock = lock;
	}

	@Override
	public void run() {
//		try {
//			lock.lock();
////			c2.signalAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			lock.unlock();
//		}
		System.out.println(Thread.currentThread().getName() + ":MT1");
		try {
			lock.lock();
			c2.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class MT2 extends Thread {
	private Lock lock;
	private Condition c2;

	public MT2(Lock lock, Condition c2) {
		this.lock = lock;
		this.c2 = c2;
	}

	@Override
	public void run() {
		try {
			lock.lock();
			c2.await();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		System.out.println(Thread.currentThread().getName() + ":MT2");
	}

}
