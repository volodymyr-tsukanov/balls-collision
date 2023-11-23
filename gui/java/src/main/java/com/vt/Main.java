package com.vt;

import com.vt.Panels.Panel;

import java.awt.Dimension;
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Collide these balls!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Panel());
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}

