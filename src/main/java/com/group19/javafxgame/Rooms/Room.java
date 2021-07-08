package com.group19.javafxgame.Rooms;

import com.almasb.fxgl.dsl.FXGL;
import com.group19.javafxgame.types.DoorLocation;
import com.group19.javafxgame.utils.Point2I;
import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.HashSet;

public class Room {

    private static final Room MIDDLE_1 = new Room(
        "Middle1.tmx",
        new Point2I(2, 19),
        new Point2I(77, 18),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static final Room MIDDLE_2 = new Room(
        "Middle2.tmx",
        new Point2I(2, 19),
        new Point2I(77, 18),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static final Room MIDDLE_3 = new Room(
        "Middle3.tmx",
        new Point2I(2, 19),
        new Point2I(77, 15),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static final Room MIDDLE_4 = new Room(
        "Middle4.tmx",
        new Point2I(2, 19),
        new Point2I(77, 15),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static final Room MIDDLE_5 = new Room(
        "Middle5.tmx",
        new Point2I(2, 19),
        new Point2I(77, 15),
        new Point2I(41, 2),
        new Point2I(41, 42)
    );

    private static final Room MIDDLE_6 = new Room(
        "Middle6.tmx",
        new Point2I(2, 22),
        new Point2I(77, 22),
        new Point2I(40, 2),
        new Point2I(40, 42)
    );

    private static final Room TLB = new Room(
            "TLB.tmx",
            new Point2I(2, 22),
            null,
            new Point2I(40, 2),
            new Point2I(40, 42)
    );

    private static final Room TRB = new Room(
            "TRB.tmx",
            null,
            new Point2I(77, 22),
            new Point2I(40, 2),
            new Point2I(40, 42)
    );

    private static final Room TUNNEL_1 = new Room(
        "Tunnel1.tmx",
        new Point2I(2, 19),
        new Point2I(77, 15),
        null,
        null
    );

    private static final Room TUNNEL_2 = new Room(
        "Tunnel2.tmx",
        new Point2I(2, 19),
        new Point2I(77, 20),
        null,
        null
    );

    private static final Room VERTICAL_TUNNEL_1 = new Room(
        "VertTunnel1.tmx",
        null,
        null,
        new Point2I(41, 2),
        new Point2I(35, 42)
    );

    private static final Room DEAD_END_TOP = new Room(
        "DeadEndTop.tmx",
        null,
        null,
        new Point2I(41, 2),
        null
    );

    public static final Room START = new Room(
            "Start.tmx",
            new Point2I(2, 22),
            new Point2I(77, 22),
            new Point2I(39, 2),
            new Point2I(39, 42)
    );

    public static final Room FINAL_LEFT = new Room(
            "FinalLeft.tmx",
            new Point2I(2, 22),
            null,
            null,
            null
    );

    public static final Room FINAL_RIGHT = new Room(
            "FinalRight.tmx",
            null,
            new Point2I(77, 22),
            null,
            null
    );

    public static final Room FINAL_TOP = new Room(
            "FinalTop.tmx",
            null,
            null,
            new Point2I(39, 2),
            null
    );

    public static final Room FINAL_BOTTOM = new Room(
            "FinalBottom.tmx",
            null,
            null,
            null,
            new Point2I(39, 42)
    );

    private static final HashSet<Room> ROOMS_LEFT_DOOR = new HashSet<>();
    private static final HashSet<Room> ROOMS_RIGHT_DOOR = new HashSet<>();
    private static final HashSet<Room> ROOMS_TOP_DOOR = new HashSet<>();
    private static final HashSet<Room> ROOMS_BOTTOM_DOOR = new HashSet<>();

    static {
        ROOMS_LEFT_DOOR.addAll(Arrays.asList(
                MIDDLE_1,
                MIDDLE_2,
                MIDDLE_3,
                MIDDLE_4,
                MIDDLE_5,
                MIDDLE_6,
                TUNNEL_1,
                TUNNEL_2,
                TLB
        ));

        ROOMS_RIGHT_DOOR.addAll(Arrays.asList(
                MIDDLE_1,
                MIDDLE_2,
                MIDDLE_3,
                MIDDLE_4,
                MIDDLE_5,
                MIDDLE_6,
                TUNNEL_1,
                TUNNEL_2,
                TRB
        ));

        ROOMS_TOP_DOOR.addAll(Arrays.asList(
                MIDDLE_1,
                MIDDLE_2,
                MIDDLE_3,
                MIDDLE_4,
                MIDDLE_5,
                MIDDLE_6,
                DEAD_END_TOP,
                VERTICAL_TUNNEL_1,
                TRB,
                TLB
        ));

        ROOMS_BOTTOM_DOOR.addAll(Arrays.asList(
                MIDDLE_1,
                MIDDLE_2,
                MIDDLE_3,
                MIDDLE_4,
                MIDDLE_5,
                MIDDLE_6,
                VERTICAL_TUNNEL_1,
                TRB,
                TLB
        ));
    }

    private final String filename;
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

    public Room(String filename,
                Point2D leftSpawn,
                Point2D rightSpawn,
                Point2D topSpawn,
                Point2D bottomSpawn) {
        this.filename = filename;
        this.leftSpawn = leftSpawn;
        this.rightSpawn = rightSpawn;
        this.topSpawn = topSpawn;
        this.bottomSpawn = bottomSpawn;
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
        return ROOMS_LEFT_DOOR;
    }

    public static HashSet<Room> getRoomsRightDoor() {
        return ROOMS_RIGHT_DOOR;
    }

    public static HashSet<Room> getRoomsTopDoor() {
        return ROOMS_TOP_DOOR;
    }

    public static HashSet<Room> getRoomsBottomDoor() {
        return ROOMS_BOTTOM_DOOR;
    }

    public Room clone() {
        return new Room(filename, leftSpawn, rightSpawn, topSpawn, bottomSpawn);
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

    @Override
    public int hashCode() {
        return filename.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Room && ((Room) other).filename.equals(filename);
    }
}
