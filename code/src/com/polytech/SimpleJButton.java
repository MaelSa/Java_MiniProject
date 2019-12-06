package com.polytech;

import javax.sound.midi.Sequencer;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleJButton {

    SimpleJButton(Sequencer sequencer){
        JFrame f=new JFrame("JavaOke");
        JPanel jPanel = new JPanel();
        //submit button
        JButton buttonPause=new JButton("Pause");
        JButton buttonResume = new JButton("Resume");
        buttonPause.setBounds(100,200,140, 40);
        buttonResume.setBounds(100,300,140,10);
        buttonPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sequencer.stop();
                buttonResume.setEnabled(true);
                buttonPause.setEnabled(false);

            }
        });
        buttonResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sequencer.start();
                buttonResume.setEnabled(false);
                buttonPause.setEnabled(true);
            }
        });
        JSlider slider = new JSlider(0, 100, 10);
        slider.addChangeListener(new ChangeListener() {
            @Override
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

        f.add(buttonPause);
        f.add(buttonResume);
        buttonResume.setEnabled(false);
        f.add(jPanel);
        f.setSize(500,500);
        //f.show();
        //f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //action listener
        //b.addActionListener(new ActionListener() {

        //    @Override
        //    public void actionPerformed(ActionEvent arg0) {
                //label1.setText("Name has been submitted.");


        //});
    }


    public static void main(String[] args) {
        //new SimpleJButton();
    }
}