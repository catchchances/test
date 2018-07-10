package com.kkdz.test.keyword;

/**
 * 如果抽象类定义了构造器，子类必须覆写构造方法。
 * 
 * @author muzhi
 *
 */
public class AbstractTest {

	public static void main(String[] args) {
		Person person = new Man("ddd");
		System.out.println(person.getName());
	}

}

abstract class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return "Marry";
	};

	public abstract String getSex();
}

class Man extends Person {
	

	public Man(String name) {
		super(name);
	}

	@Override
	public String getSex() {
		return null;
	}

}
