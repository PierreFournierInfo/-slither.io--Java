package com.poo.slither.model;

import javafx.geometry.Point2D;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.poo.slither.model.CollisionUtils.collisionSerpents;

public class Jeu implements Serializable {
    private final List<Serpent> serpents;
    private final List<Nourriture> nourritures;
    private final int nb_food;
    private final int nb_ia;
    public Jeu(int nb_ai, int nb_food) {
        this.serpents = new ArrayList<>();
        this.nourritures = new ArrayList<>();
        this.nb_food = nb_food;
        this.nb_ia = nb_ai;
        genereNourritures();
        genereIAs();
    }

    public void addSerpent(Serpent serpent) {
        serpents.add(0, serpent);
    }
    private void removeSerpent(Serpent serpent) {
        serpents.remove(serpent);
    }

    public void addNourriture(Nourriture nourriture) {
        nourritures.add(nourriture);
    }

    public List<Serpent> getSerpents() {
        return new ArrayList<>(serpents);
    }

    public List<Nourriture> getFood() {
        return nourritures;
    }

    private void genereNourritures() {
        for (int i = 0; i < nb_food; i++) {
            addNourriture();
        }
    }

    private void genereIAs() {
        for(int i = 0; i < nb_ia; i++) {
            Random random = new Random();
            double x = random.nextDouble() * 3000;
            double y = random.nextDouble() * 3000;
            SerpentIA serpentIA = new SerpentIA(x, y);
            addSerpent(serpentIA);
        }
    }

    private void addNourriture() {
        Random random = new Random();
        double x = random.nextDouble() * 3000;
        double y = random.nextDouble() * 3000;
        Nourriture nourriture;

        int randomType = new Random().nextInt(5);

        nourriture = switch (randomType) {
            case 0 -> new NourritureSimple(x, y);
            case 1 -> new NourritureVitesse(x, y);
            case 2 -> new NourriturePoison(x, y);
            case 3 -> new NourritureBouclier(x, y);
            case 4 -> new NourriturePont(x, y);
            default -> throw new IllegalStateException("Unexpected value: " + randomType);
        };

        addNourriture(nourriture);
    }

    public List<Serpent> updateGame() {
        moveSerpents();
        checkCollisions();
        return updateSerpents();
    }

    private List<Serpent> updateSerpents() {
        List<Serpent> dead = new ArrayList<>();
        for(Serpent serpent : getSerpents()) {
            if(!serpent.isAlive()) {
                removeSerpent(serpent);
                dead.add(serpent);
            }
        }
        return dead;
    }

    private void moveSerpents() {
        for(Serpent serpent : getSerpents()) {
            if(serpent.isAlive())
                serpent.bouge();
        }
    }

    private void checkCollisions() {
        collisionsSerpents();
        collisionsNourritures();
    }

    private void collisionsNourritures() {
        for (Serpent serpent : getSerpents()) {
            List<Nourriture> eatenFood = new ArrayList<>();
            boolean isDead = false;
            for (Nourriture food : getFood()) {
                if (CollisionUtils.collisionNourriture(serpent.getTete(), food)) {
                    isDead = food.applyEffect(serpent);
                    eatenFood.add(food);
                }
            }
            if(isDead) {
                serpent.meurt();
                removeSerpent(serpent);
            }
            nourritures.removeAll(eatenFood);
        }
    }

    private void collisionsSerpents() {
        for (int i = 0; i < getSerpents().size(); i++) {
            Serpent snakeA = getSerpents().get(i);

            for (int j = 0; j < getSerpents().size(); j++) {
                if(j != i) {
                    Serpent snakeB = getSerpents().get(j);
                    Segment segmentVictime = collisionSerpents(snakeA, snakeB);
                    if (segmentVictime != null) {
                        System.out.println("Collision serpents");
                        boolean isDead = segmentVictime.handelCollision(snakeA, snakeB);
                        if(isDead) {
                            for(Segment segment : snakeA.getSegments()) {
                                addNourriture(segment.toFood());
                            }
                            snakeA.meurt();
                        }
                    }
                }
            }
        }
    }

    public void clearFood() {
    }

    public void clearSerpents() {
    }
}
