package com.poo.slither.model;

public class SegmentNormalFactory implements SegmentFactory {
    @Override
    public Segment createSegment(double x, double y) {
        return new SegmentNormal(x, y);
    }
}
