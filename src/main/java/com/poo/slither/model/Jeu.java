package com.poo.slither.model;

import java.util.ArrayList;
import java.util.List;

public class Jeu {
    private final List<Serpent> serpents;
    private final List<Nourriture> nourritures;
    public static final int NB_FOOD = 1000;

    public Jeu() {
        this.serpents = new ArrayList<>();
        this.nourritures = new ArrayList<>();
    }

    public void addSerpent(Serpent serpent) {
        serpents.add(serpent);
    }
    public void removeSerpent(Serpent serpent) {
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
}
