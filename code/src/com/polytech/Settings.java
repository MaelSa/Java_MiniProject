package com.polytech;

import javax.sound.midi.Sequencer;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Settings {
    private int slowFactor;
    private int speedFactor;
    private float speedModifier;
    private boolean mute;
    private Sequencer sequencer;
    private ArrayList<String> validCommands;
    private boolean pause;

    public Settings(){
        slowFactor = 1;
        speedFactor = 1;
        speedModifier = 1;
        mute = false;
        pause = false;
        validCommands = new ArrayList<String>();
        validCommands.add("1");
        validCommands.add("2");
        validCommands.add("3");
        validCommands.add("4");
    }
    public void modifySpeed(){
        this.speedModifier = 0;
        while (!(this.speedModifier > 0)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter speed factor");
            String speedFactorString = scanner.nextLine();
            this.speedModifier = Float.parseFloat(speedFactorString);
        }
        this.sequencer.setTempoFactor(this.speedModifier);
    }

    public void muteTrack() {
        this.sequencer.setTrackMute(1, true);
        this.mute = true;
    }

    public void unmuteTrack(){
        this.sequencer.setTrackMute(1, false);
        this.mute = false;
    }

    public boolean checkValidCommand(String command){
        return validCommands.contains(command);
    }

    public void outputCommands(){
        System.out.println("1 : Modify speed");
        if(this.mute){
            System.out.println("2 : Unmute");
        }
        else{
            System.out.println("2 : Mute");
        }
        if(this.pause){
            System.out.println("3 : Pause");
        }
        else{
            System.out.println("3 : Resume");
        }
        System.out.println("4 : Stop");
    }

}
