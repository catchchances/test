package com.kkdz.test.multi_thread.unsync;

public class TransferRunnable implements Runnable {

	private Bank bank;

	private int fromAccount;

	private double maxAmount;

	private int DELAY = 10;

	public TransferRunnable(Bank bank, int from, double max) {
		this.bank = bank;
		this.fromAccount = from;
		this.maxAmount = max;
	}

	@Override
	public void run() {
		try {
			while (true) {
				int toAmount = (int) (bank.size() * Math.random());
				double amount = this.maxAmount * Math.random();
				bank.transfer(this.fromAccount, toAmount, amount);
				Thread.sleep(this.DELAY);
			}
		} catch (InterruptedException e) {
		}
	}

}
