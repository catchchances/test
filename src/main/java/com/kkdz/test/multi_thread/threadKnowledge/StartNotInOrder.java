package com.kkdz.test.multi_thread.threadKnowledge;

public class StartNotInOrder {

	/**
	 * 以下线程并不以start的顺序执行。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread t0 = new MyThread(0);
		MyThread t1 = new MyThread(1);
		MyThread t2 = new MyThread(2);
		MyThread t3 = new MyThread(3);
		MyThread t4 = new MyThread(4);
		MyThread t5 = new MyThread(5);
		MyThread t6 = new MyThread(6);
		MyThread t7 = new MyThread(7);
		MyThread t8 = new MyThread(8);
		MyThread t9 = new MyThread(9);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
	}

	static class MyThread extends Thread {
		private int i;

		public MyThread(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			super.run();
			System.out.println(i);
		}
	}
}
