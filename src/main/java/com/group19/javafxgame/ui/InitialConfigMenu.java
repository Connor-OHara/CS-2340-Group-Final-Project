package com.group19.javafxgame.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.group19.javafxgame.Types.DifficultyLevel;
import com.group19.javafxgame.utils.EnumUtil;
import com.group19.javafxgame.Types.WeaponType;
import javafx.beans.binding.StringBinding;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.EnumSet;

public class InitialConfigMenu extends FXGLMenu {

    String playerName = "      ";
    public InitialConfigMenu(DifficultyLevel defaultDiff, WeaponType defaultWeapon) {
        super(MenuType.MAIN_MENU);

        EnumUtil<DifficultyLevel> difficultyLevelsEnumUtil = new EnumUtil<>();
        ObservableList<DifficultyLevel> diffs = difficultyLevelsEnumUtil
                .enumToObservableList(EnumSet.allOf(DifficultyLevel.class));

        EnumUtil<WeaponType> weaponTypeEnumUtil = new EnumUtil<>();
        ObservableList<WeaponType> weapons = weaponTypeEnumUtil
                .enumToObservableList(EnumSet.allOf(WeaponType.class));


        ChoiceBox<DifficultyLevel> difficultyChoiceBox = FXGL.getUIFactoryService().newChoiceBox(diffs);
        difficultyChoiceBox.setValue(defaultDiff);
        difficultyChoiceBox.setOnAction(e -> FXGL.set("difficulty",difficultyChoiceBox.getValue()));


        ChoiceBox<WeaponType> weaponChoiceBox = FXGL.getUIFactoryService().newChoiceBox(weapons);
        weaponChoiceBox.setValue(defaultWeapon);
        weaponChoiceBox.setOnAction(e -> FXGL.set("weapon",weaponChoiceBox.getValue()));


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
                fireNewGame();
            }
        });//TODO: start initial room

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);

        HBox diffHBox = new HBox(15,FXGL.getUIFactoryService().newText("Difficulty: "), difficultyChoiceBox);
        HBox weaponHBox = new HBox(15,FXGL.getUIFactoryService().newText("Weapon: "), weaponChoiceBox);

        box.getChildren().add(nameBox);
        box.getChildren().add(nameButton);
        box.getChildren().add(diffHBox);
        box.getChildren().add(weaponHBox);
        box.getChildren().add(beginButton);

        getContentRoot().getChildren().addAll(box);

    }

    
    @Override
    protected Button createActionButton( StringBinding stringBinding,
                                         Runnable runnable) {
        return new Button();
    }

    
    @Override
    protected Button createActionButton( String s,  Runnable runnable) {
        return new Button();
    }

    
    @Override
    protected Node createBackground(double v, double v1) {
        return FXGL.texture("background/SimplePaintBackground.png");
    }

    
    @Override
    protected Node createProfileView( String s) {
        return new Rectangle();
    }

    
    @Override
    protected Node createTitleView( String s) {
        return new Rectangle();
    }

    
    @Override
    protected Node createVersionView( String s) {
        return new Rectangle();
    }



}
