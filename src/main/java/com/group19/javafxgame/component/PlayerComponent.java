package com.group19.javafxgame.component;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import static com.almasb.fxgl.dsl.FXGL.*;

import javafx.geometry.Point2D;

public class PlayerComponent extends Component {

    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_STRENGTH = 100;
    private static final Point2D DEFAULT_STARTING_POS = new Point2D(100, 100);

    private int health;
    private int strength;

    public PlayerComponent(int health, int strength, Point2D location) {
        super();
        this.entity = new Entity();
        this.health = health;
        this.strength = strength;
        this.entity.setPosition(location);
    }

    public PlayerComponent(Point2D location) {
       this(DEFAULT_HEALTH, DEFAULT_STRENGTH, location);
    }

    public PlayerComponent() {
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

    //can add positive or negative number
    public int addFunds(int numb){
        FXGL.inc("money", numb);
        return geti("money");
    }

    public int showFunds(){
        return geti("money");
    }


}
