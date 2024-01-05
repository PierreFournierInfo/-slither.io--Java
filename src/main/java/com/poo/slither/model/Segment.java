package com.poo.slither.model;

import javafx.scene.paint.Color;

import java.io.Serializable;


/**
 * Interface representing a segment of a snake in the Slither game.
 */
public interface Segment extends Serializable {

    /**
     * Get the x-coordinate of the segment.
     *
     * @return The x-coordinate of the segment.
     */
    double getX();

    /**
     * Get the y-coordinate of the segment.
     *
     * @return The y-coordinate of the segment.
     */
    double getY();

    /**
     * Move the segment to the specified coordinates.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    void moveTo(double x, double y);

    /**
     * Get the color of the segment.
     *
     * @return The color of the segment.
     */
    Color getColor();

    /**
     * Handle collision with two snakes.
     *
     * @param serpent1 The first snake involved in the collision.
     * @param serpent2 The second snake involved in the collision.
     * @return True if the collision was handled successfully, false otherwise.
     */
    boolean handelCollision(Serpent serpent1, Serpent serpent2);

    /**
     * Convert the segment to a nourishment item.
     *
     * @return A nourishment item representing the segment.
     */
    Nourriture toFood();
}

