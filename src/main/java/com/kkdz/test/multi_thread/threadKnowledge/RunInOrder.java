package com.kkdz.test.multi_thread.threadKnowledge;

/**
 * 假如有三个线程T1,T2,T3，如何保证T2在T3之前执行完成，T1在T2之前完成？
 * 
 */
public class RunInOrder {

	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2(t1);
		Thread3 t3 = new Thread3(t2);
		t1.start();
		t2.start();
		t3.start();
	}
}

class Thread1 extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}

class Thread2 extends Thread {
	private Thread1 t1;

	public Thread2(Thread1 t1) {
		this.t1 = t1;
	}

	@Override
	public void run() {
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
	}
}

class Thread3 extends Thread {
	private Thread2 t2;

	public Thread3(Thread2 t2) {
		this.t2 = t2;
	}

	@Override
	public void run() {
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
	}
}
