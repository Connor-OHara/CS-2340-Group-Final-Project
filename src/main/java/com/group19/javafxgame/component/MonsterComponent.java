package com.group19.javafxgame.component;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.Main;
import com.group19.javafxgame.rooms.RoomComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.group19.javafxgame.soundHandler.CombatSounds.playShotgunSound;
import static com.group19.javafxgame.soundHandler.DoorSounds.playRoomCleared;


public class MonsterComponent extends CharacterComponent {
    private int number;
    private Point2D startLocation;
    private HealthIntComponent hp = new HealthIntComponent(25);
    public MonsterComponent(int health,
                           int strength,
                           Point2D location) {
        super(health, strength, location);
    }

    public MonsterComponent() {
        this(Constants.getDefaultMonsterHealth(),
                Constants.getDefaultMonsterStrength(),
                Constants.getDefaultMonsterPosition());
    }

    public MonsterComponent(Point2D location, int number) {
        this();
        this.startLocation = location;
        this.number = number;
    }


    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public Point2D getLocation() {
        return entity.getPosition();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public HealthIntComponent getHp() {
        return hp;
    }

    public void subtractHealth(int health) {
        this.health -= health;
        hp.damage(health);
    }


    public Point2D getStartLocation() {
        return startLocation;
    }

    public void checkHP() {
        if (health <= 0) {
            Entity player = Main.getPlayer();
            player.getComponent(RoomComponent.class).getCurrentRoom().removeMonster(getEntity());
            player.getComponent(MoneyComponent.class).addFunds(1);
            player.getComponent(PlayerComponent.class).incrementMonsterKillCount();
            int healthAdd = Constants.getHealthOnKill();
            player.getComponent(PlayerComponent.class).addHealth(healthAdd);
            if (player.getComponent(RoomComponent.class).getCurrentRoom()
                    .getMonsters().isEmpty()) {
                System.out.println("cleared.");
                playRoomCleared();
                player.getComponent(RoomComponent.class).getCurrentRoom().setCleared(true);
            }
        }
    }
    public void attack(int number) {
        if (number == 1) {
            attack(2000, Constants.getEnemyShurikenProjectileSpeed(), 5,
                    "monsterAttackSound1.mp3", Color.CYAN);
        } else if (number == 2) {
            attack(1500, Constants.getEnemyShurikenProjectileSpeed(), 4,
                    "MonsterAttackSound2.mp3", Color.LAWNGREEN);
            attack(1500, Constants.getEnemyShurikenProjectileSpeed() * 3 / 4, 8,
                    "", Color.RED);
        } else if (number == 3) {
            shotGun();
        }
    }
    public void attack() {
        attack(2000, 300, 5);
    }
    public void attack(int interval, int speed, int damage) {
        attack(interval, speed, damage, "defaultProjectileSound.mp3", Color.CHARTREUSE);
    }
    public void attack(int interval, int speed, int damage, String sound, Color color) {
        attack(interval, speed, damage, sound, color, 5);
    }
    public void attack(int interval, int speed, int damage, String sound, Color color, int size) {
        attack(interval, speed, damage, sound, new Rectangle(size, size, color));
    }
    public void attack(int interval, int speed, int damage, String sound, Shape shape) {
        getGameTimer().runAtIntervalWhile(() -> {
            Point2D pos = getEntity().getCenter();
            Point2D pos2 = Main.getPlayer().getPosition();
            Point2D dir = pos2.subtract(pos);
            var projectile = FXGL.spawn("projectile",
                    new SpawnData(pos).put("dir", dir)
                            .put("speed", speed).put("dmg", damage).put("loc", pos)
                            .put("sound", sound).put("shape", shape));
        }, Duration.millis(interval),  getEntity().activeProperty());
    }

    public void shotGun() {
        double cos = Math.cos(Math.PI / 24);
        double sin = Math.sin(Math.PI / 24);
        double cos2 = Math.cos(Math.PI / 12);
        double sin2 = Math.sin(Math.PI / 12);
        int speed = 300;
        int damage = 3;
        String sound = "shotGun.mp3";
        Rectangle shape = new Rectangle(5, 5, Color.WHITE);
        getGameTimer().runAtIntervalWhile(() -> {
            Point2D pos = getEntity().getCenter();
            Point2D pos2 = Main.getPlayer().getPosition();
            Point2D dir = pos2.subtract(pos);
            double x = dir.getX();
            double y = dir.getY();
            Point2D dir2 = new Point2D(cos * x - sin * y,
                    sin * x + cos * y); //rotate 15 degrees left
            Point2D dir3 = new Point2D(cos * x + sin * y,
                    -sin * x + cos * y); //rotate 15 degrees right
            Point2D dir4 = new Point2D(cos2 * x - sin2 * y,
                    sin2 * x + cos2 * y); //rotate 15 degrees left
            Point2D dir5 = new Point2D(cos2 * x + sin2 * y,
                    -sin2 * x + cos2 * y); //rotate 15 degrees right
            shotHelper(speed, damage, sound, shape, pos, dir);
            shotHelper(speed, damage, sound, shape, pos, dir2);
            shotHelper(speed, damage, sound, shape, pos, dir3);
            shotHelper(speed, damage, sound, shape, pos, dir4);
            shotHelper(speed, damage, sound, shape, pos, dir5);
            playShotgunSound();
        }, Duration.millis(4000),  getEntity().activeProperty());
    }

    public void shotHelper(int speed, int damage, String sound, Rectangle shape,
                           Point2D pos, Point2D dir) {
        shape = new Rectangle(shape.getWidth(), shape.getHeight(), shape.getFill());
        var projectile = FXGL.spawn("projectile",
                new SpawnData(pos).put("dir", dir)
                        .put("speed", speed).put("dmg", damage).put("loc", pos)
                        .put("sound", sound).put("shape", shape));
    }
}
