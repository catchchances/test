package com.kkdz.test.generictype;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayTTest {

	public static void main(String[] args) {
		// 报错版本
		// String[] minmax = minmax("a", "b");
		// 正确版本
		// String[] minmax1 = minmax1("c", "d");
		// System.out.println();
		storeGeneric();
	}

	/**
	 * 会报错：</br>
	 * Exception in thread "main" java.lang.ClassCastException:
	 * [Ljava.lang.Object; cannot be cast to [Ljava.lang.Comparable;</br>
	 * at com.kkdz.test.generictype.ArrayTTest.minmax(ArrayTTest.java:15)</br>
	 * at com.kkdz.test.generictype.ArrayTTest.main(ArrayTTest.java:8)</br>
	 * 
	 * @param ts
	 * @return
	 */
	public static <T extends Comparable> T[] minmax(T... ts) {
		Object[] mm = new Object[ts.length];
		return (T[]) mm;
	}

	/**
	 * 改进
	 * 
	 * @param ts
	 * @return
	 */
	public static <T extends Comparable> T[] minmax1(T... ts) {
		T[] mm = (T[]) Array.newInstance(ts.getClass().getComponentType(), ts.length);
		mm = ts;
		return mm;
	}

	public static void storeGeneric() {
		class Parent {
		}

		class Child extends Parent {
		}

		ArrayList<Parent> ps = new ArrayList<>();
		ps.add(new Parent());
		ps.add(new Child());

		class Person<T> {
			T person;
		}

		/**
		 * ArrayList只认Person类型，至于Person里的是什么类型，它并不关心
		 */
		ArrayList<Person<Parent>> ap = new ArrayList<>();
		Person<Parent> pp = new Person<>();
		pp.person = new Parent();
		ap.add(pp);
		Person<Parent> pp1 = new Person<>();
		pp1.person = new Child();
		ap.add(pp1);
	}

}

class Singleton<T> {
	/**
	 * Cannot make a static reference to the non-static type T
	 */
	// private static T xxx;
}
