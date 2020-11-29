package cun.zheng.weng.model;

import cun.zheng.weng.processor.HttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * HTTP Response
 */
public class Response {

    private static final int BUFFER_SIZE = 1024;

    Request request;

    OutputStream output;

    public Response(OutputStream output){
        this.output = output;
    }

    public void setRequest(Request request){
        this.request = request;
    }

    public void sendStaticResource() throws IOException{
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try{
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if(file.exists()){
                fis = new FileInputStream(file);
                int ch = fis.read(bytes,0,BUFFER_SIZE);
                while(ch != -1){
                    output.write(bytes);
                    //流没有索引，所以每次都是从头开始读取
                    ch = fis.read(bytes,0,BUFFER_SIZE);
                }
            } else {
                //file not found
                String errMsg = "HTTP/1.1 404 File Not Found\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: 23\r\n"
                        + "\r\rn"
                        +"<h1>File Not Found</h1>";
                output.write(errMsg.getBytes());
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            if(fis != null){
                fis.close();
            }
        }
    }
}
