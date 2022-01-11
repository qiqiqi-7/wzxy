package duan;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {

        String name ="localhost";
        String port = "80";
        try{
            System.out.println("连接到主机");
            Socket socket = new Socket(name,Integer.valueOf(port));
            //获取远程的地址
            System.out.println("连接到主机的远程地址"+socket.getRemoteSocketAddress());


            //对于自己的机器来说,连接中携带的就是 输入流是 对方传送 的信息
            //对于自己的机器来说，连接中携带的  输出流就是想要输出的信息
            //获取输入流
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println("连接远程的数据，返回来的消息是"+dataInputStream.readUTF());
            //获取输出流



            //客户端连接了之后，应该先向 OutputStream流中写入数据
            //然后服务端等待连接了之后， 收到了客户端接收的数据之后，对这个连接进行相应
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("你好，我是dyj, 正在向你请求数据");
            dataOutputStream.flush();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
