package com.group19.javafxgame;

import com.group19.javafxgame.rooms.Room;
import com.group19.javafxgame.rooms.RoomUtils;
import com.group19.javafxgame.types.DoorGeneration;
import com.group19.javafxgame.types.DoorLocation;
import com.group19.javafxgame.utils.Point2I;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static java.lang.Boolean.TRUE;

public class RoomUtilsTest {

    private Room[][] maze;
    private RoomUtils roomUtils;
    private Point2I currentLocation;

    private Room startRoom = new Room(
        "Start.tmx",
        new Point2I(0, 1),
        new Point2I(2, 1),
        new Point2I(1, 0),
        new Point2I(1, 2)
    );

    private Room middleRoom = new Room(
            "Middle.tmx",
            new Point2I(0, 1),
            new Point2I(2, 1),
            new Point2I(1, 0),
            new Point2I(1, 2)
    );

    private Room rightTopBottomRoom = new Room(
            "RightTopBottom.tmx",
            null,
            new Point2I(2, 1),
            new Point2I(1, 0),
            new Point2I(1, 2)
    );

    private Room leftTopBottomRoom = new Room(
            "LeftTopBottom.tmx",
            new Point2I(0, 1),
            null,
            new Point2I(1, 0),
            new Point2I(1, 2)
    );

    private Room leftRightBottomRoom = new Room(
            "LeftRightBottom.tmx",
            new Point2I(0, 1),
            new Point2I(2, 1),
            null,
            new Point2I(1, 2)
    );

    private Room leftRightTopRoom = new Room(
            "LeftRightTop.tmx",
            new Point2I(0, 1),
            new Point2I(2, 1),
            new Point2I(1, 0),
            null
    );

    private Room topBottomRoom = new Room(
            "TopBottom.tmx",
            null,
            null,
            new Point2I(1, 0),
            new Point2I(1, 2)
    );

    private Room rightBottomRoom = new Room(
            "RightBottom.tmx",
            null,
            new Point2I(2, 1),
            null,
            new Point2I(1, 2)
    );

    private Room rightTopRoom = new Room(
            "RightTop.tmx",
            null,
            new Point2I(2, 1),
            new Point2I(1, 0),
            null
    );

    private Room leftBottomRoom = new Room(
            "LeftBottom.tmx",
            new Point2I(0, 1),
            null,
            null,
            new Point2I(1, 2)
    );

    private Room leftTopRoom = new Room(
            "LeftTop.tmx",
            new Point2I(0, 1),
            null,
            new Point2I(1, 0),
            null
    );

    private Room leftRightRoom = new Room(
            "LeftRight.tmx",
            new Point2I(0, 1),
            new Point2I(2, 1),
            null,
            null
    );

    private Room leftRoom = new Room(
            "Left.tmx",
            new Point2I(0, 1),
            null,
            null,
            null
    );

    private Room rightRoom = new Room(
            "Right.tmx",
            null,
            new Point2I(2, 1),
            null,
            null
    );

    private Room topRoom = new Room(
            "Top.tmx",
            null,
            null,
            new Point2I(1, 0),
            null
    );

    private Room bottomRoom = new Room(
            "Bottom.tmx",
            null,
            null,
            null,
            new Point2I(1, 2)
    );

    @BeforeEach
    public void setup() {
        maze = new Room[15][15];
        maze[7][7] = startRoom;
        roomUtils = new RoomUtils(maze);
        currentLocation = new Point2I(6, 6);
    }

    @Test
    public void checkRoomExists() {
        Point2I origin = new Point2I(7, 7);
        Assertions.assertTrue(roomUtils.hasRoom(origin));

        Point2I leftCoordinates = origin.getLeft();
        Assertions.assertFalse(roomUtils.hasRoom(leftCoordinates));
        maze[leftCoordinates.getY()][leftCoordinates.getX()] = rightRoom;
        Assertions.assertTrue(roomUtils.hasRoom(leftCoordinates));
    }

    @Test
    public void checkDoorGenerationRequirements() {
        Point2I origin = new Point2I(7, 7);
        Point2I rightCoordinates = origin.getRight();
        Point2I topCoordinates = origin.getUp();
        maze[rightCoordinates.getY()][rightCoordinates.getX()] = leftTopRoom;
        maze[topCoordinates.getY()][topCoordinates.getX()] = bottomRoom;

        Point2I newRoomCoordinates = rightCoordinates.getUp();
        Assertions.assertEquals(
                roomUtils.getLeftDoorGeneration(newRoomCoordinates),
                DoorGeneration.FORBIDDEN
        );
        Assertions.assertEquals(
            roomUtils.getBottomDoorGeneration(newRoomCoordinates),
            DoorGeneration.REQUIRED
        );
        Assertions.assertEquals(
                roomUtils.getTopDoorGeneration(newRoomCoordinates),
                DoorGeneration.OPTIONAL
        );
        Assertions.assertEquals(
                roomUtils.getRightDoorGeneration(newRoomCoordinates),
                DoorGeneration.OPTIONAL
        );
    }

    @Test
    public void checkEdges() {
        //Checks bounding box methods for 2d grid

        //checks left edge
        for (Point2I origin = new Point2I(7, 7); origin.getX() > -16;
            origin.setX(origin.getX() - 1)) {

            if (roomUtils.isLeftEdge(origin) == TRUE) {
                Assertions.assertNull(roomUtils.getLeftRoom(origin));
                break;
            }
        }

        //checks right edge
        for (Point2I origin = new Point2I(7, 7); origin.getX() > -16;
             origin.setX(origin.getX() + 1)) {

            if (roomUtils.isRightEdge(origin) == TRUE) {
                Assertions.assertNull(roomUtils.getRightRoom(origin));
                break;
            }
        }

        //checks top edge
        for (Point2I origin = new Point2I(7, 7); origin.getY() < -1;
             origin.setY(origin.getY() - 1)) {

            if (roomUtils.isTopEdge(origin) == TRUE) {
                Assertions.assertNull(roomUtils.getTopRoom(origin));
                break;
            }
        }

        //checks bottom edge
        for (Point2I origin = new Point2I(7, 7); origin.getY() < 16;
             origin.setY(origin.getY() + 1)) {

            if (roomUtils.isBottomEdge(origin) == TRUE) {
                Assertions.assertNull(roomUtils.getBottomRoom(origin));
                break;
            }
        }
    }

    @Test
    public void testHasLeft() {

        Point2I origin = new Point2I(7, 7);
        Point2I edge = new Point2I(7, 0);
        maze[7][7] = startRoom;
        Assertions.assertFalse(roomUtils.hasLeftRoom(origin));
        maze[7][6] = middleRoom;
        Assertions.assertTrue(roomUtils.hasLeftRoom(origin));
        maze[7][0] = leftRoom;
        Assertions.assertFalse(roomUtils.hasLeftRoom(edge));
    }

    @Test
    public void testHasRight() {
        Point2I origin = new Point2I(7, 7);
        Point2I edge = new Point2I(7, 14);
        maze[7][7] = startRoom;
        Assertions.assertFalse(roomUtils.hasRightRoom(origin));
        maze[7][8] = middleRoom;
        Assertions.assertTrue(roomUtils.hasRightRoom(origin));
        maze[7][14] = rightRoom;
        Assertions.assertFalse(roomUtils.hasRightRoom(edge));
    }

    @Test
    public void testHasTop() {
        Point2I origin = new Point2I(7, 7);
        Point2I edge = new Point2I(0, 7);
        maze[7][7] = startRoom;
        Assertions.assertFalse(roomUtils.hasTopRoom(origin));
        maze[6][7] = middleRoom;
        Assertions.assertTrue(roomUtils.hasTopRoom(origin));
        maze[0][7] = topRoom;
        Assertions.assertFalse(roomUtils.hasTopRoom(edge));
    }

    @Test
    public void testHasBottom() {
        Point2I origin = new Point2I(7, 7);
        Point2I edge = new Point2I(14, 7);
        maze[7][7] = startRoom;
        Assertions.assertFalse(roomUtils.hasBottomRoom(origin));
        maze[8][7] = middleRoom;
        Assertions.assertTrue(roomUtils.hasBottomRoom(origin));
        maze[14][7] = bottomRoom;
        Assertions.assertFalse(roomUtils.hasBottomRoom(edge));

    }
    private void generateNewRoomIfNeeded(Point2I coordinates) {
        if (roomUtils.isLeftEdge(coordinates)) {
            maze[coordinates.getX()][coordinates.getY()] = Room.FINAL_RIGHT;
        } else if (roomUtils.isRightEdge(coordinates)) {
            maze[coordinates.getX()][coordinates.getY()] = Room.FINAL_LEFT;
        } else if (roomUtils.isTopEdge(coordinates)) {
            maze[coordinates.getX()][coordinates.getY()] = Room.FINAL_BOTTOM;
        } else if (roomUtils.isBottomEdge(coordinates)) {
            maze[coordinates.getX()][coordinates.getY()] = Room.FINAL_TOP;
        }
    }
    @Test
    public void testFinalRooms() {
        generateNewRoomIfNeeded(new Point2I(7, 0));
        generateNewRoomIfNeeded(new Point2I(0, 7));
        generateNewRoomIfNeeded(new Point2I(14, 7));
        generateNewRoomIfNeeded(new Point2I(7, 14));
        Assertions.assertEquals(Room.FINAL_BOTTOM, maze[7][0]);
        Assertions.assertEquals(Room.FINAL_RIGHT, maze[0][7]);
        Assertions.assertEquals(Room.FINAL_LEFT, maze[14][7]);
        Assertions.assertEquals(Room.FINAL_TOP, maze[7][14]);
    }
    public void goThroughDoor(DoorLocation doorLocation) {
        switch (doorLocation) { //checkstyle hates this indentation :/
        case LEFT:
            currentLocation = new Point2I(currentLocation.getX() - 1, currentLocation.getY());
            break;
        case RIGHT:
            currentLocation = new Point2I(currentLocation.getX() + 1, currentLocation.getY());
            break;
        case TOP:
            currentLocation = new Point2I(currentLocation.getX(), currentLocation.getY() - 1);
            break;
        case BOTTOM:
            currentLocation = new Point2I(currentLocation.getX(), currentLocation.getY() + 1);
            break;
        default:
            break;
        }
    }
    @Test
    public void goThroughDoorLocation() {
        goThroughDoor(DoorLocation.LEFT);
        Assertions.assertEquals(new Point2I(5, 6).getX(), currentLocation.getX());
        goThroughDoor(DoorLocation.BOTTOM);
        goThroughDoor(DoorLocation.LEFT);
        Assertions.assertEquals(new Point2I(4, 7).getX(), currentLocation.getX());
        Assertions.assertEquals(new Point2I(4, 7).getY(), currentLocation.getY());
    }
}
