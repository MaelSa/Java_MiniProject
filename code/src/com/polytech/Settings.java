package com.polytech;

import javax.sound.midi.Sequencer;

public class Settings {
    private int slowFactor;
    private int speedFactor;
    private boolean mute;

    public Settings(){
        slowFactor = 1;
        speedFactor = 1;
        mute = false;
    }
    public void modifySetting(Sequencer sequencer){

    }
}
