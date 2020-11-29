package cun.zheng.weng.processor;

import cun.zheng.weng.constant.Constant;
import cun.zheng.weng.model.Request;
import cun.zheng.weng.model.Response;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor {

    public void process(Request request, Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        //URLClassLoader是ClassLoader的一个直接子类，创建URLClassLoader实例后可以使用其loadClass()来加载Servlet类
        URLClassLoader loader = null;

        try{
            //create a URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classpath = new File(Constant.CLASS_PATH);

            String repository = (new URL("file",null,classpath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }catch (IOException e){
            e.printStackTrace();
        }

        Class myClass = null;
        try{
            myClass = loader.loadClass(servletName);
        }catch (ClassNotFoundException e){
            System.out.println(e.toString());
        }

        Servlet servlet = null;

        try{
            servlet = (Servlet)myClass.newInstance();
            servlet.service((ServletRequest)request,(ServletResponse)response);
        }catch (Exception e){
            System.out.println(e.toString());
        }catch (Throwable e){
            System.out.println(e.toString());
        }
    }
}
