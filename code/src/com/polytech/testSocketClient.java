package com.polytech;
import sun.reflect.generics.scope.Scope;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class testSocketClient {
    private static final String IP = "10.188.244.45";//"127.0.0.1";//
    private static final int PORT = 8088;

    //This function is not used, but can run without problems
    public void test(String messageSent){
        try {
            Socket socket = new Socket(IP, PORT);

            //get socket read & write stream
            OutputStream os = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(os);

            //input stream
            InputStream is = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            //send message out from client to server
            printWriter.write(messageSent);
            printWriter.flush();
            socket.shutdownOutput();

            //get reply
            String reply = null;
            while (!((reply=bufferedReader.readLine())==null)){
                System.out.println("reply: "+reply);
            }

            //close all
            bufferedReader.close();
            is.close();
            printWriter.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) {
        testSocketClient testsocketclient = new testSocketClient();
        String reply = testsocketclient.sendMessage("polytech nantes...");
//        System.out.println("reply: "+reply);
    }
}
