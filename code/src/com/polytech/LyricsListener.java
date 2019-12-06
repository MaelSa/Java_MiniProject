package com.polytech;
import javax.sound.midi.*;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import java.io.*;
import java.util.Arrays;

public class LyricsListener implements MetaEventListener {
    static Object data;
    @Override
    public void meta(MetaMessage metaMessage) {
        //type = 5 means lyrics
        if(metaMessage.getType() == 5) {
            String string = new String(metaMessage.getData());
            if(!string.equals(data)){
                System.out.print(string);
                data = string;
            }
        }
        // type = 6 : parts of the song, chorus bridge...
        else if (metaMessage.getType() == 6) {
            String string = new String(metaMessage.getData());
            if(!string.equals(data)){
                System.out.print(string);
                data = string;
            }
        }
    }

    public static void main(String[] args) {
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(new File("code/fBarbiegirl.mid")));
            LyricsListener listener = new LyricsListener();
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.addMetaEventListener(listener);
            sequencer.open();
            sequencer.setSequence(is);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
