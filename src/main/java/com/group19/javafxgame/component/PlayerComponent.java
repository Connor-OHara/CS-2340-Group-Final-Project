package com.group19.javafxgame.component;

import com.almasb.fxgl.dsl.FXGL;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.player.DefaultPlayerListener;
import com.group19.javafxgame.player.IPlayerListener;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class PlayerComponent extends CharacterComponent {

    private int monsterKillCount = 0;
    private int attacks = 1;
    private IPlayerListener playerListener;
    public PlayerComponent(int health,
                           int strength,
                           Point2D location) {

        super(health, strength, location);
        this.playerListener = new DefaultPlayerListener();
        FXGL.run(this::addAttack, Duration.seconds(Constants.getPlayerAttackSpeed()));
    }
    public void addAttack() {
        attacks += attacks <= 1 ? 1 : 0;
        //System.out.println(attacks);
    }

    public int getAttacks() {
        return attacks;
    }

    public void attack() {
        attacks--;
    }
    public PlayerComponent() {
        this(Constants.getDefaultPlayerHealth(),
                Constants.getDefaultPlayerStrength(),
                Constants.getDefaultPosition());
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

    public int getMonsterKillCount() {
        return monsterKillCount;
    }

    public void setHealth(int health) {
        this.health = health;
        FXGL.set("PlayerHealthUI", health);
    }

    public void subtractHealth(int health) {
        int oldHealth = this.health;
        this.health -= health;
        FXGL.set("playerHealthUI", FXGL.geti("playerHealthUI") - health);
        playerListener.healthDidDrop(oldHealth, this.health);
    }

    public void addHealth(int health) {
        this.health += health;
        this.health = Math.min(this.health, 100);
        FXGL.set("playerHealthUI", Math.min(FXGL.geti("playerHealthUI")
                +  health, 100));
    }
    public void incrementMonsterKillCount() {
        monsterKillCount++;
    }
}
