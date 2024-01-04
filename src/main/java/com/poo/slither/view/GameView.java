package com.poo.slither.view;

import com.poo.slither.model.Jeu;
import com.poo.slither.model.Nourriture;
import com.poo.slither.model.Segment;
import com.poo.slither.model.Serpent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameView {
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    private final Canvas canvas;
    private final GraphicsContext context;
    private final Jeu jeu;

    public GameView(Canvas canvas, Jeu jeu) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.jeu = jeu;
    }

    public void dessineJeu() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Serpent serpent : jeu.getSerpents()) {
            dessineSerpent(serpent);
        }

        for (Nourriture food : jeu.getFood()) {
            dessineNourriture(food);
        }
    }

    private void dessineSerpent(Serpent serpent) {
        double offsetX = canvas.getWidth() / 2 - jeu.getSerpents().get(0).getTete().getX();
        double offsetY = canvas.getHeight() / 2 - jeu.getSerpents().get(0).getTete().getY();

        for (Segment segment : serpent.getSegments()) {
            double x = segment.getX() + offsetX;
            double y = segment.getY() + offsetY;

            if (isInVisiblePortion(x, y)) {
                SegmentView vueSegment = new SegmentView(segment);
                vueSegment.dessineSegment(context, offsetX, offsetY);
            }
        }
    }

    private boolean isInVisiblePortion(double x, double y) {
        return x >= 0 && x <= WIDTH && y >= 0 && y <= HEIGHT;
    }

    private void dessineNourriture(Nourriture food) {
        double offsetX = canvas.getWidth() / 2 - jeu.getSerpents().get(0).getTete().getX();
        double offsetY = canvas.getHeight() / 2 - jeu.getSerpents().get(0).getTete().getY();

        NourritureView vueNourriture = new NourritureView(food);
        vueNourriture.dessineNourriture(context, offsetX, offsetY);
    }
}
