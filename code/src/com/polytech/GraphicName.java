package com.polytech;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class GraphicName {
    public GraphicName(MainClient client){
        JFrame frame = new JFrame("Javaoke");
        JLabel label = new JLabel("Enter your name");
        JButton submitButton = new JButton("Submit");
        JTextField nameField = new JTextField();
        //submitButton.setBounds(100,300,100,20);
        //nameField.setBounds(100, 100, 100, 30);
        frame.add(nameField, BorderLayout.CENTER);
        frame.add(label, BorderLayout.NORTH);
        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setSize(200,100);
        frame.setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = nameField.getText();
                try {
                    client.outputStream = client.socket.getOutputStream();
                    client.dataOutputStream = new DataOutputStream(client.outputStream);
                    client.dataOutputStream.writeUTF(name);
                    client.dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                frame.dispose();

            }
        });
    }
}
