package com.poo.slither;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.CycleMethod;

public class Menu extends Application{
    private static int nombreSerpents=10;
    private static int tailleSerpents=8;
    private static final int nombreSerpentsMin=5;
    private static final int nombreSerpentsMax=15;
    private static final int tailleSerpentsMin=5;
    private static final int nombreSerpentsMA=25;


    public static void main(String[] args) {
        launch(args);
    }

    public void start (Stage stage) throws Exception{
        stage.setScene(new Scene(createContent(stage)));
        stage.show();
    }

    private Parent createContent(Stage stage){
        Pane root=new Pane();
        root.setPrefSize(1280,720);


        Image fondMenu= new Image(getClass().getResourceAsStream("/sliterIo2.jpg"),
        1280,720,
        false,true
        );

        //bouton jouer un bouton exit et un bouton pour sélectionner le nombre de serpents IA 
        //et la taille de chacun des serpents mais essaie de limiter genre entre 5 et 15
        VBox box= new VBox(
            10,
            new BoutonMenu("JOUER",()->jouer(stage)),
            new BoutonMenu("NOMBRE DE SERPENTS",()->{}),
            new BoutonMenu("TAILLE DES SERPENTS",()->{}),
            new BoutonMenu("QUITTER",()->Platform.exit())

        );

        box.setTranslateX(50);
        box.setTranslateY(250);
        
        
        box.setBackground(new Background(
                new BackgroundFill(Color.web("black",0.5),null,null)
        ));
        

        root.getChildren().addAll( 
            new ImageView(fondMenu),
            box
            );
        
        return root;
        
    }

    private static class BoutonMenu extends StackPane{
        BoutonMenu (String name, Runnable action){
            LinearGradient gradient=new LinearGradient(
                0.0,0.5,1.0,0.5,true, CycleMethod.NO_CYCLE,
                 new Stop(0.1,Color.web("white",0.5)),
                 new Stop(1.0,Color.web("black",0.5))
            );

            Rectangle bouton=new Rectangle(300,40, gradient);


            Rectangle ligne=new Rectangle (7,40);
            ligne.fillProperty().bind(
                Bindings.when(hoverProperty())
                .then(Color.BLUEVIOLET).otherwise(Color.GRAY)
            );

            
            Text text=new Text(name);
            text.fillProperty().bind(
                Bindings.when(hoverProperty())
                .then(Color.WHITE).otherwise(Color.BLACK)
            );

            setAlignment(Pos.CENTER_LEFT);

            HBox box=new HBox (15, ligne, text);
            box.setAlignment(Pos.CENTER_LEFT);

            //Ajout controle des valeurs

            

            //Controles de Souris

            
            setOnMouseClicked(e->action.run());

            setOnMousePressed(e->bouton.setFill(Color.LIGHTGREY));

            setOnMouseReleased(e->bouton.setFill(gradient));

            getChildren().addAll(
                bouton,box
            );
            

        }
    }

    public void jouer(Stage stage){
        Slither app=new Slither(stage);
        app.start(stage);
    }
    
}
