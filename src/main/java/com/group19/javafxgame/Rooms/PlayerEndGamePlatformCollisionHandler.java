package com.group19.javafxgame.Rooms;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

public class PlayerEndGamePlatformCollisionHandler extends CollisionHandler {
    /**
     * The order of types determines the order of entities in callbacks.
     *
     * @param a entity type of the first entity
     * @param b entity type of the second entity
     */
    public PlayerEndGamePlatformCollisionHandler(Object a, Object b) {
        super(a, b);
    }

    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
        FXGL.set("gameWin", 1);
    }
}
