package com.poo.slither.model;

public class SegmentBouclierFactory implements SegmentFactory {

    @Override
    public Segment createSegment(double x, double y) {
        return new SegmentBouclier(x, y);
    }
}
