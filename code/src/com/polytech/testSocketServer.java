package com.polytech;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class testSocketServer {
    private static final int PORT = 8088;
    public String receiveMessage(){
        try {

            ServerSocket ss = new ServerSocket(PORT);
            Socket s = ss.accept();

            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            DataInputStream dis = new DataInputStream(is);//把输入流封装在DataInputStream
            String msg = dis.readUTF();//使用readUTF读取字符串

            DataOutputStream dos = new DataOutputStream(os);//把输出流封装在DataOutputStream中
            dos.writeUTF("hey!");//使用writeUTF发送字符串
            dos.close();

            dis.close();
            s.close();
            ss.close();

            return msg;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //This function is not used, but can run without problems
    public void test(){
        try {
            //establish server socket
            ServerSocket ss = new ServerSocket(PORT);

            //use accept() to get new connection
            Socket socket = ss.accept();

            //get input stream
            InputStream is = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            //get output stream
            OutputStream os = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(os);

            //get input message
            String info = null;
            while (!((info=bufferedReader.readLine())==null)){
                System.out.println("info from client: "+info);
            }

            //reply
            String reply = "get your info!";
            printWriter.write(reply);
            printWriter.flush();

            //close all
            printWriter.close();
            os.close();
            bufferedReader.close();
            is.close();
            socket.close();
            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testSocketServer testsocketserver = new testSocketServer();
        String message = testsocketserver.receiveMessage();
        System.out.println("received: "+message);
    }
}
