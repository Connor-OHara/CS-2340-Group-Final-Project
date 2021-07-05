package com.group19.javafxgame.ui.menu.gameOver;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.FXGLButton;
import com.almasb.fxgl.ui.FontType;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.types.WeaponType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;



public class GameOverSubScene extends SubScene {

    public GameOverSubScene(WeaponType defaultWeapon) {

        //StackPane for text
        StackPane deathContainer = new StackPane();
        deathContainer.setAlignment(Pos.CENTER);
        deathContainer.setPrefHeight(Constants.getScreenHeight());
        deathContainer.setPrefWidth(Constants.getScreenWidth());


        //Game Over Text
        Text youDied = FXGL.getUIFactoryService()
                .newText( "You Died",
                        Color.RED,
                        FontType.GAME,
                        70);

        //Name Over Text - uses player name and sits under You Died
        Text nameOver = FXGL.getUIFactoryService()
                .newText((FXGL.gets("name") + ", your story is over"),
                        Color.RED,
                        FontType.GAME,
                        70);

        youDied.setTextAlignment(TextAlignment.CENTER);
        nameOver.setTextAlignment(TextAlignment.CENTER);
        Text[] deathText = {youDied, nameOver};
        deathContainer.getChildren().addAll(deathText);

        //TODO: Find a way to implement score into game. Score would be shown here.
        //We could also keep track of total coins picked up and not increment on that and count that as score?


        //quit button
        Button quitButton = new FXGLButton("Quit");
        quitButton.setOnAction(e -> {
            FXGL.set("endGame", 1);
        });

        quitButton.setMaxWidth(Double.POSITIVE_INFINITY);
        quitButton.setPrefHeight(40);

        //Button Container
        double buttonBoxWidth = 300;
        double tranlsateX = (Constants.getScreenWidth() - buttonBoxWidth) / 2;
        VBox buttonBox = new VBox(15, quitButton);
        buttonBox.setPrefWidth(buttonBoxWidth);
        buttonBox.setFillWidth(true);
        buttonBox.setTranslateX(tranlsateX);
        buttonBox.setTranslateY(450);

        Node[] gameOverChildren = {deathContainer, buttonBox};
        getContentRoot().getChildren().addAll(gameOverChildren);




    }


}
