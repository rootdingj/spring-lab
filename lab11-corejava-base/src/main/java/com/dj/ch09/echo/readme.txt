
实现一个多线程的回显（echo）服务器

用NIO的多路复用套接字实现服务器，NIO的操作带来了更好的性能，但有些操作是比较底层

-----------------------------
1、NIO介绍

	NIO非堵塞应用通常适用用在I/O读写等方面，我们知道，系统运行的性能瓶颈通常在I/O读写，包括对端口和文件的操作上。
	过去，在打开一个I/O通道后，read()将一直等待在端口一边读取字节内容，假如没有内容进来，read()也是傻傻的等，这会影响我们程序继续做其他事情，
	那么改进做法就是开设线程，让线程去等待，但是这样做也是相当耗费资源的。

	Java NIO非堵塞技术实际是采取Reactor模式，或者说是Observer模式为我们监察I/O端口，假如有内容进来，会自动通知我们，这样，我们就不必开启多个线程死等

2、NIO主要原理和适用
	NIO 有一个主要的类Selector,这个类似一个观察者，只要我们把需要探知的socketchannel告诉Selector,
	我们接着做别的事情，当有事件发生时，他会通知我们，传回一组SelectionKey,我们读取这些Key,就会获得我们刚刚注册过的socketchannel,
	然后，我们从这个Channel中读取数据，放心，包准能够读到，接着我们可以处理这些数据。
	Selector内部原理实际是在做一个对所注册的channel的轮询访问，不断的轮询(目前就这一个算法)，一旦轮询到一个channel有所注册的事情发生，
	比如数据来了，他就会站起来报告，交出一把钥匙，让我们通过这把钥匙来读取这个channel的内容。

---------------------------------
Java (Socket,ServerSocket)与(SocketChannel,ServerSocketChannel)区别和联系

	Socket 和ServerSocke 是一对  他们是java.net下面实现socket通信的类
	SocketChannel 和ServerSocketChannel是一对   他们是java.nio下面实现通信的类 支持异步通信

	服务器必须先建立ServerSocket或者ServerSocketChannel 来等待客户端的连接
	客户端必须建立相对应的Socket或者SocketChannel来与服务器建立连接
	服务器接受到客户端的连接受，再生成一个Socket或者SocketChannel与此客户端通信

	不过Socket和SocketChannel可以通过 socket.channel() SocketChannel.socket() 方法相互转换
	同理ServerSocket 和ServerSocketChannel 也可以相互转换

