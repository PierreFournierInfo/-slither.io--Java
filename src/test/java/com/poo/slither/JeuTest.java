package com.poo.slither;

import com.poo.slither.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.poo.slither.model.Serpent.DEFAULT_SIZE;
import static org.junit.jupiter.api.Assertions.*;

public class JeuTest {

    private Jeu jeu;

    @BeforeEach
    public void setUp() {
        jeu = new Jeu(2, 5);
    }

    @Test
    public void testUpdateGame_SerpentMoves() {
        Serpent controlledSerpent = new Serpent(100, 100);
        jeu.addSerpent(controlledSerpent);

        double initialX = controlledSerpent.getTete().getX();
        double initialY = controlledSerpent.getTete().getY();

        jeu.updateGame();

        assertNotEquals(initialX, controlledSerpent.getTete().getX());
        assertEquals(initialY, controlledSerpent.getTete().getY());
    }

    @Test
    public void testUpdateGame_CollisionWithFood() {
        Serpent controlledSerpent = new Serpent(100, 100);
        jeu.addSerpent(controlledSerpent);
        NourritureSimple food = new NourritureSimple(110, 100);
        jeu.addNourriture(food);

        int nb_food = jeu.getFood().size();

        jeu.updateGame();

        assertEquals(nb_food-1, jeu.getFood().size()); // The food should be eaten
        assertEquals(DEFAULT_SIZE + 1, controlledSerpent.getSegments().size()); // The serpent should have grown
    }

    @Test
    public void testUpdateGame_CollisionWithIA() {
        Serpent controlledSerpent = new Serpent(100, 100);
        jeu.addSerpent(controlledSerpent);
        SerpentIA aiSerpent = new SerpentIA(110, 100);
        jeu.addSerpent(aiSerpent);

        jeu.updateGame();

        assertTrue(controlledSerpent.isAlive());
        assertFalse(aiSerpent.isAlive());
    }

    @Test
    public void testUpdateGame_AIControlledSerpentMoves() {
        List<Serpent> serpents = jeu.getSerpents();
        SerpentIA aiSerpent = null;
        for (Serpent serpent : serpents) {
            if (serpent instanceof SerpentIA) {
                aiSerpent = (SerpentIA) serpent;
                break;
            }
        }
        assertNotNull(aiSerpent);

        double initialX = aiSerpent.getTete().getX();
        double initialY = aiSerpent.getTete().getY();

        jeu.updateGame();

        assertNotEquals(initialX, aiSerpent.getTete().getX());
        assertNotEquals(initialY, aiSerpent.getTete().getY());
    }
}

