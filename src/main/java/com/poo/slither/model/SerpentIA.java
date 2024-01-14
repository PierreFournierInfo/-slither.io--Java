package com.poo.slither.model;

import java.util.Random;

import static com.poo.slither.model.Jeu.MAP_HEIGHT;
import static com.poo.slither.model.Jeu.MAP_WIDTH;

public final class SerpentIA extends Serpent {
    private double deltaX, deltaY;
    public SerpentIA(double x, double y) {
        super(x, y);
        chooseRandomDirection();
    }

    @Override
    public void bouge() {
        for (int i = getSegments().size() - 1; i > 0; i--) {
            Segment current = getSegments().get(i);
            Segment previous = getSegments().get(i - 1);
            current.moveTo(previous.getX(), previous.getY());
        }

        Segment head = getSegments().get(0);
        double newX = head.getX() + deltaX * speed;
        double newY = head.getY() + deltaY * speed;

        if (newX < 100 || newX > MAP_WIDTH - 100 ||
                newY < 100 || newY > MAP_HEIGHT - 100) {
            chooseRandomDirection();
        }

        head.moveTo(newX, newY);

        if (Math.random() < 0.005) {
            chooseRandomDirection();
        }
    }

    private void chooseRandomDirection() {
        Random random = new Random();
        double angle = random.nextDouble() * 2 * Math.PI;
        deltaX = Math.cos(angle);
        deltaY = Math.sin(angle);
    }
}
