package com.group19.javafxgame.rooms;


import com.almasb.fxgl.entity.component.Component;
import com.group19.javafxgame.component.PlayerInteractionComponent;

import com.group19.javafxgame.types.DoorLocation;
import com.group19.javafxgame.utils.Point2I;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;


public class RoomComponent extends Component {

    private final Room[][] maze = new Room[15][15];
    private Point2I currentLocation;
    private final RoomUtils roomUtils;
    private int roomGenerationCount = 0;

    private final HashSet<Room> visitedRooms = new HashSet<>();


    public RoomComponent() {
        currentLocation = new Point2I(maze[0].length / 2, maze.length / 2);
        roomUtils = new RoomUtils(maze);
        Room startRoom = Room.START.clone();
        startRoom.setStart(true);
        startRoom.setCleared(true);
        setRoom(startRoom, currentLocation);
    }

    private void generateNewRoomIfNeeded(Point2I coordinates) {
        if (roomUtils.hasRoom(coordinates)) {
            return;
        }
        if (roomUtils.isLeftEdge(coordinates)) {
            setRoom(Room.FINAL_RIGHT, coordinates);
            return;
        } else if (roomUtils.isRightEdge(coordinates)) {
            setRoom(Room.FINAL_LEFT, coordinates);
            return;
        } else if (roomUtils.isTopEdge(coordinates)) {
            setRoom(Room.FINAL_BOTTOM, coordinates);
            return;
        } else if (roomUtils.isBottomEdge(coordinates)) {
            setRoom(Room.FINAL_TOP, coordinates);
            return;
        }

        LinkedList<DoorLocation> requiredDoors = new LinkedList<>();
        LinkedList<DoorLocation> forbiddenDoors = new LinkedList<>();
        roomUtils.populateDoorGeneration(requiredDoors, forbiddenDoors, coordinates);
        setRoom(generateNewRoom(requiredDoors, forbiddenDoors), coordinates);
    }

    private void setRoom(Room room, Point2I coordinates) {
        maze[coordinates.getY()][coordinates.getX()] = room;
        visitedRooms.add(room);
        roomGenerationCount++;
    }

    private Room generateNewRoom(List<DoorLocation> requiredDoors,
                                 List<DoorLocation> forbiddenDoors) {
        HashSet<Room> roomsWithRequiredDoors =
                (HashSet<Room>) Room.roomsWithDoor(requiredDoors.get(0)).clone();
        for (int i = 1; i < requiredDoors.size(); i++) {
            roomsWithRequiredDoors.retainAll(Room.roomsWithDoor(requiredDoors.get(i)));
        }

        for (int i = 0; i < forbiddenDoors.size(); i++) {
            roomsWithRequiredDoors.removeAll(Room.roomsWithDoor(forbiddenDoors.get(i)));
        }

        roomsWithRequiredDoors.remove(getCurrentRoom());
        HashSet<Room> unvisitedRooms = (HashSet<Room>) roomsWithRequiredDoors.clone();
        unvisitedRooms.removeAll(visitedRooms);
        Object[] roomArray = unvisitedRooms.isEmpty()
                ? roomsWithRequiredDoors.toArray()
                : unvisitedRooms.toArray();
        Random random = new Random();
        int randomNumber = random.nextInt(roomArray.length);
        Room selectedRoom = ((Room) roomArray[randomNumber]).clone();
        selectedRoom.addMonsters();

        return selectedRoom;
    }

    public void goThroughDoor(DoorLocation doorLocation) {
        Point2I newCoordinate;
        DoorLocation outDoor;

        Room currRoom = getCurrentRoom();

        switch (doorLocation) {
        //checkstyle hates this indentation :/
        case LEFT:
            newCoordinate = new Point2I(currentLocation.getX() - 1, currentLocation.getY());
            outDoor = DoorLocation.RIGHT;
            break;
        case RIGHT:
            newCoordinate = new Point2I(currentLocation.getX() + 1, currentLocation.getY());
            outDoor = DoorLocation.LEFT;
            break;
        case TOP:
            newCoordinate = new Point2I(currentLocation.getX(), currentLocation.getY() - 1);
            outDoor = DoorLocation.BOTTOM;
            break;
        case BOTTOM:
            newCoordinate = new Point2I(currentLocation.getX(), currentLocation.getY() + 1);
            outDoor = DoorLocation.TOP;
            break;
        default:
            throw new NoSuchElementException("There is just no possible way...");
        }

        generateNewRoomIfNeeded(newCoordinate);
        Room newRoom = roomUtils.getRoom(newCoordinate);
        currentLocation = newCoordinate;
        PlayerInteractionComponent interaction =
                getEntity().getComponent(PlayerInteractionComponent.class);

        switch (outDoor) {
        case LEFT:
            interaction.setPosition(newRoom.getLeftSpawn());
            newRoom.setLastDoor(DoorLocation.LEFT);
            break;
        case RIGHT:
            interaction.setPosition(newRoom.getRightSpawn());
            newRoom.setLastDoor(DoorLocation.RIGHT);
            break;
        case TOP:
            interaction.setPosition(newRoom.getTopSpawn());
            newRoom.setLastDoor(DoorLocation.TOP);
            break;
        case BOTTOM:
            interaction.setPosition(newRoom.getBottomSpawn());
            newRoom.setLastDoor(DoorLocation.BOTTOM);
            break;
        default:
            System.out.println("ERROR IN outDoor Switch case");
            break;
        }




        currRoom.removeMonsters();

        if (!newRoom.isCleared() && newRoom.isVisited()
                && !newRoom.isStart() && !newRoom.isFinal()) {
            newRoom.addMonsters();
        }
        if (newRoom.isFinal()) {
            newRoom.addMonsters();
            newRoom.addMonsters();
        }

        newRoom.showMonsters();
        newRoom.applyLevel();
        newRoom.setVisited(true);

        entity.setZ(Integer.MAX_VALUE);
    }

    public Room getCurrentRoom() {
        return roomUtils.getRoom(currentLocation);
    }
    public int getRoomGenerationCount() {
        return roomGenerationCount;
    }
}
