package com.kkdz.test.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleLoggerTest {

	public static void main(String[] args) {
		simpleLogger();
	}

	public static void simpleLogger() {
		Logger.getGlobal().info("File>Open menu item selecte");
		Logger.getGlobal().setLevel(Level.OFF);
		Logger.getGlobal().info("nothing show");
	}

}
