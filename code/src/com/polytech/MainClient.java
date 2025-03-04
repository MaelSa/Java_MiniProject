package com.polytech;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class for the client side of the Javaoke
 */
public class MainClient {
    Socket socket;
    private String name;
    OutputStream outputStream;
    DataOutputStream dataOutputStream;
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private FileOutputStream fileOutputStream;
    private BufferedOutputStream bufferedOutputStream;

    /**
     * Creates the client and connects it to the server via socket
     */
    MainClient(){
        try {
            System.out.println("Attempting to reach server");
            socket = new Socket("127.0.0.1", 5000);
            System.out.println("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives the string containing the name of all the songs available on the server,
     * but also the data for the most played song and most active player
     * @return String songList
     */
    public String receiveSongList() {
        try{
            this.inputStream = socket.getInputStream();
            this.dataInputStream = new DataInputStream(this.inputStream);
            String songList = this.dataInputStream.readUTF();
            return songList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Transforms the string containing the songs into a list
     * @param songList
     * @return List<String> list
     */
    public List<String> songStringToList(String songList){
        List<String> list = new ArrayList<String>();
        String str[] = songList.split("\n");
        list = Arrays.asList(str);
        return list;
    }


    public String selectSong(String songListString, List<String> songListList) {
        try{
            String choice = "";
            boolean end = false;
            Scanner scanner = new Scanner(System.in);
            while(!end){
                System.out.println(songListString);
                System.out.println("Choose a song");
                choice = scanner.nextLine();
                end = songListList.contains(choice);
                if(!end){
                    System.out.println("Not valid choice");
                }
            }
            this.outputStream = socket.getOutputStream();
            this.dataOutputStream = new DataOutputStream(outputStream);
            this.dataOutputStream.writeUTF(choice);
            this.dataOutputStream.flush();
            return choice;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Receive the midi file containing the song from the server
     */
    public void receiveSongFile(){
        try{
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Plays the last received song
     */
    public void playSong(){
        try{
            JLabel label = new JLabel();
            LyricsListener listener = new LyricsListener(label);
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.addMetaEventListener(listener);
            sequencer.open();
            InputStream is = new BufferedInputStream(new FileInputStream(new File("code/received.mid")));
            sequencer.setSequence(is);
            sequencer.start();

            GraphicDuringSong simpleJButton = new GraphicDuringSong(sequencer, label);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainClient client = new MainClient();
        GraphicName graphicName = new GraphicName(client);
        String songListString = client.receiveSongList();
        List<String> songListList = client.songStringToList(songListString);
        String[] strarray = songListList.toArray(new String[0]);
        GraphingSongSelection graphingSongSelection = new GraphingSongSelection(strarray, client);
        client.receiveSongFile();
        System.out.println("received");
        client.playSong();
    }
}
