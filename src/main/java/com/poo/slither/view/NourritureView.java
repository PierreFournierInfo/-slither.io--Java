package com.poo.slither.view;

import com.poo.slither.model.Nourriture;
import javafx.scene.canvas.GraphicsContext;

public class NourritureView {
    private final Nourriture nourriture;

    public NourritureView(Nourriture nourriture) {
        this.nourriture = nourriture;
    }

    public void dessineNourriture(GraphicsContext gc, double offsetX, double offsetY) {
        gc.setFill(nourriture.getColor());
        double r = nourriture.getRadius();

        double x = nourriture.getX() + offsetX - r / 2;
        double y = nourriture.getY() + offsetY - r / 2;

        gc.fillOval(x, y, r, r);
    }
}
