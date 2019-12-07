package com.polytech;
import javax.sound.midi.*;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.swing.*;
import javax.swing.plaf.LabelUI;
import java.io.*;
import java.util.Arrays;

public class LyricsListener implements MetaEventListener {
    static Object data;
    static String buffer;
    public JLabel jLabel;
    public LyricsListener(JLabel label){
        super();
        jLabel = label;
        buffer = "";
    }
    @Override
    public void meta(MetaMessage metaMessage) {
        //type = 5 means lyrics
        if(metaMessage.getType() == 5) {
            String string = new String(metaMessage.getData());
            if(!string.equals(data)){
                System.out.print(string);
                //buffer += string;
                this.jLabel.setText(buffer);
                if(buffer.length() > 40) {

                    buffer = string;
                }
                else{
                    buffer += string;
                }
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
            JLabel label = new JLabel();
            LyricsListener listener = new LyricsListener(label);
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
