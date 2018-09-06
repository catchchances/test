package com.kkdz.test.multi_thread;

public class VolatitleTest {
	
	//这样可以保证多线程操作时，保持isDone随时是最新的。
	private volatile boolean isDone;

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	//当然也可以使用synchronized关键字来约束方法，只是开销大了些。
	private boolean isTrue;

	public synchronized boolean isTrue() {
		return isTrue;
	}

	public synchronized void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
	
	
	
}
