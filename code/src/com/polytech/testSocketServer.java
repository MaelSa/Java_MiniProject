package com.polytech;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class testSocketServer {
    private static final int PORT = 8088;
    private ServerSocket ss;
    public testSocketServer() {
        try {
            this.ss = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
////    This method can only establish connection for one time, which does not satisfy our demands.
//    public String receiveMessage(){
//        try {
//
//            ServerSocket ss = new ServerSocket(PORT);
//            Socket s = ss.accept();
//
//            InputStream is = s.getInputStream();
//            OutputStream os = s.getOutputStream();
//
//            DataInputStream dis = new DataInputStream(is);//把输入流封装在DataInputStream
//            String msg = dis.readUTF();//使用readUTF读取字符串
//
//            DataOutputStream dos = new DataOutputStream(os);//把输出流封装在DataOutputStream中
//            dos.writeUTF("Message received");//使用writeUTF发送字符串
//            dos.close();
//
//            dis.close();
//            s.close();
//            ss.close();
//
//            return msg;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public void testContinueConnection(){
        while (true) {
            try {
                System.out.println("not accepted");
                Socket s = ss.accept();

                System.out.println("accepted");
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                DataInputStream dis = new DataInputStream(is);//把输入流封装在DataInputStream
                String msg = dis.readUTF();//使用readUTF读取字符串
                System.out.println(msg);

                DataOutputStream dos = new DataOutputStream(os);//把输出流封装在DataOutputStream中
                dos.writeUTF("Message received");//使用writeUTF发送字符串
                dos.close();

                dis.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendFile(String path){
        try {
            Socket s = this.ss.accept();
            System.out.println("connection established");
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
//            OutputStream outputStream = s.getOutputStream();
            DataOutputStream  outputStream = new DataOutputStream(s.getOutputStream());

            byte[] buff = new byte[512];
            int length = 0;
            while((length = fileInputStream.read(buff,0,buff.length))!=-1){
                System.out.println("enter loop");
                outputStream.write(buff,0,length);
            }
            s.shutdownOutput();
            fileInputStream.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SendMessage(String string){
        try {
            Socket s = ss.accept();

            System.out.println("accepted");
            OutputStream os = s.getOutputStream();

            DataOutputStream dos = new DataOutputStream(os);//把输出流封装在DataOutputStream中
            dos.writeUTF(string);//使用writeUTF发送字符串
            dos.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        testSocketServer testsocketserver = new testSocketServer();
//        String message = testsocketserver.receiveMessage();
//        System.out.println("received: "+message);
//        testsocketserver.SendMessage("Vive la France!");

//        testsocketserver.testContinueConnection();
        testsocketserver.sendFile("D:\\JavaMiniProject\\code\\src\\com\\polytech\\input.jpg");
    }
}
