package com.group19.javafxgame.component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.TimerAction;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.Main;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicReference;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;

public class SwordComponent extends Component {
    private int damage;
    private Point2D dir;
    public SwordComponent(int damage, Point2D dir) {
        super();
        this.entity = new Entity();
        this.damage = damage;
        this.dir = dir;
    }
    public SwordComponent() {
        this(Constants.getDefaultPlayerStrength() / 4, new Point2D(0, 0));
    }
    public SwordComponent(Point2D dir) {
        this(Constants.getDefaultPlayerStrength() / 4, dir);
    }
    @Override
    public void onAdded() {
        double dirRadians = Math.atan(dir.getY() / dir.getX());
        if (dir.getX() < 0) {
            dirRadians += Math.PI;
        } else if (dir.getY() < 0) {
            dirRadians += 2 * Math.PI;
        }
        AtomicReference<Double> rotation = new AtomicReference<>(-Math.PI / 4);
        Point2D pos = getEntity().getPosition();
        double finalDirRadians = dirRadians;
        TimerAction timer = getGameTimer().runAtInterval(() -> {
            rotation.updateAndGet(v -> (double) (v + Math.PI / 20));
            rotateSword(rotation.get() + finalDirRadians, getEntity());
        }, Duration.millis(20), 10);
        //I'd think 201 millis would work but it crashes /shrug
        getGameTimer().runOnceAfter(getEntity()::removeFromWorld, Duration.millis(300));
    }
    private void rotateSword(double radians, Entity sword) {
        if (sword == null) {
            return;
        }
        Point2D pos = Main.getPlayer().getCenter();
        double dirDegrees = radians * 180 / Math.PI;
        Point2D center = new Point2D((double) -Constants.getSwordLength() / 2, -2.5);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double x = (double) Constants.getSwordLength() / 2 + 8;
        //System.out.println(x);
        Point2D translate = new Point2D(cos * x, sin * x); //rotate 15 degrees left
        //System.out.println(translate);
        sword.setPosition(pos.add(center).add(translate));
        sword.setRotation(0);
        sword.rotateBy(dirDegrees);
    }

    public int getDamage() {
        return damage;
    }
}

