package com.kkdz.test.keyword;

public class FinalTest {

	private final int a;

	public FinalTest() {
		this.a = 1;
	}

	private int blockVariable;

	{
		this.blockVariable = 1;
	}

	public static void main(String[] args) {
		Employee a = new Employee(4);
		Employee b = new Employee(5);
		System.out.println("employee a salary:" + a.getSalary());
		System.out.println("employee b salary:" + b.getSalary());
		swap(a, b);
		System.out.println("employee a salary:" + a.getSalary());
		System.out.println("employee b salary:" + b.getSalary());
		setSalary(a);
		System.out.println("employee a salary:" + a.getSalary());
	}

	public static void swap(Employee x, Employee y) {
		Employee temp = x;
		x = y;
		y = temp;
	}

	public static void setSalary(Employee e) {
		e.setSalary(500);
	}

}

class Employee {
	private static String name = setName();

	private static String setName() {
		return "Divid";
	}

	public Employee(int s) {
		this.salary = s;
		setName();
	}

	private int salary;

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}