package com.vt.Objects;

public class Vector {
    private double x, y, magnitude, angleX;


    public Vector(double x, double y){
        this.x = x;
        this.y = y;
        calculate();
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getAngleX() {
        return angleX;
    }
    public double getMagnitude() {
        return magnitude;
    }

    public void setX(double x) {
        this.x = x;
        calculate();
    }
    public void setY(double y) {
        this.y = y;
        calculate();
    }


    void calculate(){
        magnitude = Math.sqrt(x * x + y * y);
        angleX = x == 0 ? 0 : Math.atan(y / x);
    }


    public boolean equals(Vector otr){
        return x == otr.x && y == otr.y;
    }


    public static Vector neg(Vector A){
        return new Vector(-A.x, -A.y);
    }
    public static Vector sum(Vector A, Vector B){
        return new Vector(A.x + B.x, A.y + B.y);
    }
    public static Vector diff(Vector A, Vector B){
        return new Vector(A.x - B.x, A.y - B.y);
    }
    public static Vector multiply(Vector A, double scalar){
        return new Vector(A.x * scalar, A.y * scalar);
    }
    public static double multiply(Vector A, Vector B){
        return A.x * B.x + A.y * B.y;
    }
    public static double angleBetween(Vector A, Vector B){
        return Math.acos(((A.x*B.x)/(A.magnitude*B.magnitude)) + ((A.y*B.y)/(A.magnitude*B.magnitude)));
    }
    public static double scalar(Vector A, Vector B){
        return A.magnitude * B.magnitude * Math.cos(angleBetween(A, B));
    }
}
