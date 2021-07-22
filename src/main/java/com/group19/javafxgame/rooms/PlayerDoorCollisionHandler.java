package com.group19.javafxgame.rooms;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.group19.javafxgame.component.PlayerInteractionComponent;
import com.group19.javafxgame.types.DoorLocation;
import javafx.geometry.Point2D;

import static com.group19.javafxgame.soundHandler.DoorSounds.playLockedDoor;
import static com.group19.javafxgame.soundHandler.DoorSounds.playUnlockedDoor;

public class PlayerDoorCollisionHandler extends CollisionHandler {
    /**
     * The order of types determines the order of entities in callba/cks.
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
        Room currRoom  = a.getComponent(RoomComponent.class).getCurrentRoom();
        DoorComponent currDoor = b.getComponent(DoorComponent.class);
        DoorLocation doorLocation = currDoor.getDoorLocation();
        DoorLocation lastDoor = currRoom.getLastDoor();
        //checks if room is cleared of enemies
        if (currRoom.isCleared() || doorLocation == lastDoor || FXGL.getb("DevMode")) {
            a.getComponent(RoomComponent.class).goThroughDoor(doorLocation);
            System.out.println("Collided with door");
            playUnlockedDoor();
        } else {
            FXGL.getGameScene().getViewport().shakeTranslational(8);
            //blocks player from exiting
            switch (doorLocation) {
            case TOP:
                a.getComponent(PlayerInteractionComponent.class).stopUp();
                Point2D newlocUp = new Point2D(a.getX(), a.getY() + 25.0);
                a.getComponent(PlayerInteractionComponent.class).setPosition(newlocUp);
                break;
            case BOTTOM:
                a.getComponent(PlayerInteractionComponent.class).stopDown();
                Point2D newlocDown = new Point2D(a.getX(), a.getY() - 25.0);
                a.getComponent(PlayerInteractionComponent.class).setPosition(newlocDown);
                break;
            case LEFT:
                a.getComponent(PlayerInteractionComponent.class).stopLeft();
                Point2D newlocLeft = new Point2D(a.getX() + 15.0, a.getY());
                a.getComponent(PlayerInteractionComponent.class).setPosition(newlocLeft);
                break;
            case RIGHT:
                a.getComponent(PlayerInteractionComponent.class).stopRight();
                Point2D newlocRight = new Point2D(a.getX() - 15.0, a.getY());
                a.getComponent(PlayerInteractionComponent.class).setPosition(newlocRight);
                break;
            default:
                break;
            }
            System.out.println("Collided with locked door");
            playLockedDoor();
        }



    }

}
