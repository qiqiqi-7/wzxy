package duan;

import java.io.IOException;
import java.net.*;

public class Post {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        Socket socket = new Socket();

        SocketAddress socketAddress = new InetSocketAddress("192.168.231.128",6379);
        socket.connect(socketAddress,100);

        /*
        * 1、超时 ——  空闲
        *
        * 2、手动挂断 ——   删除
        *
        * 4、拒接 ——  删除
        *
        * 3、呼叫失败 ——  空闲
        *
        *
        *
        * */




    }
}
