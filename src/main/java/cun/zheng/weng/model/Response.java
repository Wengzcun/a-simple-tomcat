package cun.zheng.weng.model;

import cun.zheng.weng.constant.Constant;
import cun.zheng.weng.processor.HttpServer;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * HTTP Response
 */
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;

    Request request;

    OutputStream output;

    PrintWriter writer;

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
            File file = new File(Constant.WEB_ROOT, request.getUri());
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

    @Override
    public PrintWriter getWriter() throws IOException{
        writer = new PrintWriter(output,true);
        return writer;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }


    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
