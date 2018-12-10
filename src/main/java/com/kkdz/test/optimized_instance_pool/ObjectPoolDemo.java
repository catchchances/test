package com.kkdz.test.optimized_instance_pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ObjectPoolDemo {
	private static int executeTimes = 10;
	private static int maxFactor = 10;
	private static int threadCount = 100;
	private static final int NOTUSE_OBJECTPOOL = 1;
	private static final int USE_OBJECTPOOL = 2;
	private static int runMode = NOTUSE_OBJECTPOOL;
	private static CountDownLatch latch = null;

	public static void main(String[] args) throws Exception {
		Thread.sleep(20000);
		if (args.length == 1)
			runMode = Integer.parseInt(args[0]);
		if (args.length == 2) {
			runMode = Integer.parseInt(args[0]);
			executeTimes = Integer.parseInt(args[1]);
		}
		if (args.length == 3) {
			runMode = Integer.parseInt(args[0]);
			executeTimes = Integer.parseInt(args[1]);
			maxFactor = Integer.parseInt(args[2]);
		}
		if (args.length == 4) {
			runMode = Integer.parseInt(args[0]);
			executeTimes = Integer.parseInt(args[1]);
			maxFactor = Integer.parseInt(args[2]);
			threadCount = Integer.parseInt(args[3]);
		}
		long beginTime = System.currentTimeMillis();
		Task task = new Task();
		for (int i = 0; i < executeTimes; i++) {
			System.out.println("Round: " + (i + 1));
			latch = new CountDownLatch(threadCount);
			for (int j = 0; j < threadCount; j++) {
				new Thread(task).start();
			}
			latch.await();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Execute summary: Round( " + executeTimes + " ) Thread Per Round( 100 ) Object Factor( "
				+ maxFactor + " ) Execute Time( " + (endTime - beginTime) + " ms)");
	}

	static class Task implements Runnable {
		public void run() {
			for (int j = 0; j < maxFactor; j++) {
				if (runMode == USE_OBJECTPOOL) {

					BigObjectPool.getInstance().getBigObject(j);
				} else {
					new BigObject(j);
				}
			}
			latch.countDown();
		}
	}

	static class BigObjectPool {
		private static final BigObjectPool self = new BigObjectPool();
		private final Map<Integer, BigObject> cacheObjects = new HashMap<Integer, BigObject>();

		private BigObjectPool() {
			;
		}

		public static BigObjectPool getInstance() {
			return self;
		}

		public BigObject getBigObject(int factor) {
			if (cacheObjects.containsKey(factor)) {
				return cacheObjects.get(factor);
			} else {

				BigObject object = new BigObject(factor);
				cacheObjects.put(factor, object);
				return object;
			}
		}
	}

	static class BigObject {
		private byte[] bytes = null;

		public BigObject(int factor) {
			bytes = new byte[(factor + 1) * 1024 * 1024];
		}

		public byte[] getBytes() {
			return bytes;
		}
	}
}