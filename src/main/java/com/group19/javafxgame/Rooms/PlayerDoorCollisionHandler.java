package com.group19.javafxgame.Rooms;

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

        DoorComponent currDoor = a.getComponent(DoorComponent.class);
        DoorLocation doorLocation = currDoor.getDoorLocation();
        b.getComponent(RoomComponent.class).goThroughDoor(doorLocation);

//        String doorType = currDoor.getSide();
//
//        Point2I nextCoordinate = new
//                RoomDoorUtils()
//                .doorSideToRoomCoordinate(doorType, currRoom);
//
//        System.out.println("Curr x" + currRoom.getXGrid() + " y"  + currRoom.getYGrid());
//
//        int x = nextCoordinate.getX();
//        int y = nextCoordinate.getY();
//        String roomFile;
//        System.out.println("Next x" + x + " y"  + y);
//
//        if (RoomComponent.getMaze()[x][y] != null) {
//            player.setPosition(Constants.getDefaultPosition());
//            loadRoom(RoomComponent.getMaze()[x][y]);
//            String filename = RoomComponent.getMaze()[x][y].getFilename();
//            System.out.println("Went back to " + filename);
//            player.setZ(Integer.MAX_VALUE);
//
//        } else if (x > 11 || x < 1 || y > 11 || y < 1 ) {
//            System.out.println("Made to the final room");
//            roomFile = RandomRoomUtils.getInstance().getRandomRoom("final" + doorType);
//            System.out.println("Final file name: " + roomFile);
//            player.setPosition(Constants.getDefaultPosition());
//            loadRoom(roomFile, x, y);
//            player.setZ(Integer.MAX_VALUE);
//
//        } else {
//            System.out.println(doorType);
//            roomFile = RandomRoomUtils.getInstance().getRandomRoom(doorType);
//            System.out.println("Currently at : " + roomFile);
//            System.out.println(player.getComponent(PhysicsComponent.class).getBody().getPosition());
//            player.getComponent(PlayerInteractionComponent.class).setPosition(Constants.getDefaultPosition());
//            loadRoom(roomFile, x, y);
//            player.setZ(Integer.MAX_VALUE);
//
//        }


        System.out.println("Collided with door");
    }
}
