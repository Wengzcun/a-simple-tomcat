package cun.zheng.weng;

import cun.zheng.weng.model.Request;

import java.io.IOException;
import java.io.InputStream;

public class RequestTest {
    public static void main(String[] args) {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        Request request = new Request(inputStream);
        System.out.println(request.hashCode());
    }
}
