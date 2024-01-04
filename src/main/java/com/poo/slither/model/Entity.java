package com.poo.slither.model;

import javafx.geometry.Point2D;

public class Entity {
    protected Point2D position;

    public Entity(double x, double y) {
        this.position = new Point2D(x, y);
    }

    public double getX() {return position.getX();}
    public double getY() {return position.getY();}

    public void moveTo(double x, double y) {
        position = new Point2D(x, y);
    }
}
