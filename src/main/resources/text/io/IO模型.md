Unix I/O模型5种：

Blocking IO, NonBlocking IO, IO Multiplexing, Signal-Driven IO, ASynchronous IO

BIO, NIO, IO-M归纳为同步IO。

ASynchronous IO为异步IO。

在Linux里一次IO操作会涉及到两个系统对象：用户进程和内核空间。  
当用户进程发起IO的读写操作时，数据会先拷贝到系统内核的缓冲区中，然后才从内核的缓冲区中拷贝到应用程序的地址空间。  
所以当一个read操作发生后，会经历两个阶段。

第一阶段：等待数据准备(Waiting for the data to be ready)  
第二阶段：将数据从内核拷贝到进程中(Copying the data from the kernel to the process)  

> 对sockect流来说：

> 第一步：通常涉及到等待网络数据分组到达，然后被复制到内核的某个缓冲区；  
> 第二步：将数据从内核缓冲区复制到应用进行缓冲区中；  


阻塞与非阻塞

阻塞IO，当资源不可用时，IO请求一直阻塞，直到反馈结果(有数据或超时)  
非阻塞IO(返回失败),  当资源不可用时，IO请求离开返回，返回数据标识资源不可用
非阻塞IO(异步回调),  当资源不可用时，IO请求离开返回，返回数据标识资源不可用，但使用注册回调机制。当资源可用时，由资源方主动向请求方发送信号通知(表明资源可用)




1. 阻塞IO模型：

	传统IO模型。缺省情况下，所有文件系统都是IO阻塞的。
	
	以套接字接口为例讲解：
	
	在进程空间调用recvfrom, 其系统调用直到数据包到达且被复制到应用进程的缓冲区中或者发生错误时才返回，在此期间会一直等待。进程在从调用recvfrom开始到它返回的整段时间内都是阻塞的。
	
	
2. 非阻塞IO模型：
	
	recvfrom从应用层到内核的时候，如果该缓存区没有数据的话，就直接返回一个EWOULDBLOCK或EAGAIN错误。一般都对非阻塞IO模型进行轮询检查这个状态，看内核是不是有数据到来。  
	EWOULDBLOCK:e(rror) would block; EAGAIN:e(rror) again;
	
3. IO复用模型: 

	linux提供select/poll，进程通过将一个或多个fd传递给select(poll)系统调用，阻塞在select操作上，select(poll)可以帮我们侦测多个fd是否处于就绪状态。
	
	select/poll是按顺序扫描，且支持fd数量有限，使用受制约。因此Linux也提供了一个epoll系统调用，基于事件驱动代替顺序扫描，性能更高。当有fd就绪，立即回调函数rollback。
	
4. 信号驱动IO模型：
	
	首先开启套接字信号驱动IO功能，安装一个信号处理函数，进程继续进行下去并不阻塞。当数据准备好时，进程会收到一个Sigio信号，可以在信号处理函数中调用IO操作函数处理数据。
	
5. 异步IO模型：

	应用程序调用aio_read后，无论内核是否准备好数据，用户态进程会继续执行其它内容。内核数据准备完毕并将数据复制到用户空间，并向进程发送通知。IO两个阶段都是非阻塞的。  
	


	




	