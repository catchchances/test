package com.kkdz.test.multi_thread.fair_or_not;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FairOrNot {

	public static void main(String[] args) {

		// 公平锁
		ReentrantLockSub fairLock = new ReentrantLockSub(true);
		// 非公平锁
		// ReentrantLockSub unfairLock = new ReentrantLockSub(false);
		for (int i = 0; i < 10; i++) {
			// 在这里，锁只有一个，所以无论new多少个job，只会传入同一个锁，只能同一时间有一个线程获得锁并lock
			new Thread(new Job(fairLock), "" + i).start();
		}

	}

	// 若不加static，刚在main方法里不能直接调用非静态方法。而把这个内部类也算一个方法。所以用static修饰。
	private static class Job extends Thread {
		private ReentrantLockSub lock;

		public Job(ReentrantLockSub lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread());
				System.out.println("waitting threads:" + lock.getQueuedThreads());
			} finally {
				lock.unlock();
			}
		}
	}

	// 继承Reentrant，为了扩大访问和重写getQueuedThreads方法。查看等待队列。
	private static class ReentrantLockSub extends ReentrantLock {
		private static final long serialVersionUID = 1L;

		public ReentrantLockSub(boolean fair) {
			super(fair);
		}

		@Override
		public Collection<Thread> getQueuedThreads() {

			Collection<Thread> queuedThreads = super.getQueuedThreads();
			List<Thread> list = new ArrayList<Thread>(queuedThreads);
			Collections.reverse(list);
			return list;
		}
	}

}
