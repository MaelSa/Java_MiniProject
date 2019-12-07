package com.polytech;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class GraphicName {
    public GraphicName(MainClient client){
        JFrame frame = new JFrame("Javaoke");
        JButton submitButton = new JButton("Submit");
        JTextField nameField = new JTextField("Enter name");
        submitButton.setBounds(100,300,100,20);
        nameField.setBounds(100, 100, 100, 30);
        frame.add(submitButton);
        frame.add(nameField);
        frame.setSize(500,500);
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
