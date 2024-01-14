package com.poo.slither.model;

public final class SegmentFaibleFactory implements SegmentFactory{
    @Override
    public Segment createSegment(double x, double y) {
        return new SegmentFaible(x, y);
    }
}
