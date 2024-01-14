package com.poo.slither.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Serpent implements Serializable {
    public static final int DEFAULT_SIZE = 10;
    private final List<Segment> serpent;
    private SegmentFactory segmentFactory;
    private double deltaX, deltaY;
    protected double speed;
    private int speedPoints;

    public Serpent(double x, double y) {
        this.segmentFactory = new SegmentNormalFactory();
        serpent = new ArrayList<>();
        for(int i = 0; i < DEFAULT_SIZE; i++) {
            serpent.add(segmentFactory.createSegment(x + i, y));
        }
        this.deltaX = 1;
        this.speed = 3;
    }

    public List<Segment> getSegments() {
        return new ArrayList<>(serpent);
    }

    public void setDirection(double deltaX, double deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public void bouge() {
        for(int i = serpent.size() - 1; i > 0; i--) {
            Segment cour = serpent.get(i);
            Segment prec = serpent.get(i - 1);
            cour.moveTo(prec.getX(), prec.getY());
        }

        Segment tete = serpent.get(0);
        tete.moveTo(tete.getX() + deltaX * speed, tete.getY() + deltaY * speed);
    }

    public Segment getTete() {
        try {
            return serpent.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    void setSegmentFactory(SegmentFactory segmentFactory) {
        this.segmentFactory = segmentFactory;
    }

    void ajouteUnSegment() {
        Segment head = serpent.get(0);
        Segment toAdd = segmentFactory.createSegment(head.getX(), head.getY());

        for (int i = 0; i < serpent.size()-1; i++) {
            Segment cour = serpent.get(i);
            Segment next = serpent.get(i + 1);
            cour.moveTo(next.getX(), next.getY());
        }

        Segment last = serpent.get(serpent.size() - 1);
        double newX = last.getX() + deltaX * -1;
        double newY = last.getY() + deltaY * -1;
        last.moveTo(newX, newY);

        serpent.add(0, toAdd);
    }

    public boolean isAlive() {
        return !serpent.isEmpty();
    }

    public void meurt() {
        serpent.clear();
    }

    public void increaseSpeed() {
        if(speedPoints > 0)
            speed = 5;
    }

    public double getSpeed() {
        return speed;
    }

    public void resetSpeedToDefault() {
        speed = 3;
    }

    public int getSpeedPoints() {
        return speedPoints;
    }

    public void gainSpeedPoints() {
        speedPoints += 1;
    }

    public void removeSpeedPoints() {
        if(speedPoints > 0)
            speedPoints -= 1;
    }

    boolean enlevePremierSegment() {
        if(serpent.isEmpty()) return true;
        serpent.remove(0);
        return serpent.isEmpty();
    }

    boolean cutFrom(SegmentFaible segmentFaible) {
        int i;
        for(i = 0; i < serpent.size(); i++) {
            Segment segment = serpent.get(i);
            if(segment.getX() == segmentFaible.getX() && segment.getY() == segmentFaible.getY()) {
                break;
            }
        }

        if (serpent.size() > i) {
            serpent.subList(i, serpent.size()).clear();
        }
        return isAlive();
    }
}
