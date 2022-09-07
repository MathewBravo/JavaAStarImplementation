package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jWindow = new JFrame();
        jWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jWindow.setResizable(false);
        jWindow.add(new DisplayNode());
        jWindow.pack();
        jWindow.setSize(1600,900);
        jWindow.setVisible(true);
    }
}