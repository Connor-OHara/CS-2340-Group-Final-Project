package com.group19.javafxgame.component;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.Main;
import com.group19.javafxgame.rooms.RoomComponent;
import javafx.geometry.Point2D;

import static com.group19.javafxgame.soundHandler.DoorSounds.playRoomCleared;


public class MonsterComponent extends CharacterComponent {

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

    public MonsterComponent(Point2D location) {
        this();
        this.startLocation = location;
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
}
