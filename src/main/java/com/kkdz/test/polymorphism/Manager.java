package com.kkdz.test.polymorphism;

public class Manager extends Employee {

	@Override
	public int getSalary() {
		return super.getSalary() + 5000;
	}

}
