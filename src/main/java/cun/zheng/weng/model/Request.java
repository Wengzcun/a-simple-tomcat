package cun.zheng.weng.model;

import java.io.InputStream;

/**
 * 将HTTP报文转化为Request对象
 */
public class Request {

    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public void parse(){
        StringBuilder request = new StringBuilder(2048);
        int i;
        byte[] buffer = new byte[2048];
        try{
            i = inputStream.read(buffer);
        }catch (Exception e){
            e.printStackTrace();
            i=-1;
        }
        for(int j =0; j < i; j++){
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri=parseUri(request.toString());
    }

    /**
     * 获取URI
     * HTTP请求格式第一行为 方法 + 空格 + URI + 空格 + 协议/版本
     * @param requestString
     * @return
     */
    private String parseUri(String requestString){
       int idx1,idx2;
       idx1=requestString.indexOf(" ");
       if(idx1 != -1){
           idx2=requestString.indexOf(' ', idx1 + 1);
           if(idx2 > idx1){
               return requestString.substring(idx1+1,idx2);
           }
       }
       return null;
    }

    public String getUri(){
        return uri;
    }
}
