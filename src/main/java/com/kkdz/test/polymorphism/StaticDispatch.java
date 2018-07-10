package com.kkdz.test.polymorphism;

public class StaticDispatch {
	static class Human {
	}

	static class Man extends Human {
	}

	static class Women extends Human {
	}

	public void sayHello(Human guy) {
		System.out.println("hello, guy!");
	}

	public void sayHello(Man guy) {
		System.out.println("hello, man!");
	}

	public void sayHello(Women guy) {
		System.out.println("hello, women!");
	}

	public static void main(String[] args) {
		Human man = new Man();
		Human women = new Women();
		StaticDispatch sd = new StaticDispatch();
		sd.sayHello(man);
		sd.sayHello(women);
	}

}
