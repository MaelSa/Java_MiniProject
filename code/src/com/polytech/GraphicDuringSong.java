package com.polytech;

import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the graphic interface for the song player
 */
public class GraphicDuringSong {
    /**
     * Creates a song player graphic interface.
     * @param sequencer
     * @param labelLyrics
     */
    GraphicDuringSong(Sequencer sequencer, JLabel labelLyrics){
        JFrame frame=new JFrame("JavaOke");
        frame.setLayout(new FlowLayout());
        JPanel jPanel = new JPanel();
        JButton buttonPause=new JButton("Pause");
        JButton buttonResume = new JButton("Resume");
        JButton buttonMute = new JButton("Mute");
        JButton buttonUnmute = new JButton("Unmute");
        buttonPause.addActionListener(new ActionListener() {
            @Override
            /**
             * Pauses the song, make the pause button unclickable and the resume button clickable
             */
            public void actionPerformed(ActionEvent actionEvent) {
                sequencer.stop();
                buttonResume.setEnabled(true);
                buttonPause.setEnabled(false);

            }
        });
        buttonResume.addActionListener(new ActionListener() {
            @Override
            /**
             * Resume the song, and make the button unclickable and the pause button clickable
             */
            public void actionPerformed(ActionEvent actionEvent) {
                sequencer.start();
                buttonResume.setEnabled(false);
                buttonPause.setEnabled(true);
            }
        });
        JSlider slider = new JSlider(0, 1000, 100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            /**
             * Change the speed of the song, number on the slider divided by 10
             */
            public void stateChanged(ChangeEvent changeEvent) {
                float value = (float) slider.getValue();
                sequencer.setTempoFactor(value/100);
            }
        });
        buttonMute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonMute.setEnabled(false);
                buttonUnmute.setEnabled(true);
                Track track[] = sequencer.getSequence().getTracks();
                for (int i = 0; i < track.length; i++){
                        sequencer.setTrackMute(i, true);
                }
            }
        });

        buttonUnmute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonMute.setEnabled(true);
                buttonUnmute.setEnabled(false);
                Track track[] = sequencer.getSequence().getTracks();
                for (int i = 0; i < track.length; i++){
                    sequencer.setTrackMute(i, false);
                }
            }
        });
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(200);
        slider.setMinorTickSpacing(100);
        jPanel.add(slider);
        frame.add(jPanel, BorderLayout.NORTH);

        frame.add(buttonPause);
        frame.add(buttonResume);
        buttonResume.setEnabled(false);
        buttonUnmute.setEnabled(false);
        frame.add(buttonMute, BorderLayout.EAST);
        frame.add(buttonUnmute, BorderLayout.WEST);
        frame.add(labelLyrics, BorderLayout.SOUTH);

        frame.setSize(550,300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = frame.getSize();


        int windowX = Math.max(0, (screenSize.width  - windowSize.width ) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);

        frame.setLocation(windowX, windowY);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}