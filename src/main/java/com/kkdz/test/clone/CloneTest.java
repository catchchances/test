package com.kkdz.test.clone;

import java.util.Date;

public class CloneTest {

	public static void main(String[] args) {
		Employee e = new Employee();
		e.setName("Zhangsan");
		e.setSalary(5000);
		e.setSize(1000L);
		Date date = new Date(13700009999L);
		e.setDate(date);
		try {
			Employee clone = e.clone();
			int salary = clone.getSalary();
			System.out.println(salary);
			System.out.println(clone.getName());
			System.out.println(e.getDate());
			System.out.println(clone.getDate());
			System.out.println();
			date.setDate(19);
			// 由于Date对象是可变对象，e和clone两个实例共享这个date对象，所以改变date对象，会影响两个Employee对象里的date值。
			e.setDate(date);
			System.out.println(e.getDate());
			System.out.println(clone.getDate());
			System.out.println();
			// 如果想让两个Employee对象有单独的date，那么要这样：
			Date cloneDate = (Date) date.clone();
			clone.setDate(cloneDate);
			System.out.println(e.getDate());
			System.out.println(clone.getDate());
			System.out.println();
//			相互之间不会影响
			cloneDate.setDate(99);
			clone.setDate(cloneDate);
			System.out.println(e.getDate());
			System.out.println(clone.getDate());
			System.out.println();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}

	}

}
