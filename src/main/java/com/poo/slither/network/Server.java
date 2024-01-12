package com.poo.slither.network;

import com.poo.slither.model.Jeu;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final List<ClientHandler> clients = new ArrayList<>();
    private final Jeu game;
    private final ServerSocket serverSocket;
    public static final int PORT = 12345;
    private final ExecutorService pool;

    public Server(Jeu game, ServerSocket serverSocket) {
        this.game = game;
        this.serverSocket = serverSocket;
        this.pool = Executors.newFixedThreadPool(200);
        startServer();
    }

    private void startServer() {
        System.out.println("Server waiting for connections...");
        try {
            while(!serverSocket.isClosed()) {
                System.out.println("Waiting for new client.");
                final Socket socket = serverSocket.accept();
                System.out.println("A new client has connected.");
                final ClientHandler clientHandler = new ClientHandler(socket, this, game);
                clients.add(clientHandler);
                System.out.println("A new client handler has been made and added.");
                pool.execute(clientHandler);
                System.out.println("A new client handler has been started.");
                sendServerGame(game);
                System.out.println("Server game sent to client");
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    private synchronized void sendServerGame(Jeu game) {
        for(ClientHandler clientHandler : clients) {
            clientHandler.receiveServerGame(game);
        }
        System.out.println("Server Game has been broadcast.");
    }

    public synchronized void broadcastGame(ClientHandler sender, Jeu jeu) {
        for (ClientHandler client : clients) {
            client.receiveGame(sender, jeu);
        }
        System.out.println("Game has been broadcast.");
    }


    public static void main(String[] args) throws IOException {
        new Server(new Jeu(0, 1000), new ServerSocket(PORT));
    }
}
