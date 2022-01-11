package duan.hbpu;


import duan.bena.UserInfo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpPostUtil {

    private  static Map<String,String> universal = new HashMap<>();

    public static final String POST = "POST";

    public static final String GET = "GET";

    static  {
        universal.put("accept","*/*");
        universal.put("connection","Keep-Alive");
        universal.put("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        universal.put("content-type",
                "application/x-www-form-urlencoded");
    }

    public  static  void  getHttp(String url, String reqMethod,InputStream inputStream,String jwSession){
        if(url == null || url.length() == 0){
            throw new RuntimeException("请输入正确的url地址");
        }
        if(reqMethod == null || reqMethod.length() == 0){
            throw new RuntimeException("请输入正确的请求方式");
        }
        String method = reqMethod.toUpperCase();
        if("POST".equals(method)){
            executePostHttp(url,POST,inputStream,jwSession);
        }else if("GET".equals(method)){
            executeGetHttp(url,GET,inputStream,jwSession);
        }else{
            throw new RuntimeException("对不起，暂时不支持此种方式");
        }
    }

    private static void executeGetHttp(String urlpath, String get, InputStream inputStream, String jwSession) {
        try {
            URL url = new URL(urlpath);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //设置请求的方法
            connection.setRequestMethod(get);

            //设置请求头中的内容
            setDefaultHeaders(connection);

            //设置流
            connection.setDoInput(false);
            connection.setDoOutput(false);



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void setDefaultHeaders(HttpURLConnection connection) {
        Set<String> strings = universal.keySet();
        for (String key : strings) {
            connection.setRequestProperty(key,universal.get(key));
        }
    }

    private static void executePostHttp(String urlpath, String post, InputStream inputStream, String jwSession) {
        try {
            URL url = new URL(urlpath);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //设置请求的方法
            connection.setRequestMethod(post);

            //设置请求头中的内容
            setDefaultHeaders(connection);
            //设置jwSession
            connection.setRequestProperty("JWSESSION",jwSession);

            //设置流
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //设置相应的数据
            OutputStream outputStream = connection.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            Map<String,Object> map = new HashMap<>();
            map.put("formData",new UserInfo());
            objectOutputStream.writeObject(map);

            objectOutputStream.close();
            InputStream inputStream1 = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream1));
            StringBuffer stringBuffer = new StringBuffer();
            if(bufferedReader.ready()){
                String string = null;
                while ((string =  bufferedReader.readLine()) != null){
                    stringBuffer.append(string);
                }
            }
            System.out.println(stringBuffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        System.out.println("dyj");
        String localhost = "";

        HttpURLConnection connection = null;
        try {
            URL url =  new URL(localhost);
            //获取连接
            connection = (HttpURLConnection) url.openConnection();
            //设置请求的方法为 post请求
            connection.setRequestMethod("POST");


            //设置请求头中的数据
            connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            //允许写出
            connection.setDoOutput(true);
            //允许读入
            connection.setDoInput(true);

            connection.setUseCaches(false);

            //得到容器的响应码










        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
