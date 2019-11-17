
/*
 *   Option préférée !
 */



package com.polytech;
import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.io.*;

public class testJavaMidiLibrary2 implements Runnable{
    File sound;
    Sequence seq;
    Sequencer midi;
    Thread runner;
    public testJavaMidiLibrary2(String f){
        try {
            sound=new File(f);
            seq=MidiSystem.getSequence(sound);
            midi=MidiSystem.getSequencer();
            midi.open();
            midi.setSequence(seq);
        }
        catch (Exception ex) {
        }
    }

    public void run(){
        try {
            while(true){
                if(!midi.isRunning())midi.start();
                Thread.sleep(1000);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void playMidi(){
        try
        {
            //midi.start();
            if(runner==null){
                runner=new Thread(this);
                runner.start();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void stopMidi(){
        try
        {
            runner.stop();
            runner=null;
            midi.stop();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        testJavaMidiLibrary2 playMidi1 = new testJavaMidiLibrary2("D:\\JavaMiniProject\\files\\John_Denver_-_Poems_Prayers_and_Promises.mid");
        playMidi1.playMidi();
//        playMidi1.stopMidi();
    }
}