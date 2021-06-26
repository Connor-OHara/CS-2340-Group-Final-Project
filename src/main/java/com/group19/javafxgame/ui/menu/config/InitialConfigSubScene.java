package com.group19.javafxgame.ui.menu.config;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.FontType;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.Types.DifficultyLevel;
import com.group19.javafxgame.Types.WeaponType;
import com.group19.javafxgame.utils.MenuChoiceBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

import static com.almasb.fxgl.dsl.FXGL.animationBuilder;

public class InitialConfigSubScene extends SubScene {


    String playerName = FXGL.gets("name");

    public InitialConfigSubScene(DifficultyLevel defaultDiff,
                                 WeaponType defaultWeapon) {


        // Title
        Text title = FXGL.getUIFactoryService()
                .newText("Configure Your Character",
                        Color.WHITE,
                        FontType.GAME,
                        35);
        title.setTextAlignment(TextAlignment.CENTER);

        //Choice Boxes
        ChoiceBox<DifficultyLevel> difficultyChoiceBox =
                new MenuChoiceBox<DifficultyLevel>(EnumSet.allOf(
                        DifficultyLevel.class),
                        defaultDiff)
                        .getChoiceBox();

        ChoiceBox<WeaponType> weaponChoiceBox =
                new MenuChoiceBox<WeaponType>(EnumSet.allOf(
                        WeaponType.class),
                        defaultWeapon)
                        .getChoiceBox();

        var nameBox = FXGL.getUIFactoryService().newText("Your Name is: ");


        var nameButton = FXGL.getUIFactoryService().newButton("Choose your name");
        nameButton.setMinSize(500,20);

        nameButton.setOnAction(event -> FXGL.getDialogService().showInputBox("Enter name here:", name -> {
            if (name == null || name.trim().isEmpty()) {
                FXGL.getDialogService().showErrorBox(new IllegalArgumentException("String can't be null or empty"));
            } else {
                playerName = name;
                nameBox.setText("Your Name is: " + playerName);
            }
        }));

        var beginButton = FXGL.getUIFactoryService().newButton("Begin your quest!");
        beginButton.setMinSize(500,20);
        beginButton.setOnAction(event -> {
            if (playerName == null || playerName.trim().isEmpty()) {
                FXGL.getDialogService().showErrorBox(new IllegalArgumentException("Choose name before starting!"));
            } else {
                System.out.println("Pressed!");
                FXGL.set("name", playerName);
                FXGL.set("weapon", weaponChoiceBox.getValue());
                FXGL.set("difficulty", difficultyChoiceBox.getValue());
                FXGL.set("configFinished", 1);
            }
        });

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(Constants.SCREEN_WIDTH);
        box.setTranslateY(200);

        HBox diffHBox = new HBox(15,FXGL.getUIFactoryService().newText("Difficulty: "), difficultyChoiceBox);
        diffHBox.setAlignment(Pos.CENTER);
        diffHBox.setPrefWidth(Constants.SCREEN_WIDTH);


        HBox weaponHBox = new HBox(15,FXGL.getUIFactoryService().newText("Weapon: "), weaponChoiceBox);
        weaponHBox.setAlignment(Pos.CENTER);
        weaponHBox.setPrefWidth(Constants.SCREEN_WIDTH);


        box.getChildren().add(title);
        box.getChildren().add(nameBox);
        box.getChildren().add(nameButton);
        box.getChildren().add(diffHBox);
        box.getChildren().add(weaponHBox);
        box.getChildren().add(beginButton);

        getContentRoot().getChildren().addAll(box);

    }

}
