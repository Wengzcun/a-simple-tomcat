1.最简单的服务器数据流:Request -> HttpServer -> Response
不使用任何框架，直接使用Socket传输数据，而Socket基于TCP连接，
而TCP是全双工的连接，所以一个Socket既可以写也可以读，当然了，读写数据在网络上都是通过字节流的方式传输。

2.Servlet容器功能:
2.1 当第一次调用某个servlet时，要载入该servlet类（class文件载入到内存），并调用其init()方法
2.2针对每个请求，创建一个javax.servlet.ServletRequest实例和一个javax.servlet.ServletResponse实例
2.3调用该servlet的service()方法,将servletRequest对象和servletResponse对象作为参数传入
2.4当关闭该servlet类时，调用其destroy()方法释放资源，并卸载该servlet类
