package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.Types.WeaponType;
import com.group19.javafxgame.ui.menu.config.InitialConfigSubScene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;

//import java.util.Map;
//import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Constants.GAME_NAME);
        settings.setWidth(Constants.SCREEN_WIDTH);
        settings.setHeight(Constants.SCREEN_HEIGHT);
        settings.setVersion(Constants.GAME_VERSION);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new MainSceneFactory());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
        vars.put("difficulty", Constants.DEFAULT_DIFFICULTY);
        vars.put("weapon", Constants.DEFAULT_WEAPON);
        vars.put("name", "");
        vars.put("configFinished", 0);
        //vars.put("money", Constants.DEFAULT_MONEY);
    }

    @Override
    protected void initGame() {
        Entity background;
        Entity dungeonFloor;

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
                GameUI();
            }
        });


//        int dist = 50; //TODO: move to constants?
        /**These lines are commented out because it messed up the walls*///TODO: @Matthew remove?
        //getGameScene().getViewport().setBounds(-dist, -dist, getAppWidth() + dist, getAppHeight() + dist);
        //getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
    }

    private Entity initBackground() {
        getGameScene().setBackgroundColor(Color.color(0.5, 0.5, 0.5, 1.0));
        return spawn("background");
    }

    private Entity initDungeonFloor() {
        return spawn("dungeonFloor");
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

    protected void GameUI(){
        Text goldText = new Text();
        goldText.setTranslateX(getAppWidth() / 38.0);

        goldText.setTranslateY(45);
        goldText.setFill(Color.GOLD);
        goldText.setFont(Font.font("Calibra", FontWeight.BOLD,22));
        //makes goldText watch the gold game value in vars
        goldText.textProperty().bind(getWorldProperties().intProperty("money").asString());

        getGameScene().addUINode(goldText);
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