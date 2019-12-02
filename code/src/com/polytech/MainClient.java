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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void choose_song(){

    }

    public static void main(String[] args) {
        MainClient client = new MainClient();
    }
}
