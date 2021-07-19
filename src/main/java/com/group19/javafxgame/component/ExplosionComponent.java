package com.group19.javafxgame.component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.group19.javafxgame.Constants;

import javafx.geometry.Point2D;

public class ExplosionComponent extends Component {
    private int damage;
    public ExplosionComponent(int damage, Point2D location) {
        super();
        this.entity = new Entity();
        this.damage = damage;
    }
    public ExplosionComponent(Point2D location) {
        this(Constants.getDefaultPlayerStrength() / 2, location);
    }
    public ExplosionComponent(int damage) {
        this(damage, Constants.getDefaultPosition());
    }
    public ExplosionComponent() {
        this(Constants.getDefaultPosition());
    }
    public int getDamage() {
        return damage;
    }
}
