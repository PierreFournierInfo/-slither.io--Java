package com.poo.slither;

import com.poo.slither.model.Serpent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SerpentTest {
    private Serpent serpent;

    @BeforeEach
    public void setUp() {
        serpent = new Serpent(0, 0);
    }

    @Test
    public void testBouge() {
        serpent.setDirection(1, 0);
        serpent.bouge();
        assertEquals(1 * serpent.getSpeed(), serpent.getTete().getX(), 0.001);
        assertEquals(0, serpent.getTete().getY(), 0.001);
    }

    @Test
    public void testIsAlive() {
        assertTrue(serpent.isAlive());
        serpent.meurt();
        assertFalse(serpent.isAlive());
    }

    @Test
    public void testIncreaseSpeed() {
        serpent.increaseSpeed();
        // Le serpent n'a pas des points de vitesse
        assertEquals(3, serpent.getSpeed(), 0.001);
    }

    @Test
    public void testIncreaseSpeed2() {
        serpent.gainSpeedPoints();
        serpent.increaseSpeed();
        assertEquals(5, serpent.getSpeed(), 0.001);
    }

    @Test
    public void testResetSpeedToDefault() {
        serpent.gainSpeedPoints();
        serpent.increaseSpeed();
        serpent.resetSpeedToDefault();
        assertEquals(3, serpent.getSpeed(), 0.001);
    }

    @Test
    public void testGainSpeedPoints() {
        serpent.gainSpeedPoints();
        assertEquals(1, serpent.getSpeedPoints());
    }

    @Test
    public void testRemoveSpeedPoints() {
        serpent.gainSpeedPoints();
        serpent.removeSpeedPoints();
        assertEquals(0, serpent.getSpeedPoints());
    }
}
