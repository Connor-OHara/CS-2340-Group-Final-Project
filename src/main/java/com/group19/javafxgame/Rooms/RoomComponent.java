package com.group19.javafxgame.Rooms;

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

    private Room[][] maze = new Room[13][13];
    private Point2I currentLocation;

    HashSet<Room> visitedRooms = new HashSet<Room>();

    public RoomComponent() {
        currentLocation = new Point2I(maze[0].length / 2, maze.length / 2);
        Room startRoom = Room.START.clone();
        maze[currentLocation.getY()][currentLocation.getX()] = startRoom;
        visitedRooms.add(startRoom);
    }

    private void generateNewRoomIfNeeded(Point2I coordinates) {
        if (maze[coordinates.getY()][coordinates.getX()] != null) {
            return;
        }

        if (coordinates.getX() <= 0) {
            setRoom(Room.FINAL_RIGHT, coordinates);
            return;
        } else if (coordinates.getX() >= maze[0].length - 1) {
            setRoom(Room.FINAL_LEFT, coordinates);
            return;
        } else if (coordinates.getY() <= 0) {
            setRoom(Room.FINAL_BOTTOM, coordinates);
            return;
        } else if (coordinates.getY() >= maze.length - 1) {
            setRoom(Room.FINAL_TOP, coordinates);
            return;
        }

        LinkedList<DoorLocation> requiredDoors = new LinkedList<DoorLocation>();
        LinkedList<DoorLocation> forbiddenDoors = new LinkedList<DoorLocation>();
        if (coordinates.getX() != currentLocation.getX()) {
            if (coordinates.getX() > currentLocation.getX()) {
                requiredDoors.add(DoorLocation.LEFT);
            } else {
                requiredDoors.add(DoorLocation.RIGHT);
            }
            if (coordinates.getY() > 0 &&
                    maze[coordinates.getY() - 1][coordinates.getX()] != null) {
                if (maze[coordinates.getY() - 1][coordinates.getX()].getBottomSpawn() == null) {
                    forbiddenDoors.add(DoorLocation.TOP);
                } else {
                    requiredDoors.add(DoorLocation.TOP);
                }
            }
            if (coordinates.getY() < maze.length - 1 &&
                    maze[coordinates.getY() + 1][coordinates.getX()] != null) {
                if (maze[coordinates.getY() + 1][coordinates.getX()].getTopSpawn() == null) {
                    forbiddenDoors.add(DoorLocation.BOTTOM);
                } else {
                    requiredDoors.add(DoorLocation.BOTTOM);
                }
            }
        } else if (coordinates.getY() != currentLocation.getY()) {
            if (coordinates.getY() > currentLocation.getY()) {
                requiredDoors.add(DoorLocation.TOP);
            } else {
                requiredDoors.add(DoorLocation.BOTTOM);
            }
            if (coordinates.getX() > 0 &&
                    maze[coordinates.getY()][coordinates.getX() - 1] != null) {
                if (maze[coordinates.getY()][coordinates.getX() - 1].getRightSpawn() == null) {
                    forbiddenDoors.add(DoorLocation.LEFT);
                } else {
                    requiredDoors.add(DoorLocation.LEFT);
                }
            }
            if (coordinates.getX() < maze[0].length - 1 &&
                    maze[coordinates.getY()][coordinates.getX() + 1] != null) {
                if (maze[coordinates.getY()][coordinates.getX() + 1].getLeftSpawn() == null) {
                    forbiddenDoors.add(DoorLocation.RIGHT);
                } else {
                    requiredDoors.add(DoorLocation.RIGHT);
                }
            }
        }

        setRoom(generateNewRoom(requiredDoors, forbiddenDoors), coordinates);
    }

    private void setRoom(Room room, Point2I coordinates) {
        maze[coordinates.getY()][coordinates.getX()] = room;
        visitedRooms.add(room);
    }

    private Room generateNewRoom(List<DoorLocation> requiredDoors, List<DoorLocation> forbiddenDoors) {
        HashSet<Room> roomsWithRequiredDoors = (HashSet<Room>) Room.roomsWithDoor(requiredDoors.get(0)).clone();
        for (int i = 1; i < requiredDoors.size(); i++) {
            roomsWithRequiredDoors.retainAll(Room.roomsWithDoor(requiredDoors.get(i)));
        }

        for (int i = 0; i < forbiddenDoors.size(); i++) {
            roomsWithRequiredDoors.removeAll(Room.roomsWithDoor(forbiddenDoors.get(i)));
        }

        HashSet<Room> unvisitedRooms = (HashSet<Room>) roomsWithRequiredDoors.clone();
        System.out.println("ROOMS BEFORE: " + unvisitedRooms.size() + ", " + visitedRooms.size());
        unvisitedRooms.removeAll(visitedRooms);
        System.out.println("ROOMS AFTER: " + unvisitedRooms.size());
        System.out.println("UNVISITED ROOMS EMPTY: " + unvisitedRooms.isEmpty());
        Object[] roomArray = unvisitedRooms.isEmpty() ?
            roomsWithRequiredDoors.toArray() :
            unvisitedRooms.toArray();
        Random random = new Random();
        int randomNumber = random.nextInt(roomArray.length);
        Room selectedRoom = ((Room) roomArray[randomNumber]).clone();
        return selectedRoom;
    }

    public void goThroughDoor(DoorLocation doorLocation) {
        Point2I newCoordinate;
        DoorLocation outDoor;
        switch (doorLocation) {
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
        Room newRoom = maze[newCoordinate.getY()][newCoordinate.getX()];
        currentLocation = newCoordinate;
        PlayerInteractionComponent interaction = getEntity().getComponent(PlayerInteractionComponent.class);

        switch (outDoor) {
            case LEFT:
                interaction.setPosition(newRoom.getLeftSpawn());
                break;
            case RIGHT:
                interaction.setPosition(newRoom.getRightSpawn());
                break;
            case TOP:
                interaction.setPosition(newRoom.getTopSpawn());
                break;
            case BOTTOM:
                interaction.setPosition(newRoom.getBottomSpawn());
                break;
        }

        newRoom.applyLevel();
        entity.setZ(Integer.MAX_VALUE);
    }

    public Room getCurrentRoom() {
        return maze[currentLocation.getY()][currentLocation.getX()];
    }
}
