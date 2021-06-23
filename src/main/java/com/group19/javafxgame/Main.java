package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends GameApplication {

    private int screenWidth = 600;
    private int screenHeight = 600;

    //sets up main programI
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene titleScene = new Scene(root, screenWidth, screenHeight);

        Button newButt = new Button("New Game");
        Button contButt = new Button("Continue");
        Button optButt = new Button("Options");
        Button leadButt = new Button("Leaderboard");
        Button exitButt = new Button("Exit");

        GridPane startGrid = new GridPane();
        startGrid.add(newButt, 0, 0);
        startGrid.add(contButt, 0, 1);
        startGrid.add(optButt, 1, 0);
        startGrid.add(leadButt, 1, 1);
        //startGrid.add(newButt, 0,0);
        startGrid.setHgap(30);
        startGrid.setVgap(30);

        root.getChildren().add(startGrid);
        primaryStage.setTitle("TheCoderGame");
        primaryStage.setScene(titleScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launches JavaFX application
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setIntroEnabled(true);
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        System.out.println("testing");
    }
}
