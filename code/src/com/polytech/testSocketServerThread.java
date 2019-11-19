package com.polytech;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class testSocketServerThread extends Thread {
    private static final int PORT = 8088;
    private ServerSocket ss;
    private testServer ServerThatContainMyself;
    public testSocketServerThread( testServer ServerThatContainMyself ) {
        try {
            this.ss = new ServerSocket(PORT);
            this.ServerThatContainMyself = ServerThatContainMyself;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean actionCorresponding(String msg){
        if (msg.equals("list")) {
            this.ServerThatContainMyself.handleList();
            return true;
        }else if(msg.equals("anything")){
            this.ServerThatContainMyself.handle_canBeAnything();
            return true;
        }
        return false;
    }


    //always running the method below to keep listening to the port
    public void run(){
        while (true) {
            try {
//                System.out.println("Connection not established");
                Socket s = ss.accept();

                System.out.println("Connection established");
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                DataInputStream dis = new DataInputStream(is);//把输入流封装在DataInputStream
                DataOutputStream dos = new DataOutputStream(os);//把输出流封装在DataOutputStream中
                String msg = dis.readUTF();//使用readUTF读取字符串

                //take corresponding action according to msg
                boolean actionTaken = this.actionCorresponding(msg);
                if(actionTaken){
                    dos.writeUTF(msg+" Action taken.");//使用writeUTF发送字符串
                    dos.close();
                }else {
                    dos.writeUTF(msg+" Demand wrong.");//使用writeUTF发送字符串
                    dos.close();
                }


                dis.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String args[]){
        testServer ServerThatContainMyself= new testServer();
        testSocketServerThread thread = new testSocketServerThread(ServerThatContainMyself);
        thread.start();
    }

}
