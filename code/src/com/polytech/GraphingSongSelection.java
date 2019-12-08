package com.polytech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphingSongSelection {
    public GraphingSongSelection(String[] songList, MainClient client){
        System.out.println(songList[1]);
        JFrame frame = new JFrame("Javaoke");
        JPanel jPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JLabel labelMostPlayedSong = new JLabel();
        JLabel labelMostActivePlayer = new JLabel();
        //submitButton.setBounds(100,200,140,40);
        labelMostActivePlayer.setText(songList[songList.length-1]);
        labelMostPlayedSong.setText(songList[songList.length-2]);
        ArrayList<String> L = new ArrayList<String>(Arrays.asList(songList));
        L.remove(L.size()-1);
        L.remove(L.size()-2);
        String[] stringsComboBox = L.toArray(new String[0]);
        JComboBox<String> songs = new JComboBox<String>(stringsComboBox);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object songselected = songs.getSelectedItem();
                String songselectedString = (String) songselected;

                try {
                    client.outputStream = client.socket.getOutputStream();
                    client.dataOutputStream = new DataOutputStream(client.outputStream);
                    client.dataOutputStream.writeUTF(songselectedString);
                    client.dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                frame.dispose();
            }
        });
        //songs.setBounds(300, 300, 100, 50);
        songs.setSelectedIndex(0);
        jPanel.add(songs);
        //frame.add(songs);
        //frame.add(jPanel);

        frame.add(submitButton, BorderLayout.EAST);
        frame.add(jPanel, BorderLayout.NORTH);
        frame.add(labelMostActivePlayer, BorderLayout.CENTER);
        frame.add(labelMostPlayedSong, BorderLayout.SOUTH);
        frame.setSize(500,150);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = frame.getSize();


        int windowX = Math.max(0, (screenSize.width  - windowSize.width ) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);

        frame.setLocation(windowX, windowY);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
