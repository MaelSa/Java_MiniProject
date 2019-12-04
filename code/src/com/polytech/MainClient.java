package com.polytech;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    private Socket socket;
    private String name;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private FileOutputStream fileOutputStream;
    private BufferedOutputStream bufferedOutputStream;
    MainClient(){
        try {
            System.out.println("Attempting to reach server");
            socket = new Socket("127.0.0.1", 5000);
            System.out.println("Connected");
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter username");
            name = scanner.nextLine();  // Read user input
            System.out.println("Username is: " + name);  // Output user input
            dataOutputStream.writeUTF(name);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String receiveSongList() throws IOException {
        this.inputStream = socket.getInputStream();
        this.dataInputStream = new DataInputStream(this.inputStream);
        String songList = this.dataInputStream.readUTF();
        return songList;
    }

    public void selectSong(String songList){

    }

    public void receiveSongFile() throws Exception{
        int bytesread;
        int current = 0;
        byte [] mybyte = new byte[602238600];
        this.inputStream = socket.getInputStream();
        this.fileOutputStream = new FileOutputStream("code/received.mid");
        this.bufferedOutputStream = new BufferedOutputStream(this.fileOutputStream);
        bytesread = inputStream.read(mybyte, 0, mybyte.length);
        current = bytesread;
        do{
            bytesread = inputStream.read(mybyte, current, (mybyte.length - current));
            if(bytesread >= 0) current += bytesread;
        } while(bytesread > -1);
        this.bufferedOutputStream.write(mybyte,0, current);
        this.bufferedOutputStream.flush();
    }

    public static void main(String[] args) {
        MainClient client = new MainClient();
    }
}
