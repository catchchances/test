package com.kkdz.test;

public class Test {
	public static void main(String[] args) throws Exception {
		CompareWar compareWar = new CompareWar();
		Config config = new Config("E:/war/oauthV2.2.2.0.war", "E:/war/oauthV2.2.3.0.war", "E:/war/compared");
		compareWar.compare(config);
	}
}
