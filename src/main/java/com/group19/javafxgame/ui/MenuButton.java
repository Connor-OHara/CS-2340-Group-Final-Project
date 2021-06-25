package com.group19.javafxgame.ui;

//Borrowing from FXGL Samples: GeoWars and Almas Baimagambetov

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Parent;

/**
 * Class to Centralize genera button styling
 *
 *
 *
 */
public class MenuButton extends Parent {
    /**
     * Constructor for button
     * @param name Text and Name for button as String
     * @param action Runnable action to perform on action
     *
     * We can further customize button elements here.
     */
    MenuButton(String name, Runnable action) {
        var text = FXGL.getUIFactoryService()
                .newText(name);

        setOnMouseClicked(e -> action.run());

        getChildren().add(text);
    }



}
