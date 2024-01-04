package com.poo.slither.model;

import javafx.scene.paint.Color;

public class NourriturePoison extends Entity implements Nourriture {
    public NourriturePoison(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public double getRadius() {
        return 5;
    }

    @Override
    public boolean applyEffect(Serpent serpent) {
        return serpent.enlevePremierSegment();
    }
}

