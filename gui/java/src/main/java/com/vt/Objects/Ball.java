package com.vt.Objects;

import java.awt.*;
import java.util.Random;


public class Ball {
    int x, y, size, mass, speedX, speedY;
    Color color;


    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());

        int MAX_SPEED = 5;
        Random r = new Random();
        size = r.nextInt(10, 51);
        mass = size / 10;
        speedX = r.nextBoolean() ? r.nextInt(-MAX_SPEED, 0) : r.nextInt(1, MAX_SPEED + 1);
        speedY = r.nextBoolean() ? r.nextInt(-MAX_SPEED, 0) : r.nextInt(1, MAX_SPEED + 1);
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
    public int getSpeedX() {
        return speedX;
    }
    public int getSpeedY() {
        return speedY;
    }
    public Color getColor() {
        return color;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }


    public void update() {
        if(speedX > 5) speedX = 5;
        if(speedY > 5) speedY = 5;

        x += speedX;
        y += speedY;
    }

    @Override
    public String toString(){
        return "Ball: speed={" + speedX + ";" + speedY + "}";
    }
}
