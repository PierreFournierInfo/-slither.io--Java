package com.poo.slither.model;

import javafx.scene.paint.Color;

public final class SegmentBouclier extends Entity implements Segment {
    public SegmentBouclier(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.DARKGREEN;
    }

    @Override
    public boolean handelCollision(Serpent serpent1, Serpent serpent2) {
        return false;
    }

    @Override
    public Nourriture toFood() {
        return new NourritureBouclier(getX(), getY());
    }
}
