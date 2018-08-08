package com.kkdz.test.trycatchfinally;

import java.util.Date;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 在try和catch里执行语句，速度差别不大。以1.8为例进行的测试。
 * @author bbbbln
 *
 */
public class TakeTimeTest {

	public static void main(String[] args) {
		int i = 0;
		Stack<Integer> stack = new Stack<>();
		while (i < 10000) {
			stack.push(i);
			i++;
		}

		// try block
		i = 0;
		long start = new Date().getTime();
		while (i < 10000) {
			try {
				stack.pop();
				System.out.print(i);
			} catch (EmptyStackException e) {

			}
			i++;
		}
		long end = new Date().getTime();
		System.out.println();
		System.out.println("try block:" + (end - start));

		// catch block
		i = 0;
		stack = new Stack<>();
		start = new Date().getTime();
		while (i < 10000) {
			try {
				stack.pop();
			} catch (EmptyStackException e) {
				System.out.print(i);
			}
			i++;
		}
		end = new Date().getTime();
		System.out.println();
		System.out.println("catch block:" + (end - start));

	}

}
