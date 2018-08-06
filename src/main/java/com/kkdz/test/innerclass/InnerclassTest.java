package com.kkdz.test.innerclass;

public class InnerclassTest {

	public static void main(String[] args) {
		InnerclassTest t = new InnerclassTest();
		t.start();
	}

	public void start() {
		class InnerClass {
			public void print0() {
				System.out.println("public");
			}

			private void print1() {
				System.out.println("private");
			}
		}
		InnerClass ic = new InnerClass();
		ic.print0();
		ic.print1();
	}

//	public void start(int a, String b) {
//		class InnerClass {
//
//		}
//	}

	// public void publicStart() {
	// class InnerClass {
	// public void print() {
	// System.out.println("inner");
	// }
	// }
	// InnerClass ic = new InnerClass();
	// ic.print();
	// }

}

// class OuterClassTest {
// public static void main(String[] args) {
// // OuterClassTest ot = new OuterClassTest();
// InnerclassTest it = new InnerclassTest();
// it.start();
// }
// }
