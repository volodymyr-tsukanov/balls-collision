package com.mycompany.lab7;

import java.awt.Dimension;
import javax.swing.JFrame;


public class Lab7 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moje okno!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Panel());
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
