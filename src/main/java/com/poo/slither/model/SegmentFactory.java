package com.poo.slither.model;

/**
 * Interface representing a factory for creating segments in the Slither game.
 */
public interface SegmentFactory {

    /**
     * Create a segment with the specified coordinates.
     *
     * @param x The x-coordinate of the new segment.
     * @param y The y-coordinate of the new segment.
     * @return A new segment with the specified coordinates.
     */
    Segment createSegment(double x, double y);
}

