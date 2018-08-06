package com.kkdz.test.clone;

import java.util.Date;

/**
 * 如果Employee类不实现Cloneable接口，而是直接覆盖clone方法，在克隆时会抛出CloneNotSupportedException。
 * Cloneable 是一个标记接口，本身不包含任何方法。
 * @author bbbbln
 *
 */
public class Employee implements Cloneable{

	private int salary;

	private String name;

	private Long size;
	
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@Override
	public Employee clone() throws CloneNotSupportedException {
		return (Employee) super.clone();
	}

}
