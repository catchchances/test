package com.kkdz.test.keyword;

public class EnumTest {
	public static void main(String[] args) {
		System.out.println(Size.SMALL.hashCode());//默认的实例
		System.out.println(Size.SMALL.toString());
		Size size = Enum.valueOf(Size.class, "SMALL");
		System.out.println(size.hashCode());//转化后的实例
		//结论，这两者是一样的，同一个对象
	}
}

enum Size{
	SMALL,MEDIUM,LARGE,EXTRA_LARGE
}
