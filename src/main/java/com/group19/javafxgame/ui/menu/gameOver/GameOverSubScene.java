package com.group19.javafxgame.ui.menu.gameOver;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.FXGLButton;
import com.almasb.fxgl.ui.FontType;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.player.PlayerScoreCalculator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;



public class GameOverSubScene extends SubScene {

    public GameOverSubScene(Entity player) {
        //StackPane for text
        StackPane deathContainer = new StackPane();
        deathContainer.setAlignment(Pos.CENTER);
        deathContainer.setPrefHeight(Constants.getScreenHeight());
        deathContainer.setPrefWidth(Constants.getScreenWidth());


        //Game Over Text
        Text youDied = FXGL.getUIFactoryService()
                .newText("You Died",
                        Color.RED,
                        FontType.GAME,
                        70);

        //Name Over Text - uses player name and sits under You Died
        Text nameOver = FXGL.getUIFactoryService()
                .newText((FXGL.gets("name") + ", your story is over."),
                        Color.RED,
                        FontType.GAME,
                        70);

        PlayerScoreCalculator scoreCalculator = new PlayerScoreCalculator(
                player,
                FXGL.geto("difficulty")
        );
        Text monstersKilled = FXGL.getUIFactoryService()
                .newText(scoreCalculator.getMonstersKilled() + " Monsters Killed",
                        Color.WHITE,
                        FontType.UI,
                        24);

        Text goldText = FXGL.getUIFactoryService()
                .newText(scoreCalculator.getGold() + " Gold",
                        Color.WHITE,
                        FontType.UI,
                        24);

        Text roomsVisitedText = FXGL.getUIFactoryService()
                .newText(scoreCalculator.getRoomsVisited() + " Rooms Visited",
                        Color.WHITE,
                        FontType.UI,
                        24);

        Text totalScoreText = FXGL.getUIFactoryService()
                .newText("Total Score: " + scoreCalculator.getScore(),
                        Color.YELLOW,
                        FontType.UI,
                        27);

        youDied.setTextAlignment(TextAlignment.CENTER);
        nameOver.setTextAlignment(TextAlignment.CENTER);
        monstersKilled.setTextAlignment(TextAlignment.CENTER);
        goldText.setTextAlignment(TextAlignment.CENTER);
        roomsVisitedText.setTextAlignment(TextAlignment.CENTER);
        totalScoreText.setTextAlignment(TextAlignment.CENTER);

        //quit button
        Button quitButton = new FXGLButton("Quit");
        quitButton.setOnAction(e -> {
            FXGL.set("closeGame", 1);
        });

        //restart button
        Button restartButton = new FXGLButton("Restart");
        restartButton.setOnAction(e -> {
            FXGL.set("restartGame", 1);
        });

        quitButton.setMaxWidth(300);
        quitButton.setPrefHeight(40);
        restartButton.setMaxWidth(300);
        restartButton.setPrefHeight(40);


        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(Constants.getScreenWidth());
        box.setTranslateY(200);

        box.getChildren().add(youDied);
        box.getChildren().add(nameOver);
        box.getChildren().add(monstersKilled);
        box.getChildren().add(goldText);
        box.getChildren().add(roomsVisitedText);
        box.getChildren().add(totalScoreText);
        box.getChildren().add(restartButton);
        box.getChildren().add(quitButton);

        getContentRoot().getChildren().addAll(box);

    }


}
