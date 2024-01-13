package com.poo.slither.model;

import javafx.scene.paint.Color;

public final class SegmentFaible extends Entity implements Segment {

    public SegmentFaible(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.BURLYWOOD;
    }

    @Override
    public boolean handelCollision(Serpent serpent1, Serpent serpent2) {
        return serpent1.cutFrom(this);
    }

    @Override
    public Nourriture toFood() {
        return new NourriturePont(getX(), getY());
    }
}
