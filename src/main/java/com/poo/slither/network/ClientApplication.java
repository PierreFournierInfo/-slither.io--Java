package com.poo.slither.network;

import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) {
        Client client = new Client(Server.PORT);
        stage.setScene(client.getScene());
        stage.setTitle("Réseau");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
