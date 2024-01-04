package com.poo.slither;

import com.poo.slither.model.SerpentIA;
import com.poo.slither.model.Segment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SerpentIATest {
    @Test
    public void testInitialDirection() {
        SerpentIA serpentIA = new SerpentIA(0, 0);
        serpentIA.bouge();
        // Check if deltaX and deltaY are initialized
        assertTrue(serpentIA.getSegments().get(0).getX() != 0 || serpentIA.getTete().getY() != 0);
    }

    @Test
    public void testBougeWithRandomDirectionChange() {
        SerpentIA serpentIA = new SerpentIA(0, 0);

        double initialDeltaX = serpentIA.getSegments().get(0).getX();
        double initialDeltaY = serpentIA.getSegments().get(0).getY();

        for (int i = 0; i < 1000; i++) {
            serpentIA.bouge();
            if (initialDeltaX != serpentIA.getSegments().get(0).getX() || initialDeltaY != serpentIA.getSegments().get(0).getY()) {
                return; // Test passed
            }
        }

        fail("No direction change detected during multiple moves");
    }

    @Test
    public void testBougeSegmentsMoveProperly() {
        SerpentIA serpentIA = new SerpentIA(0, 0);

        double[] initialXPositions = serpentIA.getSegments().stream().mapToDouble(Segment::getX).toArray();
        double[] initialYPositions = serpentIA.getSegments().stream().mapToDouble(Segment::getY).toArray();

        serpentIA.bouge();

        for (int i = 1; i < serpentIA.getSegments().size(); i++) {
            assertEquals(initialXPositions[i - 1], serpentIA.getSegments().get(i).getX(), 0.001);
            assertEquals(initialYPositions[i - 1], serpentIA.getSegments().get(i).getY(), 0.001);
        }
    }
}
