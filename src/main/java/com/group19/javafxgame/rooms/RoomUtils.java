package com.group19.javafxgame.rooms;

import com.group19.javafxgame.types.DoorGeneration;
import com.group19.javafxgame.types.DoorLocation;
import com.group19.javafxgame.utils.Point2I;

import java.util.List;

public class RoomUtils {

    private final Room[][] maze;
    public RoomUtils(Room[][] maze) {
        this.maze = maze;
    }

    public Room getRoom(Point2I coordinates) {
        return maze[coordinates.getY()][coordinates.getX()];
    }

    public boolean hasRoom(Point2I coordinates) {
        return getRoom(coordinates) != null;
    }

    public Room getLeftRoom(Point2I coordinates) {
        return isLeftEdge(coordinates) ? null : getRoom(coordinates.getLeft());
    }

    public boolean hasLeftRoom(Point2I coordinates) {
        return getLeftRoom(coordinates) != null;
    }

    public boolean isLeftEdge(Point2I coordinates) {
        return coordinates.getX() <= 0;
    }

    public DoorGeneration getLeftDoorGeneration(Point2I coordinates) {
        if (!hasLeftRoom(coordinates)) {
            return DoorGeneration.OPTIONAL;
        } else if (getLeftRoom(coordinates).hasRightSpawn()) {
            return DoorGeneration.REQUIRED;
        } else {
            return DoorGeneration.FORBIDDEN;
        }
    }

    public Room getRightRoom(Point2I coordinates) {
        return isRightEdge(coordinates) ? null : getRoom(coordinates.getRight());
    }

    public boolean hasRightRoom(Point2I coordinates) {
        return getRightRoom(coordinates) != null;
    }

    public boolean isRightEdge(Point2I coordinates) {
        return coordinates.getX() >= maze[0].length - 1;
    }

    public DoorGeneration getRightDoorGeneration(Point2I coordinates) {
        if (!hasRightRoom(coordinates)) {
            return DoorGeneration.OPTIONAL;
        } else if (getRightRoom(coordinates).hasLeftSpawn()) {
            return DoorGeneration.REQUIRED;
        } else {
            return DoorGeneration.FORBIDDEN;
        }
    }

    public Room getTopRoom(Point2I coordinates) {
        return isTopEdge(coordinates) ? null : getRoom(coordinates.getUp());
    }

    public boolean hasTopRoom(Point2I coordinates) {
        return getTopRoom(coordinates) != null;
    }

    public boolean isTopEdge(Point2I coordinates) {
        return coordinates.getY() <= 0;
    }

    public DoorGeneration getTopDoorGeneration(Point2I coordinates) {
        if (!hasTopRoom(coordinates)) {
            return DoorGeneration.OPTIONAL;
        } else if (getTopRoom(coordinates).hasBottomSpawn()) {
            return DoorGeneration.REQUIRED;
        } else {
            return DoorGeneration.FORBIDDEN;
        }
    }

    public Room getBottomRoom(Point2I coordinates) {
        return isBottomEdge(coordinates) ? null : getRoom(coordinates.getDown());
    }

    public boolean hasBottomRoom(Point2I coordinates) {
        return getBottomRoom(coordinates) != null;
    }

    public boolean isBottomEdge(Point2I coordinates) {
        return coordinates.getY() >= maze.length - 1;
    }

    public DoorGeneration getBottomDoorGeneration(Point2I coordinates) {
        if (!hasBottomRoom(coordinates)) {
            return DoorGeneration.OPTIONAL;
        } else if (getBottomRoom(coordinates).hasTopSpawn()) {
            return DoorGeneration.REQUIRED;
        } else {
            return DoorGeneration.FORBIDDEN;
        }
    }

    public void populateDoorGeneration(List<DoorLocation> requiredDoors,
                                       List<DoorLocation> forbiddenDoors,
                                       Point2I coordinates) {
        DoorGeneration leftGeneration = getLeftDoorGeneration(coordinates);
        DoorGeneration rightGeneration = getRightDoorGeneration(coordinates);
        DoorGeneration topGeneration = getTopDoorGeneration(coordinates);
        DoorGeneration bottomGeneration = getBottomDoorGeneration(coordinates);

        if (leftGeneration == DoorGeneration.REQUIRED) {
            requiredDoors.add(DoorLocation.LEFT);
        } else if (leftGeneration == DoorGeneration.FORBIDDEN) {
            forbiddenDoors.add(DoorLocation.LEFT);
        }

        if (rightGeneration == DoorGeneration.REQUIRED) {
            requiredDoors.add(DoorLocation.RIGHT);
        } else if (rightGeneration == DoorGeneration.FORBIDDEN) {
            forbiddenDoors.add(DoorLocation.RIGHT);
        }

        if (topGeneration == DoorGeneration.REQUIRED) {
            requiredDoors.add(DoorLocation.TOP);
        } else if (topGeneration == DoorGeneration.FORBIDDEN) {
            forbiddenDoors.add(DoorLocation.TOP);
        }

        if (bottomGeneration == DoorGeneration.REQUIRED) {
            requiredDoors.add(DoorLocation.BOTTOM);
        } else if (bottomGeneration == DoorGeneration.FORBIDDEN) {
            forbiddenDoors.add(DoorLocation.BOTTOM);
        }
    }

}
