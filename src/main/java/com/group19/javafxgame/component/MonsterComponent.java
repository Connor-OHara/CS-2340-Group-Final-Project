package com.group19.javafxgame.component;
import com.group19.javafxgame.Constants;
import javafx.geometry.Point2D;


public class MonsterComponent extends CharacterComponent {

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

}
