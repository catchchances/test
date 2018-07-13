package com.kkdz.test.changeByteCode;

public class VerifierTest {

	static int fun() {
		int m;
		int n;
		m = 1;
		n = 2;
		int r = m + n;
		return r;
	}

	public static void main(String[] args) {
		System.out.println(fun());
	}

}
