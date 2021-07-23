package com.group19.javafxgame.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.TimerAction;
import com.group19.javafxgame.Constants;

import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import static com.group19.javafxgame.soundHandler.CombatSounds.playExplosion;

public class BombComponent extends Component {
    private final double timer = 2.0;
    private TimerAction timed;
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
    @Override
    public void onAdded() {
        timed = getGameTimer().runOnceAfter(() -> {
            var explosion = FXGL.spawn("Explosion");
            playExplosion();
            explosion.setPosition(getEntity().getCenter()
                    .subtract(Constants.getDefaultBombRange(),
                            Constants.getDefaultBombRange()).add(8, 8));
            getEntity().removeFromWorld();
            getGameTimer().runOnceAfter(explosion::removeFromWorld,
                    Duration.millis(100)); },
            Duration.seconds(timer));
    }
    @Override
    public void onRemoved() {
        timed.expire();
    }
}
