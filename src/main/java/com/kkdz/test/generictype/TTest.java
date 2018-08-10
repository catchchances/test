package com.kkdz.test.generictype;

import java.io.Serializable;
import java.util.ArrayList;

public class TTest {

	class MyList<T> {

	}

	/**
	 * 以下泛型只是为了表达写法，没有实际含义。</br>
	 * 注意：表达extends概念时，类（只能一个）在前，其它接口在后面
	 * 
	 * @author bbbbln
	 *
	 * @param <T>
	 * @param <U>
	 */
	class MyList1<T, U extends ArrayList<Integer> & Comparable<Integer> & Serializable> {
		/**
		 * 错误的写法</br>
		 * 以下为静态的，不是对象级别的。
		 */
//		private static T xxx;
//		public static T getXXX() {
//			return null;
//		}
	}

	/**
	 * 在修饰符后，方法名前面。
	 */
	public <T> T getElements() {
		return null;
	}

}
