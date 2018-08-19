package com.kkdz.test.multi_thread.producer_consumer;

/**
 * 生产者消费者，利用多线程加同步方法
 * 
 * @author Muzhi
 *
 */
public class ProducerAndConsumer {
	// 苹果
	private static class Apple {
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public void clearValue() {
			this.value = "";
		}

	}

	public static void main(String[] args) {
		Apple apple = new Apple();
		Object lock = new Object();
		Producer producer = new Producer(apple, lock);
		Consumer consumer = new Consumer(apple, lock);
		// 这里我测试了一下，把apple当成锁对象，也是可以的。
		// Producer producer = new Producer(apple, apple);
		// Consumer consumer = new Consumer(apple, apple);
		new Thread(producer).start();
		new Thread(consumer).start();
	}

	private static class Producer implements Runnable {
		Apple apple;
		Object lock;

		public Producer(Apple apple, Object lock) {
			this.apple = apple;
			this.lock = lock;
		}

		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					if (!"".equals(apple.getValue())) {
						try {
							lock.wait();// 有产品的时候，放弃锁，让出资源。与消费者一起竞争资源。有一定的概率让消费者拿到锁对象。
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					apple.setValue("xxx");
					System.out.println("我生产了一个苹果，编号" + apple.getValue());
					lock.notifyAll();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	private static class Consumer implements Runnable {
		Apple apple;
		Object lock;

		public Consumer(Apple apple, Object lock) {
			this.apple = apple;
			this.lock = lock;
		}

		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					if ("".equals(apple.getValue())) {
						try {
							lock.wait(); // 没有产品的时候，放弃锁，让出资源。和生产者一起再次竞争。有一定概率让生产者继续生产。
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("我吃了一个苹果，编号" + apple.getValue());
					apple.clearValue();
					lock.notifyAll();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}
	}

}
