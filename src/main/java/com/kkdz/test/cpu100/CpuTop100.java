package com.kkdz.test.cpu100;

public class CpuTop100 {
	
	public static void main(String[] args) {
		int i = 0;
		while(true) {
			i = i+1;
		}
	}

}
//找出cpu100%
//先找到占用cpu的java进程，jps, top 等
//查出pid，并转换成16进制数(Dex)备用。
//jstack pid | grep Dex或者jstack pid > a.txt
//在a.txt里查看16进制数相关的位置，查找代码位置，进而查找原因


