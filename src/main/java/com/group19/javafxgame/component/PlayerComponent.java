package com.group19.javafxgame.component;
import com.almasb.fxgl.entity.component.Component;

import javafx.geometry.Point2D;

public class PlayerComponent extends Component {

    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_STRENGTH = 100;
    private static final Point2D DEFAULT_STARTING_POS = new Point2D(100, 100);
    private static final int DEFAULT_MONEY = 10;

    private int health;
    private int strength;
    private Point2D location;
    private int money;

    public PlayerComponent(int health, int strength, Point2D location,int money) {
        this.health = health;
        this.strength = strength;
        this.location = location;
        this.money = money;
    }

    public PlayerComponent(Point2D location) {
       this(DEFAULT_HEALTH, DEFAULT_STRENGTH, location, DEFAULT_MONEY);
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

    public int getMoney(){
        return money;
    }




}
