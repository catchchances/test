package com.kkdz.test.generaltype;

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

	}

	/**
	 * 在修饰符后，方法名前面。
	 */
	public <T> T getElements() {
		return null;
	}

}
