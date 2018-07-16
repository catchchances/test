package com.kkdz.test.toString;

public class TestToString {
	
	public static void main(String[] args) {
		TestToString t1 = new TestToString();
		System.out.println(t1.toString());
	}

	//产生死循环
	@Override
	public String toString() {
		return "TestToString" + this;
	}
}
