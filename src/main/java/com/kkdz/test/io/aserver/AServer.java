package com.kkdz.test.io.aserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AServer {
	
	public static void main(String[] args) throws IOException {

		int port = 8888;
		try {
		if(null != args && args.length > 0) {
			port = Integer.valueOf(args[0]);

		}
		}catch(NumberFormatException e) {
			//use default port
		}
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			System.out.println("Socket server is start in port:"+ port);
			Socket socket = null;
			while(true) {
				socket = ss.accept();
				new Thread(new AServerHandler(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			//error
		}finally {
			if(null != ss) {
				System.out.println("the time server close");
				ss.close();
				ss = null;
			}
		}


	}

}
