package com.kkdz.test.multi_thread.sync;

public class UnsyncBankTest {

	public static void main(String[] args) {
		int nAcounts = 100;
		double initialBalance = 1000;
		Bank b = new Bank(nAcounts, initialBalance);
		for (int i = 0; i < nAcounts; i++) {
			TransferRunnable r = new TransferRunnable(b, i, initialBalance);
			Thread t = new Thread(r);
			t.start();
		}
	}

}
