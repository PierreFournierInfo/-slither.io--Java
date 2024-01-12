package com.poo.slither.network;

import com.poo.slither.model.Jeu;
import com.poo.slither.model.Serpent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server gameServer;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    private final Jeu game;

    public ClientHandler(Socket clientSocket, Server gameServer, Jeu jeu) throws IOException {
        this.clientSocket = clientSocket;
        this.gameServer = gameServer;
        this.game = jeu;
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        initPlayer();
        startSendGameLoop();
    }

    private void startSendGameLoop() {
        try {
            while(clientSocket.isConnected()) {
                Jeu jeu = (Jeu) inputStream.readObject();
                gameServer.broadcastGame(this, jeu);
            }
        } catch (IOException | ClassNotFoundException e) {
            closeConnection();
        }
    }

    private void initPlayer() {
        Serpent serpent = new Serpent(100, 100);
        game.addSerpent(serpent);
        try {
            // envoyer le jeu pour le client
            outputStream.writeObject(game);
        } catch (IOException e) {
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void receiveGame(ClientHandler sender, Jeu jeu) {
        if(sender == this) return;
        try {
            outputStream.writeObject(jeu);
            outputStream.flush();
        } catch (IOException | ClassCastException e) {
            closeConnection();
        }
    }

    public void receiveServerGame(Jeu game) {
        try {
            outputStream.writeObject(game);
            outputStream.flush();
        } catch (IOException | ClassCastException e) {
            closeConnection();
        }
    }
}
