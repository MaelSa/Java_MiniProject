package com.polytech;
//import sun.reflect.generics.scope.Scope;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class testSocketClient {
    private static final String IP = "10.188.244.45";//"127.0.0.1";//
    private static final int PORT = 8088;
    private testClient clientThatContainMyself;
    public testSocketClient(){
        this.clientThatContainMyself = new testClient();
    }

    //String communication only
    public String sendMessage(String message){
        try {
            Socket s = new Socket(IP, PORT);
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();

            DataOutputStream dos = new DataOutputStream(os);//把输出流封装在DataOutputStream中
            dos.writeUTF(message);//使用writeUTF发送字符串

            DataInputStream dis = new DataInputStream(is);
            String msg = dis.readUTF();//使用readUTF读取字符串

            dis.close();
            dos.close();
            s.close();

            return msg;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getFile(String fileName){
        try {
            Socket s = new Socket(IP, PORT);
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();

            DataOutputStream dos = new DataOutputStream(os);//把输出流封装在DataOutputStream中
            dos.writeUTF(fileName);//使用writeUTF发送字符串
            dos.close();

            FileOutputStream fos = new FileOutputStream("received.jpg"  /*fileName*/);
            byte[] buff = new byte[1024];
            int length = 0;//actual received length
            while((length = is.read(buff))!=-1){
                fos.write(buff,0,length);
            }
            fos.flush();

            s.close();

        } catch (IOException e) {
            e.printStackTrace();
            //return null;
        }
    }





    public static void main(String[] args) {
        testSocketClient testsocketclient = new testSocketClient();
        String reply = testsocketclient.sendMessage("anything");
        System.out.println("reply: "+reply);

        testSocketClient testsocketclient2 = new testSocketClient();
        String reply2 = testsocketclient2.sendMessage("list");
        System.out.println("reply: "+reply2);

//        testSocketClient testsocketclient3 = new testSocketClient();
//        testsocketclient3.getFile("");



    }
}
