package com.poo.slither.model;

import javafx.scene.paint.Color;

public class NourritureSimple extends Entity implements Nourriture {
    public NourritureSimple(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.PURPLE;
    }

    @Override
    public double getRadius() {
        return 10;
    }

    @Override
    public boolean applyEffect(Serpent serpent) {
        serpent.setSegmentFactory(new SegmentNormalFactory());
        serpent.ajouteUnSegment();
        return false;
    }
}
