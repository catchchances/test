
##### buffer 

Java里NIO相比于旧的IO新增的`buffer缓冲区`。

每种基本数据类型都会有一种对应buffer(boolean除外)，bytebuffer最常用。

buffer里用`数组`来存储数据，做为流的暂存区。

如果没有buffer，就得一次一个变量来取。而把buffer交给流，他会尽量填写满buffer. 

##### channel

`channel`是一个管道，双向读写。 流是单向的。

channel主要分为面向网络的SelectableChannel和面向文件的FileChannel. 

面向网络用得最多的是子类ServerSocketChannel and SocketChannel. 


##### selector
> the key to the nio  

多路复用器，会轮询注册到selector里边的channels，选出准备就绪的channel，然后通过selectionKey去访问这组channel。

selector will check the registed channels again and again.

It will select a group of channels being ready. 

**selectionKey --to-->  a group of channels.**







