package com.kkdz.test.polymorphism;

public class PolymorphismTest {

	public static void main(String[] args) {
		// Employee e = new Employee();
		Manager m = new Manager();
		PolymorphismTest t = new PolymorphismTest();
		m.setSalary(3000);
		int salary = t.viewSalary(m);
		System.out.println("manager's salary is " + salary);

	}

	public int viewSalary(Employee e) {
		return e.getSalary();
	}

}
