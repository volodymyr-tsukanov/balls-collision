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
    private Events events;


    public Panel() {
        balls = new ArrayList<>();
        events = new Events();
        timer = new Timer(33, events); //30fps -> 1s/30 = 0.033s
        
        setBackground(Color.BLACK);
        addMouseListener(events);
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
        graphics.drawString("Balls: " + balls.size(), 40, 40);
        graphics.drawString("Collisions: " + events.collisionCount, 40, 55);
    }


    public class Events implements MouseListener, ActionListener {
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
            // Collisions
            for (int i = 0; i < balls.size(); i++) {
                Ball b1 = balls.get(i);

                // Ball to ball
                for(int j = i+1; j < balls.size(); j++){
                    Ball b2 = balls.get(j);

                    if (Math.abs(b1.getX() - b2.getX()) < b1.getSize() && Math.abs(b1.getY() - b2.getY()) < b1.getSize()) {
                        /*b1.setSpeedX(((b1.getMass()-b2.getMass())*b1.getSpeedX()+2*b2.getMass()*b2.getSpeedX()+b1.getMass()*b1.getSpeedX())/(b1.getMass()+b2.getMass()));
                        b1.setSpeedY(((b1.getMass()-b2.getMass())*b1.getSpeedY()+2*b2.getMass()*b2.getSpeedY()+b1.getMass()*b1.getSpeedY())/(b1.getMass()+b2.getMass()));
                        b2.setSpeedX(((b2.getMass()-b1.getMass())*b2.getSpeedX()+2*b1.getMass()*b1.getSpeedX()+b2.getMass()*b2.getSpeedX())/(b1.getMass()+b2.getMass()));
                        b2.setSpeedY(((b2.getMass()-b1.getMass())*b2.getSpeedY()+2*b1.getMass()*b1.getSpeedY()+b2.getMass()*b2.getSpeedY())/(b1.getMass()+b2.getMass()));*/
                        b1.setSpeedX(-b1.getSpeedX());
                        b1.setSpeedY(-b1.getSpeedY());
                        b2.setSpeedX(-b2.getSpeedX());
                        b2.setSpeedY(-b2.getSpeedY());

                        collisionCount++;
                        System.out.println("Collision " + i + ":" + b1 + " -> " + j + ":" + b2);
                    }
                }

                // Borders
                if (b1.getX() <= 0 || b1.getX() >= getWidth()) b1.setSpeedX(-b1.getSpeedX());
                if (b1.getY() <= 0 || b1.getY() >= getHeight()) b1.setSpeedY(-b1.getSpeedY());

                // Kill
                if (Math.abs(b1.getX()) > 2*getWidth()) balls.remove(i);
                if (Math.abs(b1.getY()) > 2*getHeight()) balls.remove(i);
            }
        }

        void updateObjects(){
            for (Ball b : balls) b.update();
        }
    }
}
