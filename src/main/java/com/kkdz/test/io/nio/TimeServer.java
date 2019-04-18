package com.kkdz.test.io.nio;

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
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();

		// 1. open server socket channel (acceptorSrv)
		// 2. bind port, set blocking or not
		// 3. create reactor thread and selector
		// 4. bind acceptorSrv to reactor, listening to accept event
		// 5. repeat: selector selects the ready selectionKey.

	}

}
