package com.vt.Objects;

import java.awt.*;
import java.util.Random;


public class Ball {
    int x, y, size, mass;
    Vector speed;
    Color color;


    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());

        int MAX_SPEED = 5;
        Random r = new Random();
        size = r.nextInt(10, 51);
        mass = size / 10;
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
    public int getMass() {
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
        if(speed.getX() > 5) speed.setX(5);
        if(speed.getY() > 5) speed.setY(5);

        x += speed.getX();
        y += speed.getY();
    }

    @Override
    public String toString(){
        return "Ball: speed={" + speed.getX() + ";" + speed.getY() + "}";
    }
}
