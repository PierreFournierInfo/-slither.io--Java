package com.poo.slither;

import com.poo.slither.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionUtilsTest {

    @Test
    public void testCollisionSerpents() {
        Serpent snakeA = new Serpent(0, 0);
        Serpent snakeB = new Serpent(10, 10);

        assertNotNull(CollisionUtils.collisionSerpents(snakeA, snakeB));
    }

    @Test
    public void testCollisionSerpents_Collision() {
        Serpent snakeA = new Serpent(0, 0);
        Serpent snakeB = new Serpent(1, 0);

        Segment collisionSegment = CollisionUtils.collisionSerpents(snakeA, snakeB);

        assertNotNull(collisionSegment);
        assertEquals(snakeB.getSegments().get(0), collisionSegment);
    }

    @Test
    public void testCollisionSerpents_NullInput() {
        assertNull(CollisionUtils.collisionSerpents(null, null));
    }

    @Test
    public void testCollisionNourriture() {
        Segment segment = new SegmentNormal(0, 0);
        Nourriture nourriture = new NourritureBouclier(10, 10);

        assertTrue(CollisionUtils.collisionNourriture(segment, nourriture));
    }

    @Test
    public void testCollisionNourriture_Collision() {
        Segment segment = new SegmentNormal(0, 0);
        Nourriture nourriture = new NourritureSimple(1, 0);

        assertTrue(CollisionUtils.collisionNourriture(segment, nourriture));
    }

    @Test
    public void testCollisionNourriture_NullInput() {
        assertFalse(CollisionUtils.collisionNourriture(null, null));
    }

    @Test
    public void testCollisionSegments() {
        Segment segmentA = new SegmentNormal(0, 0);
        Segment segmentB = new SegmentBouclier(10, 10);

        assertTrue(CollisionUtils.collisionSegments(segmentA, segmentB));
    }

    @Test
    public void testCollisionSegments_Collision() {
        Segment segmentA = new SegmentFaible(0, 0);
        Segment segmentB = new SegmentFaible(1, 0);

        assertTrue(CollisionUtils.collisionSegments(segmentA, segmentB));
    }

    @Test
    public void testCollisionSegments_NullInput() {
        assertFalse(CollisionUtils.collisionSegments(null, null));
    }
}

