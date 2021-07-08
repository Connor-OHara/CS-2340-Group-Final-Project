package com.group19.javafxgame.rooms;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.group19.javafxgame.types.DoorLocation;

public class PlayerDoorCollisionHandler extends CollisionHandler {
    /**
     * The order of types determines the order of entities in callbacks.
     *
     * @param a entity type of the first entity
     * @param b entity type of the second entity
     */
    public PlayerDoorCollisionHandler(Object a, Object b) {
        super(a, b);
    }

    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);

        DoorComponent currDoor = b.getComponent(DoorComponent.class);
        DoorLocation doorLocation = currDoor.getDoorLocation();
        a.getComponent(RoomComponent.class).goThroughDoor(doorLocation);
        System.out.println("Collided with door");
    }
}
