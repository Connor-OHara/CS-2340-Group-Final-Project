package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.Types.WeaponType;
import com.group19.javafxgame.ui.menu.config.InitialConfigSubScene;
import com.group19.javafxgame.ui.menu.main.MainMenuSceneFactory;
import javafx.scene.paint.Color;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Constants.GAME_NAME);
        settings.setWidth(Constants.SCREEN_WIDTH);
        settings.setHeight(Constants.SCREEN_HEIGHT);
        settings.setVersion(Constants.GAME_VERSION);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new MainMenuSceneFactory());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
        vars.put("difficulty", Constants.DEFAULT_DIFFICULTY);
        vars.put("weapon", Constants.DEFAULT_WEAPON);
        vars.put("name", "");
        vars.put("configFinished", 0);
    }

    @Override
    protected void initGame() {
        Entity background;

        getGameWorld().addEntityFactory(new CharacterFactory());



//        FXGL.run(()-> getSceneService().pushSubScene(new InitialConfigSubScene(Constants.DEFAULT_DIFFICULTY,
//                Constants.DEFAULT_WEAPON)),Duration.hours(1));

        background = initBackground();
        initConfigScreen();

        getWorldProperties().<Integer>addListener("configFinished", (prev, now) -> {
            if (now == 1) {
                removeBackgroundAndConfigScreen(background);
                loadRoom();
                spawnCharacters();
            }
        });



        //FXGL.setLevelFromMap("default2.tmx");




//        getGameScene().setBackgroundColor(Color.color(0.5, 0.5, 0.5, 1.0));
//
//        //System.out.println(geto("weapon").toString());
//        player = spawn("Player");
//        int dist = 50; //TODO: move to constants?
        /**These lines are commented out because it messed up the walls*/
        //getGameScene().getViewport().setBounds(-dist, -dist, getAppWidth() + dist, getAppHeight() + dist);
        //getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
    }

    private Entity initBackground() {
        getGameScene().setBackgroundColor(Color.color(0.5, 0.5, 0.5, 1.0));
        return spawn("background");
    }
    private void removeBackgroundAndConfigScreen(Entity background) {
        getGameScene().setBackgroundColor(Color.color(0.5, 0.5, 0.5, 1.0));
        getGameScene().clearUINodes();
        background.removeFromWorld();
    }


    private void initConfigScreen() {
        InitialConfigSubScene configSubScene = new InitialConfigSubScene(
                Constants.DEFAULT_DIFFICULTY, Constants.DEFAULT_WEAPON
        );

        getGameScene().addUINodes(configSubScene.getContentRoot());

    }

    private void loadRoom() {
        FXGL.setLevelFromMap("default2.tmx");
    }

    private void spawnCharacters() {
        player = spawn("Player");
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
