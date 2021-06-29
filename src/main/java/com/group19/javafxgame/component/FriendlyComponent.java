package com.group19.javafxgame.component;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class FriendlyComponent extends CharacterComponent {

    public boolean haveInteracted;
    /**
     *
     * Another thing we could have is that doors in the current
     * room remain locked until we kill the monsters or talk to
     * the NPC
     *
     */
    public FriendlyComponent() {
        super();
        this.haveInteracted = false;
    }
    public FriendlyComponent(int health, int strength, Point2D location) {
        super(health, strength, location);
        this.haveInteracted = false;
    }

    public FriendlyComponent(Point2D location) {
        super(location);
        this.haveInteracted = false;
    }

}
