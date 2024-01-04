package com.poo.slither.model;

import javafx.scene.paint.Color;

public interface Nourriture {
    double getX();
    double getY();
    Color getColor();
    double getRadius();
    /*
     * return whether the Food has killed the snake or not
     */
    boolean applyEffect(Serpent serpent);
}
