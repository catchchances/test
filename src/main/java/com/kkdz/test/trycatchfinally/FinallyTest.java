package com.kkdz.test.trycatchfinally;

public class FinallyTest {

	public static void main(String[] args) {
		int result = getResult();
		System.out.println(result);
		getResultWithException();
	}

	/**
	 * 可以明显发现代码的执行顺序：finally里的return代码先执行。
	 * 
	 * @return
	 */
	public static int getResult() {
		try {
			return 0;
		} finally {
			return 1;
		}
	}

	/**
	 * 同理finally里的会先执行
	 * 
	 * @return
	 */
	public static int getResultWithException() {
		try {
			throw new RuntimeException("try block exception");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			throw new RuntimeException("finally block exception");
		}
	}

}
