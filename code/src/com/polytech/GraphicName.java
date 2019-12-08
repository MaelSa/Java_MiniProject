package com.polytech;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Graphic interface for the typing of the player's name
 */
public class GraphicName {
    /**
     * Contructor to initialize the graphic interface for the client's name
     * @param client
     */
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


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = frame.getSize();


        int windowX = Math.max(0, (screenSize.width  - windowSize.width ) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);

        frame.setLocation(windowX, windowY);

        frame.setVisible(true);
        submitButton.addActionListener(new ActionListener() {
            @Override
            /**
             * When the name is submitted, is sends it to the server
             */
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
