package com.polytech;
import javax.sound.midi.*;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.swing.*;
import javax.swing.plaf.LabelUI;
import java.io.*;
import java.util.Arrays;

/**
 * Implementation of the MetaEventListener class. This class aims to get the lyrics in the metaevents.
 */
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
    /**
     * Gets the lyrics sent by the sequencer and send them to the GUI.
     */
    public void meta(MetaMessage metaMessage) {
        //type = 5 means lyrics
        if(metaMessage.getType() == 5) {
            String string = new String(metaMessage.getData());
            if(!string.equals(data)){
                System.out.print(string);
                this.jLabel.setText(buffer);
                if(buffer.length() > 50) {

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


}
