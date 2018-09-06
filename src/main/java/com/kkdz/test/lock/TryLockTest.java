package com.kkdz.test.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {

}

//试着获取锁
class TryThreadLock extends Thread {

	Lock lock = new ReentrantLock();

	@Override
	public void run() {
		try {
			// 尝试获取锁
			if (lock.tryLock(100, TimeUnit.HOURS)) {

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

}