package com.group19.javafxgame.component;

import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.Constants;
import javafx.geometry.Point2D;

import com.almasb.fxgl.entity.component.Component;

public class CharacterComponent extends Component {
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
        this(Constants.getDefaultPlayerHealth(),
                Constants.getDefaultPlayerStrength(),
                location);
    }

    public CharacterComponent() {
        this(Constants.getDefaultPosition());
    }

    public void translateLeft(Double dx) {
        entity.translateX(-dx);
    }
    public void translateLeft() {
        translateLeft(1.5d);
    }

    public void translateRight(Double dx) {
        entity.translateX(dx);
    }
    public void translateRight() {
        translateRight(1.5d);
    }

    public void translateUp(Double dy) {
        entity.translateY(-dy);
    }
    public void translateUp() {
        translateUp(1.5d);
    }

    public void translateDown(Double dy) {
        entity.translateY(dy);
    }
    public void translateDown() {
        translateDown(1.5d);
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
