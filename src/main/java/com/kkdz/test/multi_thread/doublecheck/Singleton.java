package com.kkdz.test.multi_thread.doublecheck;

public class Singleton {

	private static volatile Singleton singleton;

	private Singleton() {

	}

	//
	// instance = new Singleton()这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。

	// 1. 给 instance 分配内存
	// 2. 调用 Singleton 的构造函数来初始化成员变量
	// 3. 将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了
	// 但是在 JVM 的即时编译器中存在指令重排序的优化。也就是说上面的第二步和第三步的顺序是不能保证的，最终的执行顺序可能是 1-2-3 也可能是
	// 1-3-2。如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，这时 instance 已经是非 null
	// 了（但却没有初始化），所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。
	//
	// 我们只需要将 instance 变量声明成 volatile 就可以了。
	//这里利用的是volatile的禁止指令重排序
	//

	// 双重检查，保证单例。
	public Singleton getSingleton() {
		if (null == singleton) {
			synchronized (Singleton.class) {
				if (null == singleton) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	//还有一种方法，声明为static final ，这样也是线程安全的
}
