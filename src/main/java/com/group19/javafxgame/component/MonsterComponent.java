package com.group19.javafxgame.component;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.group19.javafxgame.Constants;
import javafx.geometry.Point2D;


public class MonsterComponent extends CharacterComponent {

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
}
