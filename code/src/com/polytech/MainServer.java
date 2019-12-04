package com.polytech;

import javax.xml.crypto.Data;
import javax.xml.stream.events.EntityReference;
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
        availableSongsString = findAllMidiFiles.midiFilesString();
    }
    public void sendSongList(){
        try {
            this.outputStream = client.getOutputStream();
            this.dataOutputStream = new DataOutputStream(outputStream);
            String dataSong = "The most played song is " + this.generalData.getMostPlayedSong().get(0) + " it was played " + this.generalData.getMostPlayedSong().get(1) + " times";
            String dataPlayer = "The most active player is " + this.generalData.getMostActivePlayer().get(0) + " they played " + this.generalData.getMostActivePlayer().get(1) + " times";
            this.dataOutputStream.writeUTF(this.availableSongsString + dataSong + "\n" + dataPlayer);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String receiveSelectedSong() throws Exception {
        String selectedSong;
        this.inputStream = client.getInputStream();
        this.dataInputStream = new DataInputStream(this.inputStream);
        selectedSong = this.dataInputStream.readUTF();
        return selectedSong;
    }
    public void loadGeneralData() throws Exception{
        FileInputStream fi = null;
        fi = new FileInputStream(new File("code/generalData.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        this.generalData = (GeneralData) oi.readObject();
        oi.close();
        fi.close();
    }
    public void storeGeneralData() throws Exception{
        FileOutputStream f = new FileOutputStream(new File("code/generalData.txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this.generalData);
        o.close();
        f.close();
    }
    public void sendChosenSong(String song) throws Exception{
        String songmid = song + ".mid";
        File songsend = new File("code/"+songmid);
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

    public void selectSong() throws Exception{


    }
    public static void main(String[] args) throws Exception{

        MainServer server = new MainServer();
        //server.generalData = new GeneralData();
        //server.storeGeneralData();
        server.loadGeneralData();
        server.sendSongList();
        String choice = server.receiveSelectedSong();
        server.generalData.update_song_player(choice, server.client_name);
        server.storeGeneralData();
        server.sendChosenSong(choice);


    }

}
