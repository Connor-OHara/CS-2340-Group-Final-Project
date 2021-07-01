package com.group19.javafxgame.component;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;

import com.almasb.fxgl.entity.component.Component;

public abstract class CharacterComponent extends Component {
    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_STRENGTH = 100;
    private static final Point2D DEFAULT_STARTING_POS = new Point2D(100, 100);

    protected int health;
    protected int strength;


    public CharacterComponent(int health, int strength, Point2D location) {
        super();
        this.entity = new Entity();
        this.health = health;
        this.strength = strength;
        this.entity.setPosition(location);
    }

    public CharacterComponent(Point2D location) {
        this(DEFAULT_HEALTH, DEFAULT_STRENGTH, location);
    }

    public CharacterComponent() {
        this(DEFAULT_STARTING_POS);
    }

    public void translateLeft(Double dx) {
        entity.translateX(-dx);
    }
    public void translateLeft() {
        translateLeft(10d);
    }

    public void translateRight(Double dx) {
        entity.translateX(dx);
    }
    public void translateRight() {
        translateRight(10d);
    }

    public void translateUp(Double dy) {
        entity.translateY(-dy);
    }
    public void translateUp() {
        translateUp(10d);
    }

    public void translateDown(Double dy) {
        entity.translateY(dy);
    }
    public void translateDown() {
        translateDown(10d);
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
