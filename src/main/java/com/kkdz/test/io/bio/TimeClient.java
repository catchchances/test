package com.kkdz.test.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
	public static void main(String[] args) throws IOException {

		int port = 8888;
		try {
			if (null != args && args.length > 0) {
				port = Integer.valueOf(args[0]);

			}
		} catch (NumberFormatException e) {
			// use default port
		}
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("QUERY TIME ORDER");
			System.out.println("send order to server successed");
			String resp = in.readLine();
			System.out.println("Now is "+resp);
		}catch(Exception e) {
			
		}finally {
			if(out != null) {
				out.close();
			}
			if(in != null) {
				in.close();
			}
			if(socket != null) {
				socket.close();
			}
			out = null;
			in = null;
			socket = null;
		}
	}
}
