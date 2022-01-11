package duan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends  Thread{


    private  String hostName;

    private Integer port;

    private ServerSocket serverSocket = null;

    public Server(String hostName, String port) throws IOException {
        this(hostName,Integer.valueOf(port));
    }

    public Server(String hostName, Integer port) throws IOException {
        serverSocket = new ServerSocket(port);//socket监听的端口
    }



    @Override
    public void run() {
        while (true){
            try{
                System.out.println("server服务器正在等待连接");
                Socket accept = serverSocket.accept();
                System.out.println("远程连接过来的地址是=>"+accept.getRemoteSocketAddress());

                //给对方回应信息
                DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
                dataOutputStream.writeUTF("好的，这是我回复给你的数据");

                //获取输入流
                InputStream inputStream = accept.getInputStream();
                //查看输入流中的信息
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                System.out.println("获取到的内容是=>"+dataInputStream.readUTF());

                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server("localhost","80");

        server.run();


    }
}
