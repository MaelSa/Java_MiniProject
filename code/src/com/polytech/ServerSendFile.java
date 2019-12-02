package com.polytech;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.*;
import java.io.*;

public class ServerSendFile {
    public ServerSocket socket = null;
    public FileInputStream infile = null;
    public FileOutputStream outfile = null;
    public Socket client = null;
    public OutputStream os = null;

    public ServerSendFile(int port) throws Exception{
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        ServerSocket socket = new ServerSocket(port);
        client = socket.accept();
        File songsend = new File("code/Hotel.mid");
        byte [] mybytearray  = new byte [(int)songsend.length()];
        infile = new FileInputStream(songsend);
        bis = new BufferedInputStream(infile);
        bis.read(mybytearray, 0, mybytearray.length);
        System.out.println(mybytearray.length);
        os = client.getOutputStream();
        os.write(mybytearray, 0, mybytearray.length);
        os.flush();
        System.out.println("Done");
        bis.close();
        os.close();
        client.close();
        socket.close();

    }
    public static void main(String args[]) throws Exception
    {
        ServerSendFile client = new ServerSendFile(5000);
    }
}
