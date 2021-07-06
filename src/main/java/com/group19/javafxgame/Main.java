package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.Factories.CharacterFactory;
import com.group19.javafxgame.Factories.MainSceneFactory;
import com.group19.javafxgame.Factories.RoomFactory;
import com.group19.javafxgame.Rooms.PlayerDoorCollisionHandler;
import com.group19.javafxgame.Rooms.RoomComponent;
import com.group19.javafxgame.Types.LevelType;
import com.group19.javafxgame.Types.WeaponType;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.input.UserAction;
import com.group19.javafxgame.component.MoneyComponent;
import com.group19.javafxgame.component.PlayerInteractionComponent;
import com.group19.javafxgame.ui.menu.config.InitialConfigSubScene;
import com.group19.javafxgame.ui.menu.gameOver.GameOverSubScene;
import com.group19.javafxgame.ui.menu.gameOver.GameWinSubScene;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.group19.javafxgame.Types.CharacterType.PLAYER;

import javafx.scene.text.Text;

public class Main extends GameApplication {

    private static Entity player;

    private EntityFactory entityFactory = new CharacterFactory();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Constants.getGameName());
        settings.setWidth(Constants.getScreenWidth());
        settings.setHeight(Constants.getScreenHeight());
        settings.setVersion(Constants.getGameVersion());
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new MainSceneFactory());
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
        vars.put("endGame", 0);
        vars.put("money", Constants.getDefaultMoney());
    }

    @Override
    protected void initGame() {
        Entity background;

        getGameWorld().addEntityFactory(entityFactory);
        getGameWorld().addEntityFactory(new RoomFactory());
        getPhysicsWorld().setGravity(0, 0);


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
                //TODO: Game over test code is below, remove after end room/enemies are added
                //FXGL.set("gameWin", 1);
            }
        });


        getWorldProperties().<Integer>addListener("endGame", (prev, now) -> {
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
        //TODO: remove once the end room is placed
        // (load end screen upon touching the ladder to leave)
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

    private Entity initDungeonFloor() {
        return spawn("dungeonFloor");
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
        GameWinSubScene gameWinSubcScene = new GameWinSubScene(
               
        );

        getGameScene().addUINodes(gameWinSubcScene.getContentRoot());
    }


    private void initConfigScreen() {
        InitialConfigSubScene configSubScene = new InitialConfigSubScene(
                Constants.getDefaultDifficulty(), Constants.getDefaultWeapon()
        );

        getGameScene().addUINodes(configSubScene.getContentRoot());

    }

//    private void loadRoom(String filename, int xGrid, int yGrid) {
//        RoomComponent newRoom = new RoomComponent(filename, xGrid, yGrid);
//        RoomComponent[][] currMaze = RoomComponent.getMaze();
//        currMaze[xGrid][yGrid] = newRoom;
//        RoomComponent.setMaze(currMaze);
//        currRoom = newRoom;
//        currRoom.setLevelFromRoom();
//
//    }

    private void spawnCharacters() {
        player = spawn("Player");
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
    }

    private void nextLevel() {
        if (geto("weapon") == WeaponType.SWORD) {
            showMessage("You finished the demo!");
            return;
        }
    }



    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponent(PlayerInteractionComponent.class).translateLeft();
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
            new PlayerDoorCollisionHandler(LevelType.DOOR, PLAYER)
        );
    }

    public static Entity getPlayer() {
        return player;
    }

    public static void main(String[] args) {
        //launches JavaFX application
        launch(args);
    }

}