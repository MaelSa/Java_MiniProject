package com.polytech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class GraphingSongSelection {
    public GraphingSongSelection(String[] songList, MainClient client){
        System.out.println(songList[1]);
        JFrame frame = new JFrame("Javaoke");
        JPanel jPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100,200,140,40);
        JComboBox<String> songs = new JComboBox<String>(songList);
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

        frame.add(submitButton);
        frame.add(jPanel);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
