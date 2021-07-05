package com.group19.javafxgame.utils;

import com.group19.javafxgame.Rooms.RoomComponent;

public class RoomDoorUtils {

    public RoomCoordinate doorSideToRoomCoordinate(String side,
                                                   RoomComponent room) {
        int xGrid = room.getxGrid();
        int yGrid = room.getyGrid();
        if (side.equals("left")) {
            xGrid -= 1;
        } else if (side.equals("right")) {
            xGrid += 1;
        } else if (side.equals("top")) {
            yGrid += 1;
        } else if (side.equals("bottom")) {
            yGrid -= 1;
        } else {
            throw new IllegalArgumentException(side + " Side must be one of" +
                    "left, right, top, bottom" +
                    "failing on " + room.getFilename());
        }
        return new RoomCoordinate(xGrid, yGrid);
    }

}
