package com.kkdz.test.logger;

import java.util.logging.Logger;

public class DebugTest {

	public static void main(String[] args) {
		DebugTest dt = new DebugTest() {
			@Override
			public void action() {
				super.action();
				Logger.getGlobal().info("xxx");
			}
		};
		dt.action();
	}

	public void action() {
		// do something
		System.out.println("do nothing");
	}

}
