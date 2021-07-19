package com.group19.javafxgame.component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.group19.javafxgame.Constants;

import javafx.geometry.Point2D;

public class BombComponent extends Component {
    private final double timer = 2.0;
    public BombComponent(Point2D location) {
        super();
        this.entity = new Entity();
    }
    public BombComponent() {
        this(Constants.getDefaultPosition());
    }
    public double getTimer() {
        return timer;
    }
}
