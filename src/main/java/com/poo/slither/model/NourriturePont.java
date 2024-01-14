package com.poo.slither.model;

import javafx.scene.paint.Color;

public final class NourriturePont extends Entity implements Nourriture {
    public NourriturePont(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.GREY;
    }

    @Override
    public double getRadius() {
        return 9;
    }

    @Override
    public boolean applyEffect(Serpent serpent) {
        serpent.setSegmentFactory(new SegmentFaibleFactory());
        serpent.ajouteUnSegment();
        return false;
    }
}
