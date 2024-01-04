package com.poo.slither.model;

import static com.poo.slither.view.SegmentView.SEGMENT_SIZE;

public class CollisionUtils {
    public static Segment collisionSerpents(Serpent snakeA, Serpent snakeB) {
        if(snakeA == null || snakeB == null) return null;
        Segment headA = snakeA.getTete();

        for (Segment segment : snakeB.getSegments()) {
            if (collisionSegments(headA, segment)) {
                return segment;
            }
        }

        return null;
    }

    public static boolean collisionNourriture(Segment segmentNormal, Nourriture nourriture) {
        if(segmentNormal == null || nourriture == null) return false;
        double distance = Math.sqrt(Math.pow(segmentNormal.getX() - nourriture.getX(), 2) + Math.pow(segmentNormal.getY() - nourriture.getY(), 2));
        return distance < SEGMENT_SIZE;
    }
    public static boolean collisionSegments(Segment segmentNormalA, Segment segmentNormalB) {
        if(segmentNormalA == null || segmentNormalB == null) return false;

        double distance = Math.sqrt(Math.pow(segmentNormalA.getX() - segmentNormalB.getX(), 2)
                + Math.pow(segmentNormalA.getY() - segmentNormalB.getY(), 2));

        return distance < SEGMENT_SIZE;
    }
}
