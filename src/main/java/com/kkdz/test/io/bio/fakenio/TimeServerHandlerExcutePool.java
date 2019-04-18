package com.kkdz.test.io.bio.fakenio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExcutePool {

	private ExecutorService executorService;

	public TimeServerHandlerExcutePool() {
		super();
	}

	public TimeServerHandlerExcutePool(int maxPoolSize, int queueSize) {
		int coreWorkerSize = Runtime.getRuntime().availableProcessors();
		ArrayBlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<java.lang.Runnable>(queueSize);
		executorService = new ThreadPoolExecutor(coreWorkerSize, maxPoolSize, 120L, TimeUnit.SECONDS, taskQueue);
	}

	public void excute(TimeServerHandler timeServerHandler) {
		executorService.execute(timeServerHandler);
	}

}
