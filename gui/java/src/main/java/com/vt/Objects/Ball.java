package com.vt.Objects;

import java.awt.*;
import java.util.Random;


public class Ball {
    int x, y, size;
    double mass;
    Vector speed;
    Color color;


    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());

        int MAX_SPEED = 5;
        Random r = new Random();
        size = r.nextInt(40, 51);
        mass = size * 10;
        double sx = r.nextBoolean() ? r.nextDouble(-MAX_SPEED, 0) : r.nextDouble(1, MAX_SPEED+1), sy = r.nextBoolean() ? r.nextDouble(-MAX_SPEED, 0) : r.nextDouble(1, MAX_SPEED+1);
        speed = new Vector(sx, sy);
    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSize() {
        return size;
    }
    public double getMass() {
        return mass;
    }
    public Vector getSpeed() {
        return speed;
    }
    public Color getColor() {
        return color;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }


    public void update() {
        if(speed.getMagnitude() > 1000) destroy();
        else if(speed.getMagnitude() > 50) speed = Vector.multiply(speed, 0.25);

        x += speed.getX();
        y += speed.getY();
    }

    public void destroy(){
        x = 9999*9999;
        y = 9999*9999;
        speed = new Vector(0, 0);
    }

    @Override
    public String toString(){
        return "Ball: speed={" + speed.getX() + ";" + speed.getY() + "}";
    }
}
