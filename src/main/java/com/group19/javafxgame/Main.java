package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Constants.GAME_NAME);
        settings.setWidth(Constants.SCREEN_WIDTH);
        settings.setHeight(Constants.SCREEN_HEIGHT);
        settings.setVersion(Constants.GAME_VERSION);
        settings.setMainMenuEnabled(true);
    }

    public static void main(String[] args) {
        //launches JavaFX application
        launch(args);
    }
}
