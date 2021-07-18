package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.input.UserAction;
import com.group19.javafxgame.factories.CharacterFactory;
import com.group19.javafxgame.factories.MainSceneFactory;
import com.group19.javafxgame.factories.RoomFactory;
import com.group19.javafxgame.rooms.PlayerDoorCollisionHandler;
import com.group19.javafxgame.rooms.PlayerEndGamePlatformCollisionHandler;
import com.group19.javafxgame.rooms.RoomComponent;
import com.group19.javafxgame.component.MoneyComponent;
import com.group19.javafxgame.component.PlayerInteractionComponent;
import com.group19.javafxgame.types.CharacterType;
import com.group19.javafxgame.types.LevelType;
import com.group19.javafxgame.ui.menu.config.InitialConfigSubScene;
import com.group19.javafxgame.ui.menu.gameOver.GameOverSubScene;
import com.group19.javafxgame.ui.menu.gameOver.GameWinSubScene;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    private static Entity player;

    private final EntityFactory entityFactory = new CharacterFactory();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Constants.getGameName());
        settings.setWidth(Constants.getScreenWidth());
        settings.setHeight(Constants.getScreenHeight());
        settings.setVersion(Constants.getGameVersion());
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new MainSceneFactory());

        //uncomment both below for fullscreen
        //settings.setFullScreenAllowed(true);
        //settings.setFullScreenFromStart(true);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
        vars.put("difficulty", Constants.getDefaultDifficulty());
        vars.put("weapon", Constants.getDefaultWeapon());
        vars.put("name", "");
        vars.put("configFinished", 0);
        vars.put("gameOver", 0);
        vars.put("gameWin", 0);
        vars.put("closeGame", 0);
        vars.put("money", Constants.getDefaultMoney());
    }

    @Override
    protected void onPreInit() {
        //volume can be changed in esc menu
        getSettings().setGlobalMusicVolume(0.15);
        getSettings().setGlobalSoundVolume(0.15);
        loopBGM("background_cave_wind.mp3");
    }

    @Override
    protected void initGame() {
        Entity background;

        getGameWorld().addEntityFactory(entityFactory);
        getGameWorld().addEntityFactory(new RoomFactory());
        getPhysicsWorld().setGravity(0, 0);

        getAudioPlayer().playMusic(FXGL.getAssetLoader().loadMusic("background_cave_wind.mp3"));

        background = initBackground();
        initConfigScreen();

        getWorldProperties().<Integer>addListener("configFinished", (prev, now) -> {
            if (now == 1) {
                removeBackgroundAndConfigScreen(background);
                player = spawn("Player");
                player.getComponent(RoomComponent.class).getCurrentRoom().applyLevel();
                player.setZ(Integer.MAX_VALUE);
                MoneyComponent moneyComponent = player.getComponent(MoneyComponent.class);
                gameUI(moneyComponent);
            }
        });


        getWorldProperties().<Integer>addListener("closeGame", (prev, now) -> {
            if (now == 1) {
                Platform.exit();
            }
        });

        getWorldProperties().<Integer>addListener("gameOver", (prev, now) -> {
            if (now == 1) {
                initBackground();
                initGameOverScreen();
                removeGameUI();
            }
        });

        getWorldProperties().<Integer>addListener("gameWin", (prev, now) -> {
            if (now == 1) {
                initBackground();
                initGameWinScreen();
                removeGameUI();
            }
        });

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

    private void initGameOverScreen() {
        GameOverSubScene gameOverSubScene = new GameOverSubScene(

        );
        getGameScene().addUINodes(gameOverSubScene.getContentRoot());
    }


    private void initGameWinScreen() {
        GameWinSubScene gameWinSubScene = new GameWinSubScene(
               
        );

        getGameScene().addUINodes(gameWinSubScene.getContentRoot());
    }


    private void initConfigScreen() {
        InitialConfigSubScene configSubScene = new InitialConfigSubScene(
                Constants.getDefaultDifficulty(), Constants.getDefaultWeapon()
        );

        getGameScene().addUINodes(configSubScene.getContentRoot());

    }

    protected void gameUI(MoneyComponent moneyComponent) {
        Text goldText = new Text();
        Text goldLabel = new Text("Gold:");

        goldText.setTranslateX(getAppWidth() / 14.0);
        goldLabel.setTranslateX(getAppWidth() / 40.0);

        goldText.setTranslateY(45);
        goldText.setFill(Color.GOLD);
        goldText.setFont(Font.font("Calibra", FontWeight.BOLD, 22));
        goldLabel.setTranslateY(45);
        goldLabel.setFill(Color.GOLD);
        goldLabel.setFont(Font.font("Calibra", FontWeight.BOLD, 22));

        String money = String.valueOf(moneyComponent.showFunds());
        goldText.textProperty().bind(new SimpleStringProperty(money));
        goldText.textProperty().bind(new SimpleStringProperty(money));

        getGameScene().addUINode(goldText);
        getGameScene().addUINode(goldLabel);

        System.out.println(getPhysicsWorld().toMeters(Constants.getScreenWidth()));
    }

    protected void removeGameUI() {
        getGameScene().removeUINodes();
        despawnWithScale(player);
    }



    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponent(PlayerInteractionComponent.class).translateLeft();
                updateFaceDirection(player, "LEFT");
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                player.getComponent(PlayerInteractionComponent.class).stopLeft();
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponent(PlayerInteractionComponent.class).translateRight();
                updateFaceDirection(player, "RIGHT");
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                player.getComponent(PlayerInteractionComponent.class).stopRight();
            }
        }, KeyCode.RIGHT);

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponent(PlayerInteractionComponent.class).translateUp();
                updateFaceDirection(player, "UP");
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                player.getComponent(PlayerInteractionComponent.class).stopUp();
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponent(PlayerInteractionComponent.class).translateDown();
                updateFaceDirection(player, "DOWN");
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                player.getComponent(PlayerInteractionComponent.class).stopDown();
            }
        }, KeyCode.DOWN);
    }

    @Override
    protected void initPhysics() {
        super.initPhysics();

        getPhysicsWorld().addCollisionHandler(
            new PlayerEndGamePlatformCollisionHandler(
                CharacterType.PLAYER,
                LevelType.END_GAME_PLATFORM
            )
        );
        getPhysicsWorld().addCollisionHandler(
            new PlayerDoorCollisionHandler(CharacterType.PLAYER, LevelType.DOOR)
        );
    }

    //updates current direction and saves last direction,
    // calls updateSpriteDir to reflect the sprite
    public void updateFaceDirection(Entity entity, String newDir) {
        String current = entity.getComponent(PlayerInteractionComponent.class).getCurrDir();
        entity.getComponent(PlayerInteractionComponent.class).setLastDir(current);
        entity.getComponent(PlayerInteractionComponent.class).setCurrDir(newDir);
        current = entity.getComponent(PlayerInteractionComponent.class).getCurrDir();
        String last = entity.getComponent(PlayerInteractionComponent.class).getLastDir();
        updateSpriteDir(entity, current, last);
    }


    //changes sprite direction
    public void updateSpriteDir(Entity character, String currDir, String lastdir) {
        if (currDir == lastdir) {
            return;
        }
        if (currDir == "RIGHT") {
            character.setScaleX(-1);
        } else if (currDir == "LEFT") {
            character.setScaleX(1);
        }
        return;
    }

    public static void main(String[] args) {
        //launches JavaFX application
        launch(args);
    }

}