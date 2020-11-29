package cun.zheng.weng.processor;

import cun.zheng.weng.model.Request;
import cun.zheng.weng.model.Response;

public class StaticResourceProcessor {

    public void process(Request request, Response response){
        try {
            response.sendStaticResource();
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
