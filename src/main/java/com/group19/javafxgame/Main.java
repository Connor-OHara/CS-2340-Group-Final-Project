package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.group19.javafxgame.Types.WeaponType;
import javafx.scene.paint.Color;

import com.almasb.fxgl.entity.Entity;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.showMessage;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Main extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Constants.GAME_NAME);
        settings.setWidth(Constants.SCREEN_WIDTH);
        settings.setHeight(Constants.SCREEN_HEIGHT);
        settings.setVersion(Constants.GAME_VERSION);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new InitialConfigMenuSceneFactory());

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
        vars.put("difficulty", Constants.DEFAULT_DIFFICULTY);
        vars.put("weapon", Constants.DEFAULT_WEAPON);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new CharacterFactory());

        getGameScene().setBackgroundColor(Color.color(0.5, 0.5, 0.5, 1.0));

        player = spawn("Player");

        int dist = 50; //TODO: move to constants?
        getGameScene().getViewport().setBounds(-dist, -dist, getAppWidth() + dist, getAppHeight() + dist);
        getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);


    }


    private void nextLevel() {
        if (geto("weapon") == WeaponType.SWORD) {
            showMessage("You finished the demo!");
            return;
        }

    }


    public static void main(String[] args) {
        //launches JavaFX application
        launch(args);
    }




}
