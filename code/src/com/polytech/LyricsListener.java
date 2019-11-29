package com.polytech;
import javax.sound.midi.*;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;

public class LyricsListener implements MetaEventListener {
    @Override
    public void meta(MetaMessage metaMessage) {
        if(metaMessage.getType()==5){
            String string = new String(metaMessage.getData());
            System.out.println(string);
        }

    }
}
