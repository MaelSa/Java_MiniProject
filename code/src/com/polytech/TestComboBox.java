package com.polytech;

import javax.swing.*;

public class TestComboBox {
    public TestComboBox() {
        String[] str = {"oui", "deux", "cc"};
        JFrame frame = new JFrame("Test");
        JPanel jpanel = new JPanel();
        JComboBox<String> comboBox = new JComboBox<String>(str);
        jpanel.add(comboBox);
        frame.add(jpanel);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TestComboBox testComboBox = new TestComboBox();
    }

}
