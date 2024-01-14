package com.poo.slither.model;

import javafx.scene.paint.Color;

public final class NourritureVitesse extends Entity implements Nourriture{
    public NourritureVitesse(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }

    @Override
    public double getRadius() {
        return 7;
    }

    @Override
    public boolean applyEffect(Serpent serpent) {
        serpent.gainSpeedPoints();
        return false;
    }
}
