package com.vt.Panels;

import com.vt.Objects.Ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JPanel {
    ArrayList<Ball> balls;
    private Timer timer;
    private Event event;


    public Panel() {
        balls = new ArrayList<>();
        event = new Event();
        timer = new Timer(33, event); //30fps -> 1s/30 = 0.033s
        
        setBackground(Color.BLACK);
        addMouseListener(event);
        timer.start();
    }

    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawObjects(graphics);
        drawUI(graphics);
    }
    
    protected void drawObjects(Graphics graphics){
        for (Ball k : balls) {
            graphics.setColor(k.getColor());
            graphics.drawOval(k.getX(), k.getY(), k.getSize(), k.getSize());
        }
    }
    
    protected void drawUI(Graphics graphics){
        graphics.setColor(Color.YELLOW);
        graphics.drawString(Integer.toString(balls.size()), 40, 40);
    }


    public class Event implements MouseListener, ActionListener {
        int collisionCount = 0;


        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            balls.add(new Ball(e.getX(), e.getY()));
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void actionPerformed(ActionEvent e) {
            simulation();
            updateObjects();
            repaint();
        }


        void simulation(){
            ArrayList<Integer> collided = new ArrayList<>();
            for (int i = 0; i < balls.size(); i++) {
                if(collided.contains(i)) continue;

                Ball b1 = balls.get(i);

                for(int j = i+1; j < balls.size(); j++){
                    if(collided.contains(j)) continue;

                    Ball b2 = balls.get(j);

                    if (Math.abs(b1.getX() - b2.getX()) < b1.getSize() && Math.abs(b1.getY() - b2.getY()) < b1.getSize()) {
                        b1.setSpeedX(((b1.getMass()-b2.getMass())*b1.getSpeedX()+2*b2.getMass()*b2.getSpeedX()+b1.getMass()*b1.getSpeedX())/(b1.getMass()+b2.getMass()));
                        b1.setSpeedY(((b1.getMass()-b2.getMass())*b1.getSpeedY()+2*b2.getMass()*b2.getSpeedY()+b1.getMass()*b1.getSpeedY())/(b1.getMass()+b2.getMass()));
                        b2.setSpeedX(((b2.getMass()-b1.getMass())*b2.getSpeedX()+2*b1.getMass()*b1.getSpeedX()+b2.getMass()*b2.getSpeedX())/(b1.getMass()+b2.getMass()));
                        b2.setSpeedY(((b2.getMass()-b1.getMass())*b2.getSpeedY()+2*b1.getMass()*b1.getSpeedY()+b2.getMass()*b2.getSpeedY())/(b1.getMass()+b2.getMass()));

                        collided.add(i);
                        collided.add(j);
                        collisionCount++;
                        System.out.println("Collision " + i + ":" + b1 + " -> " + j + ":" + b2);
                    }
                }
            }
        }

        void updateObjects(){
            for (Ball k : balls) k.update();
        }
    }
}
