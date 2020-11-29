package cun.zheng.weng.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class OneServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("From service");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("Hello...");
        writer.println("Tom cat...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
