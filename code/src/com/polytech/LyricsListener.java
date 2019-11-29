package com.polytech;
import javax.sound.midi.*;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;

public class LyricsListener implements MetaEventListener {
    @Override
    public void meta(MetaMessage metaMessage) {
        int type = metaMessage.getType();
        System.out.println(type);
    }
}
