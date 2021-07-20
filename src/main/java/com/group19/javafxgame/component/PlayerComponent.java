package com.group19.javafxgame.component;

import com.almasb.fxgl.dsl.FXGL;
import com.group19.javafxgame.Constants;
import javafx.geometry.Point2D;

public class PlayerComponent extends CharacterComponent {

    public PlayerComponent(int health,
                           int strength,
                           Point2D location) {

        super(health, strength, location);

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

    public void setHealth(int health) {
        this.health = health;
        FXGL.set("PlayerHealthUI", health);
    }
}
