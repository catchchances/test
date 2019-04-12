package com.kkdz.test.io.aserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AServerHandler implements Runnable {

	private Socket socket = null;
	
	public AServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
			String resp = null;
			String body = null;
			while(true) {
				body = in.readLine();
				if(null == body)
				{
					break;
				}
				System.out.println("this time server receive order:"+body);
			}
			resp = "{\"state\":\"0\"}";
			out.println(resp);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(null != in) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in = null;
			}
			if(null != out) {
				out.close();
				out = null;
			}
			if(null != socket) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				socket = null;
			}
		}
	}


}
