package com.poo.slither.model;

import javafx.scene.paint.Color;

import static com.poo.slither.model.Jeu.MAP_HEIGHT;
import static com.poo.slither.model.Jeu.MAP_WIDTH;

public final class SegmentNormal extends Entity implements Segment {
    public SegmentNormal(double x, double y) {
        super(x, y);
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public boolean handelCollision(Serpent serpent1, Serpent serpent2) {
        Segment tete1 = serpent1.getTete();
        if(tete1 instanceof SegmentBouclier) {
            serpent1.enlevePremierSegment();
            serpent1.getTete().moveTo(MAP_WIDTH/2., MAP_HEIGHT/2.);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Nourriture toFood() {
        return new NourritureSimple(getX(), getY());
    }

}