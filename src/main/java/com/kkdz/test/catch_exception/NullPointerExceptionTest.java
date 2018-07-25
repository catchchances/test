package com.kkdz.test.catch_exception;

public class NullPointerExceptionTest {

	public static void main(String[] args) {
		try {
			throw new NullPointerException("xxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
