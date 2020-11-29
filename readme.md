最简单的服务器数据流:Request -> HttpServer -> Response
不使用任何框架，直接使用Socket传输数据，而Socket基于TCP连接，
而TCP是全双工的连接，所以一个Socket既可以写也可以读，当然了，读写数据在网络上都是通过字节传输，载体就是流。
