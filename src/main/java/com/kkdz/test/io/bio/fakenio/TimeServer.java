package com.kkdz.test.io.bio.fakenio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
	
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
			TimeServerHandlerExcutePool singleExcutor = new TimeServerHandlerExcutePool(50, 10000);// I/O任务线程池
			while(true) {
				socket = ss.accept();
				singleExcutor.excute(new TimeServerHandler(socket));
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
