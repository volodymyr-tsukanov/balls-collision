package com.vt.Panels;

import com.vt.Objects.Ball;
import com.vt.Objects.Vector;

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
    private Timer updateTimer;
    private Events events;


    public Panel() {
        balls = new ArrayList<>();
        events = new Events();
        updateTimer = new Timer(33, events); //30fps -> 1s/30 = 0.033s

        setBackground(Color.BLACK);
        addMouseListener(events);
        updateTimer.start();
    }

    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawObjects(graphics);
        drawUI(graphics);
    }
    
    protected void drawObjects(Graphics graphics){
        for (Ball b : balls) {
            graphics.setColor(b.getColor());
            graphics.drawOval(b.getX(), b.getY(), b.getSize(), b.getSize());
        }
    }
    
    protected void drawUI(Graphics graphics){
        graphics.setColor(Color.YELLOW);
        graphics.drawString("Balls: " + balls.size(), 40, 40);
        graphics.drawString("Collisions: " + events.collisionCount, 40, 55);
    }


    public class Events implements MouseListener, ActionListener {
        int collisionCount = 0, step = 0;


        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            balls.add(new Ball(e.getX(), e.getY()));
            repaint();

            updateTimer.restart();
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void actionPerformed(ActionEvent e) {
            if(step < 2) step++;
            else{
                simulation();
                step = 0;
            }
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
                        processCollision(b1, b2);
                        processCollision(b2, b1);

                        collisionCount++;
                        System.out.println("Collision " + i + ":" + b1 + " -> " + j + ":" + b2);
                    }
                }

                // Borders
                if (b1.getX() <= 0 || b1.getX() >= getWidth() || b1.getY() <= 0 || b1.getY() >= getHeight())
                    b1.setSpeed(Vector.multiply(Vector.neg(b1.getSpeed()), 0.85));

                // Kill
                if (Math.abs(b1.getX()) > 2*getWidth() || Math.abs(b1.getY()) > 2*getHeight()) balls.remove(b1);
            }
        }

        void processCollision(Ball b1, Ball b2){
            Vector b1Speed = b1.getSpeed(), b2Speed = b2.getSpeed();
            double x, y, collisionAngle = Vector.angleBetween(b1Speed, b2Speed), b1Mass = b1.getMass(), b2Mass = b2.getMass();

            x = ((b1Speed.getMagnitude()*Math.cos(b1Speed.getAngleX()-collisionAngle)*(b1Mass-b2Mass)+2*b2Mass*b2Speed.getMagnitude()*Math.cos(b2Speed.getAngleX()-collisionAngle))/(b1Mass + b2Mass))*(Math.cos(collisionAngle)+b1Speed.getMagnitude()*Math.sin(b1Speed.getAngleX()-collisionAngle)*Math.cos(collisionAngle+Math.PI/2));
            y = ((b1Speed.getMagnitude()*Math.cos(b1Speed.getAngleX()-collisionAngle)*(b1Mass-b2Mass)+2*b2Mass*b2Speed.getMagnitude()*Math.cos(b2Speed.getAngleX()-collisionAngle))/(b1Mass + b2Mass))*(Math.sin(collisionAngle)+b1Speed.getMagnitude()*Math.sin(b1Speed.getAngleX()-collisionAngle)*Math.sin(collisionAngle+Math.PI/2));

            if(Double.isNaN(x) || Double.isNaN(y)) b1.destroy();
            else b1.setSpeed(new Vector(x, y));
        }

        void updateObjects(){
            for (Ball b : balls) b.update();
        }
    }
}
