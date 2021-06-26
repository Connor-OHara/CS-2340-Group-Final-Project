package com.group19.javafxgame.component;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {

    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_STRENGTH = 100;
    private static final Point2D DEFAULT_STARTING_POS = new Point2D(100, 100);

    private int health;
    private int strength;
    private Point2D location;

    public PlayerComponent(int health, int strength, Point2D location) {
        this.health = health;
        this.strength = strength;
        this.location = location;
    }

    public PlayerComponent(Point2D location) {
       this(DEFAULT_HEALTH, DEFAULT_STRENGTH, location);
    }

    public PlayerComponent() {
        this(DEFAULT_STARTING_POS);
    }

    public void left() {
        entity.translateX(-10);
    }

    public void right() {
        entity.translateX(10);
    }

    public void up() {
        entity.translateY(-10);
    }

    public void down() {
        entity.translateY(10);
    }


    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public Point2D getLocation() {
        return location;
    }

    //can add positive or negative number
    public int addFunds(int numb){
        FXGL.inc("money", numb);
        return geti("money");
    }

    public int showFunds(){
        return geti("money");
    }

}
