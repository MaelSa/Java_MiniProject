package com.polytech;

import sun.awt.windows.ThemeReader;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.File;
import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.io.*;



public class testJavaMidiLibraryWithoutThread{
    File sound;
    Sequence seq;
    Sequencer midi;
    Thread runner;
    public testJavaMidiLibraryWithoutThread(String f){
        try {
            sound=new File(f);
            seq= MidiSystem.getSequence(sound);
            midi=MidiSystem.getSequencer();
            midi.open();
            midi.setSequence(seq);
        }
        catch (Exception ex) {
        }
    }

    public void slower(testJavaMidiLibraryWithoutThread playMidi1, int slowerFactor)throws Exception{
        if(slowerFactor>0){
            playMidi1.midi.setTempoInMPQ(playMidi1.midi.getTempoInMPQ() * slowerFactor);
        }else {
            throw new Exception("factor should be greater than 0");
        }
    }

    public void faster(testJavaMidiLibraryWithoutThread playMidi1, int fasterFactor)throws Exception{
        if(fasterFactor>0){
            playMidi1.midi.setTempoFactor(playMidi1.midi.getTempoFactor() * fasterFactor);
//          Equal function:
//          playMidi1.midi.setTempoInBPM(playMidi1.midi.getTempoInBPM()*fasterFactor);
        }else {
            throw new Exception("factor should be greater than 0 ");
        }
    }

    public void setTrackMute(testJavaMidiLibraryWithoutThread playMidi1, int trackNumber){
        playMidi1.midi.setTrackMute(trackNumber,true);
    }

    public static void main(String[] args)
    {
        testJavaMidiLibraryWithoutThread playMidi1 = new testJavaMidiLibraryWithoutThread("D:\\JavaMiniProject\\files\\John_Denver_-_Poems_Prayers_and_Promises.mid");
//        Track[] tracks = playMidi1.seq.getTracks();
//        for(Track t : tracks){
//            System.out.println(t);
//        }

        playMidi1.midi.start();
    }
}