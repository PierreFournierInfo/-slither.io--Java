package com.poo.slither.view;

import com.poo.slither.model.Jeu;
import com.poo.slither.model.Nourriture;
import com.poo.slither.model.Segment;
import com.poo.slither.model.Serpent;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import static com.poo.slither.model.Jeu.MAP_HEIGHT;
import static com.poo.slither.model.Jeu.MAP_WIDTH;

public class GameView extends Pane {
    private static Rectangle2D bounds = Screen.getPrimary().getBounds();
    public static int WIDTH = (int) bounds.getWidth();
    public static int HEIGHT = (int) bounds.getHeight();
    private final Canvas canvas;
    private final GraphicsContext context;
    private final Jeu jeu;
    private final Serpent camera;

    public GameView(Jeu jeu, Serpent serpent) {
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.context = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
        this.jeu = jeu;
        this.camera = serpent;
    }

    public GameView(int width, int height, Jeu jeu, Serpent serpent) {
        WIDTH = width;
        HEIGHT = height;
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.context = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
        this.jeu = jeu;
        this.camera = serpent;
    }

    public void renderGame() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Dessiner les bords de la map
        Segment tete = camera.getTete();
        if(tete == null) return;
        double offsetX = canvas.getWidth() / 2 - tete.getX();
        double offsetY = canvas.getHeight() / 2 - tete.getY();
        context.setFill(Color.BLACK);
        context.fillRect(offsetX, offsetY, MAP_WIDTH, MAP_HEIGHT);

        for (Serpent serpent : jeu.getSerpents()) {
            dessineSerpent(serpent);
        }

        for (Nourriture food : jeu.getFood()) {
            dessineNourriture(food);
        }
    }

    private void dessineSerpent(Serpent serpent) {
        Segment tete = camera.getTete();
        if(tete == null) return;
        double offsetX = canvas.getWidth() / 2 - tete.getX();
        double offsetY = canvas.getHeight() / 2 - tete.getY();

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
        Segment tete = camera.getTete();
        if(tete == null) return;
        double offsetX = canvas.getWidth() / 2 - tete.getX();
        double offsetY = canvas.getHeight() / 2 - tete.getY();

        NourritureView vueNourriture = new NourritureView(food);
        vueNourriture.dessineNourriture(context, offsetX, offsetY);
    }
}
