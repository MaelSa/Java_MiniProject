package com.polytech;

import javax.xml.crypto.Data;
import javax.xml.stream.events.EntityReference;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The MainServer class in the main class for the server side of JavaOke project
 * @author Maël Salazard and Yulin Xie
 */
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
    /**
     *The MainServer constructor, does not take parameters.
     * It initializes the socket connection
     * with the client, and receives its name and creates songsList,
     * with all the midi files
     * in the "code" folder
     */
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

    /**
     * Sends a string to the client socket. This string contains the name of all
     * the midi files available
     * and the data relative to the most played song and most active player.
     */
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

    /**
     * Receive the selected song's name
     * @return String, the song selected by the client
     */
    public String receiveSelectedSong() {
        try{
            String selectedSong;
            this.inputStream = client.getInputStream();
            this.dataInputStream = new DataInputStream(this.inputStream);
            selectedSong = this.dataInputStream.readUTF();
            return selectedSong;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deserialazes the GeneralData object
     * containing the most played song and most active player data
     */
    public void loadGeneralData() {
        try {
            FileInputStream fi = null;
            fi = new FileInputStream(new File("code/generalData.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            this.generalData = (GeneralData) oi.readObject();
            oi.close();
            fi.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Serializes the GeneralData object
     */
    public void storeGeneralData(){
        try{
            FileOutputStream f = new FileOutputStream(new File("code/generalData.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(this.generalData);
            o.close();
            f.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * takes the song's name chosen by the client, and sends them the corresponding
     * midi file via the client socket
     * @param song
     */
    public void sendChosenSong(String song) {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

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
