package com.kkdz.test.multi_thread.threadKnowledge;

/**
 * 假如有三个线程T1,T2,T3，如何保证T2在T3之前执行完成，T1在T2之前完成？
 */
// 以下是错误的写法，会抛异常
// 本意是：让t2等待。用t1来唤醒它。
public class RunInOrder2 {

	public static void main(String[] args) {
		Object o1 = new Object();
		Object o2 = new Object();
		for (int i = 0; i < 100; i++) {
			T1 t1 = new T1(o1, o2);
			T2 t2 = new T2(o2);
			t1.start();
			t2.start();
		}
	}
}

class T1 extends Thread {
	private Object o1;
	private Object o2;

	public T1(Object o1, Object o2) {
		this.o1 = o1;
		this.o2 = o2;
	}

	@Override
	public void run() {

		synchronized (o1) {
			System.out.println(Thread.currentThread().getName() + ":T1");
			// o2.notifyAll();// 在这里会报：java.lang.IllegalMonitorStateException
			// 经过深刻分析，记录下来：
			// 这个地方我通俗的讲是这样的：对于一个线程在这个同步块里，只能拥有一个对象的监视器（锁）。
			// 在这里同步块拥有o1的锁。而运行o2.notifyAll()，就表明线程应该拥有o2的锁。但实际上，并不能。
		}
		// 以下三行代码是解决方法:只是利用别外一个同步块来取得o2的锁。
		synchronized (o2) {
			o2.notify();
		}
	}
}

class T2 extends Thread {
	private Object o2;

	public T2(Object o2) {
		this.o2 = o2;
	}

	@Override
	public void run() {
		synchronized (o2) {
			try {
				o2.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ":T2");
		}

	}
}
