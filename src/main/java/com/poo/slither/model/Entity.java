package com.poo.slither.model;

import javafx.geometry.Point2D;

/**
 * Base class representing an entity in the Slither game.
 */
public class Entity {

    /**
     * The position of the entity.
     */
    protected Point2D position;

    /**
     * Constructs an entity with the specified initial coordinates.
     *
     * @param x The initial x-coordinate of the entity.
     * @param y The initial y-coordinate of the entity.
     */
    public Entity(double x, double y) {
        this.position = new Point2D(x, y);
    }

    public Entity() {
        this.position = new Point2D(0, 0);
    }

    /**
     * Get the x-coordinate of the entity.
     *
     * @return The x-coordinate of the entity.
     */
    public double getX() {
        return position.getX();
    }

    /**
     * Get the y-coordinate of the entity.
     *
     * @return The y-coordinate of the entity.
     */
    public double getY() {
        return position.getY();
    }

    /**
     * Move the entity to the specified coordinates.
     *
     * @param x The new x-coordinate of the entity.
     * @param y The new y-coordinate of the entity.
     */
    public void moveTo(double x, double y) {
        position = new Point2D(x, y);
    }
}