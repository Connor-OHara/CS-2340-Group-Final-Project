package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.group19.javafxgame.component.ExplosionComponent;
import com.group19.javafxgame.component.MoneyComponent;
import com.group19.javafxgame.component.MonsterComponent;
import com.group19.javafxgame.component.MonsterInteractionComponent;
import com.group19.javafxgame.component.PlayerComponent;
import com.group19.javafxgame.component.PlayerInteractionComponent;
import com.group19.javafxgame.component.ProjectileComp;
import com.group19.javafxgame.component.SwordComponent;
import com.group19.javafxgame.factories.AttackFactory;
import com.group19.javafxgame.factories.CharacterFactory;
import com.group19.javafxgame.factories.MainSceneFactory;
import com.group19.javafxgame.factories.RoomFactory;
import com.group19.javafxgame.rooms.PlayerDoorCollisionHandler;
import com.group19.javafxgame.rooms.PlayerEndGamePlatformCollisionHandler;
import com.group19.javafxgame.rooms.RoomComponent;
import com.group19.javafxgame.types.AttackType;
import com.group19.javafxgame.types.CharacterType;
import com.group19.javafxgame.types.LevelType;
import com.group19.javafxgame.types.WeaponType;
import com.group19.javafxgame.ui.menu.config.InitialConfigSubScene;
import com.group19.javafxgame.ui.menu.gameOver.GameOverSubScene;
import com.group19.javafxgame.ui.menu.gameOver.GameWinSubScene;
import com.group19.javafxgame.ui.menu.main.MainMenu;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.group19.javafxgame.soundHandler.CombatSounds.*;

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
        vars.put("restartGame", 0);
        vars.put("gameWin", 0);
        vars.put("closeGame", 0);
        vars.put("money", Constants.getDefaultMoney());
        vars.put("playerHealthUI", 100);
        //Change to False to Lock Rooms As if playing full game
        vars.put("DevMode", false);
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
        getGameWorld().addEntityFactory(new AttackFactory());
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

        getWorldProperties().<Integer>addListener("restartGame", (prev, now) -> {
            System.out.println("Pressed button");
            if (now == 1) {
                System.out.println("TESTING");
                ((MainMenu) getSettings().getSceneFactory().newMainMenu()).resetGame();
            }
        });

        getWorldProperties().<Integer>addListener("gameOver", (prev, now) -> {
            if (now == 1) {
                removeGameUI();
                initBackground();
                initGameOverScreen();
            }
        });

        getWorldProperties().<Integer>addListener("gameWin", (prev, now) -> {
            if (now == 1) {
                removeGameUI();
                initBackground();
                initGameWinScreen();
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
        GameOverSubScene gameOverSubScene = new GameOverSubScene(player);
        getGameScene().addUINodes(gameOverSubScene.getContentRoot());
    }


    private void initGameWinScreen() {
        GameWinSubScene gameWinSubScene = new GameWinSubScene(player);
        getGameScene().addUINodes(gameWinSubScene.getContentRoot());
    }


    private void initConfigScreen() {
        InitialConfigSubScene configSubScene = new InitialConfigSubScene(
                Constants.getDefaultDifficulty(), Constants.getDefaultWeapon()
        );
        getGameScene().addUINodes(configSubScene.getContentRoot());

    }

    protected void removeGameUI() {
        getGameScene().clearUINodes();
        List<Entity> entitiesCopy = getGameWorld().getEntitiesCopy();
        for (Entity entity: entitiesCopy) {
            entity.removeComponent(IrremovableComponent.class);
            entity.removeFromWorld();
        }
    }

    protected void gameUI(MoneyComponent moneyComponent) {
        Text healthText = addVarText("playerHealthUI", getAppWidth() / 15.0, getAppWidth() / 35.0);
        Text healthLabel = new Text("Health: ");


        Text goldText = addVarText("money", getAppWidth() / 15.0, getAppWidth() / 20.0);
        Text goldLabel = new Text("Gold:");

        //goldText.setTranslateX(getAppWidth() / 14.0);
        goldLabel.setTranslateX(getAppWidth() / 35.0);

        //healthText.setTranslateX(getAppWidth() / 111.0);
        healthLabel.setTranslateX(getAppWidth() / 55.0);

        //goldText.setTranslateY(75);
        goldText.setFill(Color.GOLD);
        goldLabel.setTranslateY(getAppWidth() / 20.0);
        goldLabel.setFill(Color.GOLD);
        goldLabel.setFont(Font.font("Segoe UI Semibold", FontWeight.BOLD, 17));

        //healthText.setTranslateY(45);
        healthText.setFill(Color.RED);
        healthLabel.setTranslateY(getAppWidth() / 35.0);
        healthLabel.setFill(Color.RED);
        healthLabel.setFont(Font.font("Segoe UI Semibold", FontWeight.BOLD, 17));

        //bind health and money to UI elements
        String money = String.valueOf(moneyComponent.getFunds());
        String health = String.valueOf(player.getComponent(PlayerComponent.class).getHealth());

        getGameScene().addUINode(goldLabel);
        getGameScene().addUINode(healthLabel);
    }



    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::translateLeft);
                updateFaceDirection(player, "LEFT");
            }
            @Override
            protected void onAction() {
                super.onAction();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::translateLeft);
                updateFaceDirection(player, "LEFT");
            }
            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::stopLeft);
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::translateRight);
                updateFaceDirection(player, "RIGHT");
            }
            @Override
            protected void onAction() {
                super.onAction();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::translateRight);
                updateFaceDirection(player, "RIGHT");
            }
            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::stopRight);
            }
        }, KeyCode.RIGHT);

        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::translateUp);
                updateFaceDirection(player, "UP");
            }
            @Override
            protected void onAction() {
                super.onAction();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::translateUp);
                updateFaceDirection(player, "UP");
            }
            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::stopUp);
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::translateDown);
                updateFaceDirection(player, "DOWN");
            }
            @Override
            protected void onAction() {
                super.onAction();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::translateDown);
                updateFaceDirection(player, "DOWN");
            }
            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                player.getComponentOptional(PlayerInteractionComponent.class)
                        .ifPresent(PlayerInteractionComponent::stopDown);
            }
        }, KeyCode.DOWN);

        getInput().addAction(new UserAction("Click") {
            @Override
            protected void onActionEnd() {
                if (geti("configFinished") == 0 || geti("gameWin") == 1) {
                    return;
                }
                player.getComponentOptional(PlayerComponent.class).ifPresent(playerComp -> {
                    if (playerComp.getAttacks() >= 1) {
                        playerComp.attack();
                    } else {
                        return;
                    }
                    if (geto("weapon") == WeaponType.SHIELD) {
                        var bomb = FXGL.spawn("Bomb");
                        bomb.getComponent(PhysicsComponent.class)
                                .overwritePosition(player.getPosition());
                    } else if (geto("weapon") == WeaponType.SHURIKEN) {
                        Point2D dir = getInput().getMousePositionWorld()
                                .subtract(player.getCenter()).normalize();
                        double cos = Math.cos(Math.PI / 12);
                        double sin = Math.sin(Math.PI / 12);
                        double x = dir.getX();
                        double y = dir.getY();
                        Point2D dir2 = new Point2D(cos * x - sin * y,
                                sin * x + cos * y); //rotate 15 degrees left
                        Point2D dir3 = new Point2D(cos * x + sin * y,
                                -sin * x + cos * y); //rotate 15 degrees right

                        var shuriken = FXGL.spawn("Shuriken",
                                new SpawnData(player.getX(), player.getY()).put("dir", dir)
                                        .put("loc", player.getCenter()));
                        var shuriken2 = FXGL.spawn("Shuriken",
                                new SpawnData(player.getX(), player.getY()).put("dir", dir2)
                                        .put("loc", player.getCenter()));
                        var shuriken3 = FXGL.spawn("Shuriken",
                                new SpawnData(player.getX(), player.getY()).put("dir", dir3)
                                        .put("loc", player.getCenter()));
                        //sounds stacked for more depth
                        playShurikenSound();
                        playShurikenSound();
                        playShurikenSound();
                    } else if (geto("weapon") == WeaponType.SWORD) {
                        Point2D dir = getInput().getMousePositionWorld()
                                .subtract(player.getCenter()).normalize();
                        var sword = FXGL.spawn("Sword",
                                new SpawnData(player.getCenter()).put("dir", dir));
                        playSwordSound();
                    }
                });
            }
        }, MouseButton.PRIMARY);
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

        onCollisionBegin(CharacterType.PLAYER, AttackType.EXPLOSION, (player, explosion) -> {
            double distance = player.getCenter().distance(explosion.getCenter());
            double explosionXRadius = explosion.getWidth() / 2;
            double explosionYRadius = explosion.getHeight() / 2;
            double explosionCorrectedRadius = Math.sqrt(explosionXRadius * explosionXRadius
                    + explosionYRadius * explosionYRadius
            );
            double healthDrop = 35 * (1 - distance / explosionCorrectedRadius);
            player.getComponent(PlayerComponent.class).subtractHealth((int) healthDrop);
            playPlayerPainSound();
        });

        onCollisionBegin(AttackType.SHURIKEN, LevelType.WALL, (shuriken, wall) -> {
            shuriken.removeFromWorld();
        });
        onCollisionBegin(AttackType.SHURIKEN2, LevelType.WALL, (shuriken, wall) -> {
            shuriken.removeFromWorld();
        });
        onCollisionBegin(AttackType.SHURIKEN, CharacterType.MONSTER, (shuriken, monster) -> {
            shuriken.removeFromWorld();
            monster.getComponent(MonsterComponent.class).subtractHealth(5);
            monster.getComponent(MonsterComponent.class).checkHP();
        });
        onCollisionBegin(AttackType.SHURIKEN2, CharacterType.PLAYER, (shuriken, players) -> {
            shuriken.removeFromWorld();
            players.getComponent(PlayerComponent.class).subtractHealth(5);
            playPlayerPainSound();
        });
        onCollisionBegin(AttackType.EXPLOSION, CharacterType.MONSTER, (explosion, monster) -> {
            monster.getComponent(MonsterComponent.class)
                    .subtractHealth(explosion.getComponent(ExplosionComponent.class).getDamage());
            monster.getComponent(MonsterComponent.class).checkHP();
        });
        onCollisionBegin(AttackType.SWORD, CharacterType.MONSTER, (sword, monster) -> {
            monster.getComponent(MonsterComponent.class)
                    .subtractHealth(sword.getComponent(SwordComponent.class).getDamage());
            monster.getComponent(MonsterComponent.class).checkHP();
        });
        onCollisionBegin(AttackType.PROJECTILE, CharacterType.PLAYER, (projectile, players) -> {
            players.getComponent(PlayerComponent.class).subtractHealth(
                    projectile.getComponent(ProjectileComp.class).getDamage()
            );
            playPlayerPainSound();
            projectile.removeFromWorld();
        });
        onCollisionBegin(AttackType.PROJECTILE, LevelType.WALL, (projectile, wall) -> {
            projectile.removeFromWorld();
        });
        onCollision(CharacterType.PLAYER, CharacterType.MONSTER, (player, monster) -> {
            monster.getComponent(MonsterInteractionComponent.class)
                    .setPosition(monster.getComponent(MonsterComponent.class)
                            .getStartLocation());
            PhysicsComponent monsterPhysics = monster.getComponent(PhysicsComponent.class);
            monsterPhysics.setVelocityX(0.0);
            monsterPhysics.setVelocityY(0.0);
        });
    }

    //updates current direction and saves last direction,
    // calls updateSpriteDir to reflect the sprite
    public void updateFaceDirection(Entity entity, String newDir) {
        entity.getComponentOptional(PlayerInteractionComponent.class).ifPresent(comp -> {
            String current = comp.getCurrDir();
            comp.setLastDir(current);
            comp.setCurrDir(newDir);
            current = comp.getCurrDir();
            String last = comp.getLastDir();
            updateSpriteDir(entity, current, last);
        });
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

    public static Entity getPlayer() {
        return player;
    }
}