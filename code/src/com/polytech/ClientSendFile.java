package com.polytech;

import java.io.*;
import java.net.Socket;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

public class ClientSendFile {
    private Socket socket = null;
    private String filereceive = "code/received.mid";

    public ClientSendFile(String adress, int port) throws Exception {
        int bytesread;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        byte [] mybyte = new byte[602238600];
        socket = new Socket(adress, port);
        System.out.println("Connected");
        InputStream iss = socket.getInputStream();
        fos = new FileOutputStream(filereceive);
        bos = new BufferedOutputStream(fos);
        bytesread = iss.read(mybyte, 0, mybyte.length);
        current = bytesread;
        //BufferedInputStream bis = null;
        do {
            bytesread = iss.read(mybyte, current, (mybyte.length - current));
            if(bytesread >= 0) current += bytesread;
        } while(bytesread > -1);

        bos.write(mybyte, 0, current);
        bos.flush();
        System.out.println("done");
        InputStream is = new BufferedInputStream(new FileInputStream(new File("code/received.mid")));
        //LyricsListener listener = new LyricsListener();
        Sequencer sequencer = MidiSystem.getSequencer();
        //sequencer.addMetaEventListener(listener);
        sequencer.open();
        sequencer.setSequence(is);
        sequencer.start();
        socket.close();


    }
    public static void main(String args[]) throws Exception
    {
            ClientSendFile client = new ClientSendFile("127.0.0.1", 5000);
    }
}
