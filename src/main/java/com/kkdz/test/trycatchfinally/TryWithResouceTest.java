package com.kkdz.test.trycatchfinally;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TryWithResouceTest {

	public static void main(String[] args) {
		try {
			getResouce();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * try with resource
	 * @throws Exception
	 */
	static void getResouce() throws Exception {
		File file = new File("c");
		try (FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis)) {
			System.out.println("try with resource");

		}
	}

}
