package com.kkdz.test.innerclass;

import java.util.ArrayList;

public class InnerclassTest2 {
	public static void main(String[] args) {

	}

	public void addFirends0() {
		ArrayList<String> friends = new ArrayList<String>();
		friends.add("Marry");
		friends.add("Jake");
		invite(friends);
	}

	public void addFirends1() {
		invite(new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("Marry");
				add("Jake");
				add("John");
			}
		});
	}

	public void invite(ArrayList<String> friends) {
		// todo
	}
}
