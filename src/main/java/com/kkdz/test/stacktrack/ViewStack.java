package com.kkdz.test.stacktrack;

import java.io.ByteArrayOutputStream;

public class ViewStack {

	public static void main(String[] args) {
		printStack();
	}

	static void printStack() {
		Throwable t = new Throwable();
		t.printStackTrace();

		StackTraceElement[] frames = t.getStackTrace();
		for (StackTraceElement frame : frames) {
			// 可以查看多个栈里的信息
			System.out.println();
		}

	}

}
