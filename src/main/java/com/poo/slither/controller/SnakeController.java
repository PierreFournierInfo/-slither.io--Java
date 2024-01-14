package com.poo.slither.controller;

import com.poo.slither.model.Segment;
import com.poo.slither.model.Serpent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import static com.poo.slither.view.GameView.*;

public class SnakeController {
    private final Serpent serpent;
    private boolean movingUp, movingDown, movingLeft, movingRight;
    private double deltaX = 1, deltaY = 0;
    private Timeline speedDecrementTimeline;

    public SnakeController(Serpent serpent, Scene scene) {
        this.serpent = serpent;
        if(serpent.isAlive()) {
            scene.setOnKeyPressed(this::handleKeyPress);
            scene.setOnKeyReleased(this::handleKeyRelease);
            scene.setOnMouseMoved(this::mouvementSouris);
            scene.setOnMouseDragged(this::mouvementSouris);
            scene.setOnMousePressed(this::handleMousePress);
            scene.setOnMouseReleased(this::handleMouseRelease);
        }
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();

        switch (code) {
            case UP:
                movingUp = true;
                break;
            case DOWN:
                movingDown = true;
                break;
            case LEFT:
                movingLeft = true;
                break;
            case RIGHT:
                movingRight = true;
                break;
        }

        updateDirection();
    }

    private void handleKeyRelease(KeyEvent event) {
        KeyCode code = event.getCode();

        switch (code) {
            case UP:
                movingUp = false;
                break;
            case DOWN:
                movingDown = false;
                break;
            case LEFT:
                movingLeft = false;
                break;
            case RIGHT:
                movingRight = false;
                break;
        }

        updateDirection();
    }

    private void updateDirection() {
        if (movingUp) {
            deltaY = -1;
        } else if (movingDown) {
            deltaY = 1;
        }

        if (movingLeft) {
            deltaX = -1;
        } else if (movingRight) {
            deltaX = 1;
        }

        double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (length != 0) {
            deltaX /= length;
            deltaY /= length;
        }

        serpent.setDirection(deltaX, deltaY);
    }

    private void mouvementSouris(MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        Segment tete = serpent.getTete();
        if(tete == null) return;
        double headX = tete.getX();
        double headY = tete.getY();
        double mouseWorldX = mouseX - (double) WIDTH / 2  + headX;
        double mouseWorldY = mouseY - (double) HEIGHT / 2 + headY;

        double deltaX = mouseWorldX - headX;
        double deltaY = mouseWorldY - headY;

        double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (length != 0) {
            deltaX /= length;
            deltaY /= length;
        }
        serpent.setDirection(deltaX, deltaY);
    }

    private void handleMousePress(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            serpent.increaseSpeed();
            startSpeedDecrement();
        }
    }

    private void handleMouseRelease(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            serpent.resetSpeedToDefault();
            stopSpeedDecrement();
        }
    }

    private void startSpeedDecrement() {
        speedDecrementTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.3), event -> {
                    serpent.removeSpeedPoints();
                    if(serpent.getSpeedPoints() <= 0) serpent.resetSpeedToDefault();
                })
        );
        speedDecrementTimeline.setCycleCount(Timeline.INDEFINITE);
        speedDecrementTimeline.play();
    }

    public void stopSpeedDecrement() {
        if (speedDecrementTimeline != null) {
            speedDecrementTimeline.stop();
        }
    }
}
