package com.kkdz.test.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {

	private ServerSocketChannel servChannel;

	private volatile boolean stop;

	private Selector selector;

	public MultiplexerTimeServer(int port) {
		try {
			selector = Selector.open();
			servChannel = ServerSocketChannel.open();
			servChannel.configureBlocking(false);
			servChannel.socket().bind(new InetSocketAddress(port), 1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("the time server is start in port:" + port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop() {
		this.stop = true;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> keys = selector.keys();
				for (SelectionKey key : keys) {
					try {
						HandleInput(key);
					} catch (Exception e) {
						key.cancel();
						if (key.channel() != null) {
							key.channel().close();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (selector != null) {
				try {
					selector.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void HandleInput(SelectionKey key) throws IOException {
		if (!key.isValid()) {
			return;
		}
		if (key.isAcceptable()) {
			// accept the new connection
			ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
			SocketChannel sc = ssChannel.accept();
			sc.configureBlocking(false);
			ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		} else if (key.isReadable()) {
			
		} else {

		}
	}

}
