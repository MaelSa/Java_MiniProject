package com.polytech;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MainServer {
    private ServerSocket servsock;
    private Socket client;
    private String client_name;
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private BufferedOutputStream bufferedOutputStream;
    private BufferedInputStream bufferedInputStream;
    private GeneralData generalData;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ArrayList<String> availableSongsList;
    private String availableSongsString;
    MainServer(){
        try {
            servsock = new ServerSocket(5000);
            System.out.println("Waiting for client");
            client = servsock.accept();
            System.out.println("Connected with client");
            inputStream = client.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            client_name = dataInputStream.readUTF();
            System.out.println("Waiting for client's name");
            System.out.println(client_name);
            outputStream = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        availableSongsList = findAllMidiFiles.midiFilesArrayList(new File("code"),new ArrayList<String>());
        availableSongsString = findAllMidiFiles.midiFilesString(new File("code"), "");
    }
    public void sendSongList(){
        try {
            this.dataOutputStream.writeUTF(this.availableSongsString);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadGeneralData() throws Exception{
        FileInputStream fi = null;

            fi = new FileInputStream(new File("generalData.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            this.generalData = (GeneralData) oi.readObject();
            oi.close();
            fi.close();
    }
    public void storeGeneralData() throws Exception{
        FileOutputStream f = new FileOutputStream(new File("generalData.txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this.generalData);
        o.close();
        f.close();
    }
    public void sendChosenSong(String song) throws Exception{
        String songmid = song + ".mid";
        File songsend = new File(songmid);
        byte [] mybytearray  = new byte [(int)songsend.length()];
        this.fileInputStream = new FileInputStream(songsend);
        this.bufferedInputStream = new BufferedInputStream(fileInputStream);
        this.bufferedInputStream.read(mybytearray, 0, mybytearray.length);
        this.outputStream = this.client.getOutputStream();
        this.outputStream.write(mybytearray, 0, mybytearray.length);
        this.outputStream.flush();
        System.out.println("Song sent");
        this.bufferedInputStream.close();
        this.outputStream.close();
    }
    public static void main(String[] args) {
        MainServer server = new MainServer();
    }

}
