package com.vt.Objects;

public class Vector {
    private double a, b, magnitude, angleX;


    public Vector(double a, double b){
        this.a = a;
        this.b = b;
        calculate();
    }


    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getAngleX() {
        return angleX;
    }
    public double getMagnitude() {
        return magnitude;
    }

    public void setA(double a) {
        this.a = a;
        calculate();
    }
    public void setB(double b) {
        this.b = b;
        calculate();
    }


    void calculate(){
        magnitude = Math.sqrt(a*a + b*b);
        angleX = a == 0 ? 0 : Math.atan(b/a);
    }


    public boolean equals(Vector otr){
        return a == otr.a && b == otr.b;
    }


    public static Vector sum(Vector A, Vector B){
        return new Vector(A.a + B.a, A.b + B.b);
    }
    public static Vector diff(Vector A, Vector B){
        return new Vector(A.a - B.a, A.b - B.b);
    }
    public static Vector multiply(Vector A, double scalar){
        return new Vector(A.a * scalar, A.b * scalar);
    }
    public static double multiply(Vector A, Vector B){
        return A.a * B.a + A.b * B.b;
    }
}
