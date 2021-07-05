package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.Factories.CharacterFactory;
import com.group19.javafxgame.Factories.MainSceneFactory;
import com.group19.javafxgame.Factories.RoomFactory;
import com.group19.javafxgame.Rooms.DoorComponent;
import com.group19.javafxgame.Rooms.RoomComponent;
import com.group19.javafxgame.Types.LevelType;
import com.group19.javafxgame.Types.WeaponType;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.group19.javafxgame.component.MoneyComponent;
import com.group19.javafxgame.component.PlayerInteractionComponent;
import com.group19.javafxgame.ui.menu.config.InitialConfigSubScene;
import com.group19.javafxgame.utils.RandomRoomUtils;
import com.group19.javafxgame.utils.RoomCoordinate;
import com.group19.javafxgame.utils.RoomDoorUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Map;
import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.group19.javafxgame.Types.CharacterType.PLAYER;

import javafx.scene.text.Text;

public class Main extends GameApplication {

    private Entity player;
    private RoomComponent currRoom;

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
                loadRoom("MiddleFromDefault.tmx", 6, 6);
                player = spawn("Player");
                MoneyComponent moneyComponent = player.getComponent(MoneyComponent.class);
                gameUI(moneyComponent);
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


    private void initConfigScreen() {
        InitialConfigSubScene configSubScene = new InitialConfigSubScene(
                Constants.getDefaultDifficulty(), Constants.getDefaultWeapon()
        );

        getGameScene().addUINodes(configSubScene.getContentRoot());

    }

    private void goToRoom() {

    }

    private void loadRoom(String filename, int xGrid, int yGrid) {

        RoomComponent newRoom = new RoomComponent(filename, xGrid, yGrid);
        RoomComponent[][] currMaze = RoomComponent.getMaze();
        currMaze[xGrid][yGrid] = newRoom;
        RoomComponent.setMaze(currMaze);
        currRoom = newRoom;
        currRoom.setLevelFromRoom();

    }

    private void loadRoom(RoomComponent room) {
        currRoom = room;
        currRoom.setLevelFromRoom();
    }

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

        var leftButton = FXGL.getUIFactoryService().newButton("Left");
        leftButton.setMinSize(30, 15);

        leftButton.setOnAction(event -> {
            RoomCoordinate nextCoordinate = new
                    RoomDoorUtils()
                    .doorSideToRoomCoordinate("left", currRoom);

            String[] rooms = {"Middle1.tmx", "MiddleFromDefault.tmx", "Middle2.tmx", "Tunnel1.tmx"};

            Random rand = new Random();
            String nextRoomName = rooms[rand.nextInt(rooms.length)];
            int x = nextCoordinate.getxGrid();
            int y = nextCoordinate.getyGrid();

            loadRoom(nextRoomName, x, y);
            player.setZ(Integer.MAX_VALUE);
        });

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
                new CollisionHandler(LevelType.DOOR, PLAYER) {
                    @Override
                    protected void onCollisionBegin(Entity a, Entity b) {
                        super.onCollisionBegin(a, b);


                            DoorComponent currDoor = a.getComponent(DoorComponent.class);
                            String doorType = currDoor.getSide();

                            RoomCoordinate nextCoordinate = new
                                    RoomDoorUtils()
                                    .doorSideToRoomCoordinate(doorType, currRoom);

                            System.out.println("Curr x" + currRoom.getxGrid() + " y"  + currRoom.getyGrid());

                            int x = nextCoordinate.getxGrid();
                            int y = nextCoordinate.getyGrid();
                            String roomFile;
                            System.out.println("Next x" + x + " y"  + y);

                            if (RoomComponent.getMaze()[x][y] != null) {
                                player.setPosition(Constants.getDefaultPosition());
                                loadRoom(RoomComponent.getMaze()[x][y]);
                                String filename = RoomComponent.getMaze()[x][y].getFilename();
                                System.out.println("Went back to " + filename);
                                player.setZ(Integer.MAX_VALUE);

                            } else if (x > 11 || x < 1 || y > 11 || y < 1 ) {
                                System.out.println("Made to the final room");
                                roomFile = RandomRoomUtils.getInstance().getRandomRoom("final" + doorType);
                                System.out.println("Final file name: " + roomFile);
                                player.setPosition(Constants.getDefaultPosition());
                                loadRoom(roomFile, x, y);
                                player.setZ(Integer.MAX_VALUE);

                            } else {
                                System.out.println(doorType);
                                roomFile = RandomRoomUtils.getInstance().getRandomRoom(doorType);
                                System.out.println("Currently at : " + roomFile);
                                player.setPosition(Constants.getDefaultPosition());
                                loadRoom(roomFile, x, y);
                                player.setZ(Integer.MAX_VALUE);

                            }

                        System.out.println("Collided with door");
                    }
                }
        );
    }
}