package com.kkdz.test.io.aio;

public class TimeServer {

	public static void main(String[] args) {
		int port = 8888;
		if (null != args && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (Exception e) {
				// do nothing
			}
		}
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
		new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();


	}

}
