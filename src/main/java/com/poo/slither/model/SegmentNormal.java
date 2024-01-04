package com.poo.slither.model;

import javafx.scene.paint.Color;

public class SegmentNormal extends Entity implements Segment {
    public SegmentNormal(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public boolean handelCollision(Serpent serpent1, Serpent serpent2) {
        return false;
    }

    @Override
    public Nourriture toFood() {
        return new NourritureSimple(getX(), getY());
    }

}