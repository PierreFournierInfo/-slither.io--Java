package com.poo.slither.network;

import com.poo.slither.controller.SnakeController;
import com.poo.slither.model.Jeu;
import com.poo.slither.model.Nourriture;
import com.poo.slither.model.Serpent;
import com.poo.slither.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    private final Jeu game;
    private Scene scene;
    private GameView gameView;
    private final Serpent serpent;

    public Client(int port) {
        try {
            socket = new Socket("localhost", port);
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.game = (Jeu) inputStream.readObject();
            this.serpent = game.getSerpents().get(game.getSerpents().size() - 1);
            new Thread(() -> {
                startReadGameLoop();
                startWriteGameLoop();
            }).start();
            new Thread(this::startGameLoop).start();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void startGameLoop() {
        this.gameView = new GameView(game, serpent);
        scene = new Scene(gameView);
        new SnakeController(serpent, scene);
        KeyFrame k = new KeyFrame(Duration.seconds(1.0 / 60.0), event -> {
            if(!game.getSerpents().isEmpty()) {
                game.updateGame();
                gameView.renderGame();
            }
        });

        Timeline timeline = new Timeline(k);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public Scene getScene() {
        return scene;
    }

    private void startWriteGameLoop() {
        while (socket.isConnected()) {
            try {
                outputStream.writeObject(game);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startReadGameLoop() {
        new Thread(() -> {
            try {
                while(socket.isConnected()) {
                    Jeu jeu = (Jeu) inputStream.readObject();
                    updateGame(jeu);
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private synchronized void updateGame(Jeu serverUpdate) {
        this.game.clearFood();
        this.game.clearSerpents();
        for(Serpent serpent : serverUpdate.getSerpents()) {
            this.game.addSerpent(serpent);
        }
        for(Nourriture nourriture : serverUpdate.getFood()) {
            this.game.addNourriture(nourriture);
        }
    }
}
