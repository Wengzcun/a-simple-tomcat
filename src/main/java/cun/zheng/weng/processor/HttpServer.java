package cun.zheng.weng.processor;

import cun.zheng.weng.model.Request;
import cun.zheng.weng.model.Response;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static final String SHUTDOWM_CMD = "/SHUTDOWN";

    public static final String WEB_ROOT = System.getProperty("user.dir")
            + File.separator + "target/classes/webroot";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await(){

        //Create a ServerSocket
        ServerSocket serverSocket = null;
        int port = 8080;
        try{
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        //Loop waiting for a request
        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                //make a tcp connection
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //create Request Object
                Request request = new Request(input);
                request.parse();

                //create Response Object
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                //close the socket
                socket.close();

                //check if the previous URI is a shutdown command
                shutdown = request.getUri().endsWith(SHUTDOWM_CMD);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }
}
