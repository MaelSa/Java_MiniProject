package com.polytech;

import javax.sound.midi.Sequencer;
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
        JFrame f=new JFrame("JavaOke");
        JPanel jPanel = new JPanel();
        JButton buttonPause=new JButton("Pause");
        JButton buttonResume = new JButton("Resume");
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
        JSlider slider = new JSlider(0, 100, 10);
        slider.addChangeListener(new ChangeListener() {
            @Override
            /**
             * Change the speed of the song, number on the slider divided by 10
             */
            public void stateChanged(ChangeEvent changeEvent) {
                float value = (float) slider.getValue();
                sequencer.setTempoFactor(value/10);
            }
        });
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        jPanel.add(slider);

        f.add(buttonPause, BorderLayout.EAST);
        f.add(buttonResume, BorderLayout.WEST);
        f.add(labelLyrics, BorderLayout.CENTER);
        buttonResume.setEnabled(false);
        f.add(jPanel, BorderLayout.NORTH);
        f.setSize(500,150);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = f.getSize();


        int windowX = Math.max(0, (screenSize.width  - windowSize.width ) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);

        f.setLocation(windowX, windowY);

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}