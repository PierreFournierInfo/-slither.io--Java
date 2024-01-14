package com.poo.slither.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Interface representing nourishment in the Slither game.
 */
public interface Nourriture extends Serializable {

    /**
     * Get the x-coordinate of the nourishment.
     *
     * @return The x-coordinate of the nourishment.
     */
    double getX();

    /**
     * Get the y-coordinate of the nourishment.
     *
     * @return The y-coordinate of the nourishment.
     */
    double getY();

    /**
     * Get the color of the nourishment.
     *
     * @return The color of the nourishment.
     */
    Color getColor();

    /**
     * Get the radius of the nourishment.
     *
     * @return The radius of the nourishment.
     */
    double getRadius();

    /**
     * Apply the effect of the nourishment on a snake.
     *
     * @param serpent The snake on which the nourishment's effect is applied.
     * @return True if the nourishment has killed the snake, false otherwise.
     */
    boolean applyEffect(Serpent serpent);
}

