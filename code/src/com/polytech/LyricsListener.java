package com.polytech;
import javax.sound.midi.*;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;

public class LyricsListener implements MetaEventListener {
    @Override
    public void meta(MetaMessage metaMessage) {
        System.out.println(metaMessage.getType());
        //type = 5 means lyrics
        if(metaMessage.getType() == 5) {
            String string = new String(metaMessage.getData());
            //System.out.print(string);
        }
        // type = 6 : parts of the song, chorus bridge...
        else if(metaMessage.getType() == 6) {
            String string = new String(metaMessage.getData());
            System.out.println(string);

        }

    }
}
