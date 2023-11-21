package com.mycompany.lab7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JPanel {

    private ArrayList<Kula> listaKul;
    private int size = 20;
    private Timer timer;
    private final int DELAY = 33;
//dla 30fps -> 1s/30 = 0,033s

    public Panel() {
        listaKul = new ArrayList<>();
        setBackground(Color.BLACK);
        addMouseListener(new Event());
        timer = new Timer(DELAY, new Event());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Kula k : listaKul) {
            g.setColor(k.color);
            g.drawOval(k.x, k.y, k.size, k.size);
        }
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(listaKul.size()), 40, 40);
    }

    
    public class Event implements MouseListener, ActionListener {

        int collisionCount = 0;
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            listaKul.add(new Kula(e.getX(), e.getY(), size));
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            collisions();
            for (Kula k : listaKul) k.update();
            repaint();
        }
        
        
        void collisions(){
            ArrayList<Integer> collided = new ArrayList<>();
            for (int i = 0; i < listaKul.size(); i++) {
                if(collided.contains(i)) continue;
                
                Kula k1 = listaKul.get(i);
                
                for(int j = i+1; j < listaKul.size(); j++){
                    if(collided.contains(j)) continue;
                    
                    Kula k2 = listaKul.get(j);
                    
                    if (Math.abs(k1.x - k2.x) < size && Math.abs(k1.y - k2.y) < size) {
                        k1.xspeed = ((k1.mass-k2.mass)*k1.xspeed+2*k2.mass*k2.xspeed+k1.mass*k1.xspeed)/(k1.mass+k2.mass);
                        k1.yspeed = ((k1.mass-k2.mass)*k1.yspeed+2*k2.mass*k2.yspeed+k1.mass*k1.yspeed)/(k1.mass+k2.mass);
                        k2.xspeed = ((k2.mass-k1.mass)*k2.xspeed+2*k1.mass*k1.xspeed+k2.mass*k2.xspeed)/(k1.mass+k2.mass);
                        k2.yspeed = ((k2.mass-k1.mass)*k2.yspeed+2*k1.mass*k1.yspeed+k2.mass*k2.yspeed)/(k1.mass+k2.mass);
                        
                        collided.add(i);
                        collided.add(j);
                        collisionCount++;
                        System.out.println("Collision: x=" + k1.x + " y=" + k1.y + " kX=" + k2.x + " kY=" + k2.y);
                        break;
                    }
                }
            }
        }
    }
    
    
    private class Kula {
        public int x, y, size, mass, xspeed, yspeed;
        public Color color;
        private final int MAX_SPEED = 5;
        
        public Kula(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
            Random r = new Random();
            mass = r.nextInt(1, 11);
            xspeed = r.nextBoolean() ? r.nextInt(-MAX_SPEED, 0) : r.nextInt(1, MAX_SPEED+1);
            yspeed = r.nextBoolean() ? r.nextInt(-MAX_SPEED, 0) : r.nextInt(1, MAX_SPEED+1);
            
            System.out.println("Kula: xs=" + xspeed + " yspeed=" + yspeed);
        }
        
        public void update() {
            if (x <= 0 || x >= getWidth()) xspeed = -xspeed;
            if (y <= 0 || y >= getHeight()) yspeed = -yspeed;
            
            x += xspeed;
            y += yspeed;
        }
    }
}
