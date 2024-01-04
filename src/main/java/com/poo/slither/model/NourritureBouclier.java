package com.poo.slither.model;

import javafx.scene.paint.Color;

public class NourritureBouclier extends Entity implements Nourriture{
    public NourritureBouclier(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.GOLD;
    }

    @Override
    public double getRadius() {
        return 12;
    }

    @Override
    public boolean applyEffect(Serpent serpent) {
        serpent.setSegmentFactory(new SegmentBouclierFactory());
        serpent.ajouteUnSegment();
        return false;
    }
}

