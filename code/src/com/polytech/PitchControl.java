package com.polytech;

import javax.sound.midi.*;
import java.io.*;
import static javax.sound.midi.ShortMessage.*;

/**
 * Class for changing the pitch of the music BEFORE playing it
 */
public class PitchControl {
    /**
     * Returns an array with the name of all the midi files in th "code" folder
     * @param filePath: The path of the MIDI file whose pitch is to be modified
     * @param pitch: Modification of pitch (Positive value = pitch up, negative value = pitch down, 0 = unchanged)
     * @return Sequencer: The pitch-modified Sequencer to be played
     */
    public static Sequencer changePitch(String filePath, int pitch) {
        try {
            /* Wrap the MIDI file into a Sequence */
            Sequence sequence = MidiSystem.getSequence(new File(filePath));

            /* Processing for all Tracks in this Sequence */
            for (Track track :  sequence.getTracks()) {
                /* Processing for all MidiEvent in each Track */
                for (int i = 0; i < track.size(); i++) {
                    MidiEvent event = track.get(i);
                    /* Read the message in the current MidiEvent */
                    MidiMessage message = event.getMessage();
                    /* Verification of message type */
                    if (message instanceof ShortMessage) {
                        /* Casting of message type into ShortMessage*/
                        ShortMessage sm = (ShortMessage) message;
                        /* Detailed processing respectively */
                        if (sm.getCommand() == NOTE_ON) {
                            /* Set new ShortMessage according to the required pitch change for the NOTE_ON message */
                            sm.setMessage(NOTE_ON, sm.getChannel(), sm.getData1() + pitch, sm.getData2());
                        } else if (sm.getCommand() == NOTE_OFF) {
                            /* Set new ShortMessage according to the required pitch change for the NOTE_OFF message */
                            sm.setMessage(NOTE_OFF, sm.getChannel(), sm.getData1() + pitch, sm.getData2());
                        }
                    }
                }
            }
            /* Create a sequencer for the sequence */
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            /* Wrap the Sequence into a Sequencer */
            sequencer.setSequence(sequence);
            /* Return of the current modified Sequencer */
            return sequencer;

        } catch (Exception e) {
            e.printStackTrace();
            /* If something goes wrong then return null */
            return null;
        }
    }


    /**
     * Example of how to use this function to change the pitch
     * @param args
     * We call this function, by putting the path of target midi file as well as the pitch change as parameters,
     * we then get a processed Sequencer waited to be called start()
     */
    public static void main(String[] args) {
            Sequencer sequencer = changePitch("D:\\JavaMiniProjectMaster\\code\\Hotel.mid",0);
            sequencer.start();
    }
}
