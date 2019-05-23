package ru.mirea;


public class Firefly {
    private double x;
    private double y;

    private double l;   //уровень люциферина
    private double r;   //радиус окресности светлячка

    public double getF() {
        return 1.1 * Math.pow(y, 2) + 2.2 * Math.pow(x, 2) - 4.1 * y - 1.6 * x;
    }

    public Firefly(double min, double max) {
        x = min + (max - min) * Math.random();
        y = min + (max - min) * Math.random();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}
