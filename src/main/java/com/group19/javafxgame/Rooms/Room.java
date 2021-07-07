package com.group19.javafxgame.Rooms;

import com.almasb.fxgl.dsl.FXGL;
import com.group19.javafxgame.types.DoorLocation;
import com.group19.javafxgame.utils.Point2I;
import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.HashSet;

public class Room {

    private static Room middle1 = new Room(
        "Middle1.tmx",
        new Point2I(2, 19),
        new Point2I(77, 18),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static Room middle2 = new Room(
        "Middle2.tmx",
        new Point2I(2, 19),
        new Point2I(77, 18),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static Room middle3 = new Room(
        "Middle3.tmx",
        new Point2I(2, 19),
        new Point2I(77, 15),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static Room middle4 = new Room(
        "Middle4.tmx",
        new Point2I(2, 19),
        new Point2I(77, 15),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static Room middle5 = new Room(
        "Middle5.tmx",
        new Point2I(2, 19),
        new Point2I(77, 15),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    public final static Room middle6 = new Room(
            "Middle6.tmx",
            new Point2I(2, 19),
            new Point2I(77, 15),
            new Point2I(41, 2),
            new Point2I(41, 42)
    );

    private static Room tunnel1 = new Room(
        "Tunnel1.tmx",
        new Point2I(2, 19),
        new Point2I(77, 15),
        null,
        null
    );

    private static Room tunnel2 = new Room(
            "Tunnel2.tmx",
            new Point2I(2, 19),
            new Point2I(77, 20),
            null,
            null
    );

    private static Room vertTunnel1 = new Room(
            "VertTunnel1.tmx",
            null,
            null,
            new Point2I(41, 2),
            new Point2I(35, 42)
    );

    private static Room deadEndTop = new Room(
            "DeadEndTop.tmx",
            null,
            null,
            new Point2I(41, 2),
            null
    );

    final public static Room START = new Room(
            "Start.tmx",
            new Point2I(2, 19),
            new Point2I(77, 19),
            new Point2I(35, 2),
            new Point2I(35, 42)
    );

    final public static Room FINAL_LEFT = new Room(
            "FinalLeft.tmx",
            new Point2I(2, 21),
            null,
            null,
            null
    );

    final public static Room FINAL_RIGHT = new Room(
            "FinalRight.tmx",
            null,
            new Point2I(77, 18),
            null,
            null
    );

    final public static Room FINAL_TOP = new Room(
            "FinalTop.tmx",
            null,
            null,
            new Point2I(41, 2),
            null
    );

    public final static Room FINAL_BOTTOM = new Room(
            "FinalBottom.tmx",
            null,
            null,
            null,
            new Point2I(36, 42)
    );

    private static HashSet<Room> roomsLeftDoor = new HashSet<Room>();
    private static HashSet<Room> roomsRightDoor = new HashSet<Room>();
    private static HashSet<Room> roomsTopDoor = new HashSet<Room>();
    private static HashSet<Room> roomsBottomDoor = new HashSet<Room>();

    static {
        roomsLeftDoor.addAll(Arrays.asList(new Room[] {
            middle1,
            middle2,
            middle3,
            middle4,
            middle5,
            middle6,
            tunnel1,
            tunnel2
        }));

        roomsRightDoor.addAll(Arrays.asList(new Room[] {
            middle1,
            middle2,
            middle3,
            middle4,
            middle5,
            middle6,
            tunnel1,
            tunnel2
        }));

        roomsTopDoor.addAll(Arrays.asList(new Room[] {
            middle1,
            middle2,
            middle3,
            middle4,
            middle5,
            middle6,
            deadEndTop,
            vertTunnel1
        }));

        roomsBottomDoor.addAll(Arrays.asList(new Room[] {
            middle1,
            middle2,
            middle3,
            middle4,
            middle5,
            middle6,
            vertTunnel1
        }));
    }

    private String filename;
    private Point2D leftSpawn;
    private Point2D rightSpawn;
    private Point2D topSpawn;
    private Point2D bottomSpawn;

    public Room(String filename,
                Point2I leftSpawn,
                Point2I rightSpawn,
                Point2I topSpawn,
                Point2I bottomSpawn) {
        this.filename = filename;
        this.leftSpawn = leftSpawn == null ? null
                : new Point2D(leftSpawn.getX() * 16, leftSpawn.getY() * 16);
        this.rightSpawn = rightSpawn == null ? null
                : new Point2D(rightSpawn.getX() * 16, rightSpawn.getY() * 16);
        this.topSpawn = topSpawn == null ? null
                : new Point2D(topSpawn.getX() * 16, topSpawn.getY() * 16);
        this.bottomSpawn = bottomSpawn == null ? null
                : new Point2D(bottomSpawn.getX() * 16, bottomSpawn.getY() * 16);
    }

    public void applyLevel() {
        System.out.println("Setting room with " + this.filename);
        FXGL.setLevelFromMap(this.filename);
    }

    public Point2D getLeftSpawn() {
        return leftSpawn;
    }

    public Point2D getRightSpawn() {
        return rightSpawn;
    }

    public Point2D getTopSpawn() {
        return topSpawn;
    }

    public Point2D getBottomSpawn() {
        return bottomSpawn;
    }


    public static HashSet<Room> getRoomsLeftDoor() {
        return roomsLeftDoor;
    }

    public static HashSet<Room> getRoomsRightDoor() {
        return roomsRightDoor;
    }

    public static HashSet<Room> getRoomsTopDoor() {
        return roomsTopDoor;
    }

    public static HashSet<Room> getRoomsBottomDoor() {
        return roomsBottomDoor;
    }

    public Room clone() {
        Point2I dummyPoint = new Point2I(0, 0);
        Room toReturn = new Room(filename, dummyPoint, dummyPoint, dummyPoint, dummyPoint);
        toReturn.leftSpawn = leftSpawn;
        toReturn.rightSpawn = rightSpawn;
        toReturn.topSpawn = topSpawn;
        toReturn.bottomSpawn = bottomSpawn;
        return toReturn;
    }

    public static HashSet<Room> roomsWithDoor(DoorLocation doorLocation) {
        switch (doorLocation) {
            case LEFT:
            return Room.getRoomsLeftDoor();
            case RIGHT:
            return (HashSet<Room>) Room.getRoomsRightDoor().clone();
            case TOP:
            return (HashSet<Room>) Room.getRoomsTopDoor().clone();
            case BOTTOM:
            return (HashSet<Room>) Room.getRoomsBottomDoor().clone();
            default:
            throw new IllegalStateException("Unexpected value: " + doorLocation);
        }
    }
}
