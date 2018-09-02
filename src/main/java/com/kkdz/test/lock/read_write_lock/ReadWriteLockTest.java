package com.kkdz.test.lock.read_write_lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 本例是用来表明ReentrantReadWriteLock（可重入读写锁）的互斥关系
 * 结果表明：读/写互斥、写/写互斥；读/读不互斥。
 * 也就是说
 * 一个线程写的时候，其它线程既不能读也不能写；
 * 一个线程读的时候，其它线程可以读，但不能写。
 * @author ZhangLong
 *
 */
public class ReadWriteLockTest {
	public static void main(String[] args) {
		ReadWriteLock lock = new ReentrantReadWriteLock();
		for (int i = 0; i < 10; i++) {
			ReadThread rt = new ReadThread(lock);
			WriteThread wt = new WriteThread(lock);
			rt.start();
			wt.start();
		}
	}
}

class ReadThread extends Thread {
	private ReadWriteLock lock;

	public ReadThread(ReadWriteLock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			lock.readLock().lock();
			System.out.println("read start@" + Thread.currentThread().getName());
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
			System.out.println("read end@" + Thread.currentThread().getName());
		}
	}
}

class WriteThread extends Thread {
	private ReadWriteLock lock;

	public WriteThread(ReadWriteLock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			lock.writeLock().lock();
			System.out.println("write start@" + Thread.currentThread().getName());
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
			System.out.println("write end@" + Thread.currentThread().getName());
		}
	}
}
