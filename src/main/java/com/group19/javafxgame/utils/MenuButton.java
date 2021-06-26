package com.group19.javafxgame.utils;

//Borrowing from FXGL Samples: GeoWars and Almas Baimagambetov

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

/**
 * Class to centralize general button styling
 */
public class MenuButton extends Button {
    /**
     * Constructor for button
     * @param name Text and Name for button as String
     * @param action Runnable action to perform on action
     *
     * We can further customize button elements here.
     */
    public MenuButton(String name, Runnable action) {
        // Text
        Font labelFont = new Font("impact", 20);
        this.setText(name);
        this.setFont(labelFont);

        // Background
        LinearGradient gradient = new LinearGradient(
                0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0.1, Color.web("white", 0.5)),
                new Stop(1, Color.web("black", 0.5))
        );
        BackgroundFill backgroundFill = new BackgroundFill(
                gradient,
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        Background background = new Background(backgroundFill);
        this.setBackground(background);

        // Event Handler
        setOnMouseClicked(e -> {
            if (action != null) {
                action.run();
            }
        });
    }



}
