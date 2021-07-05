package com.group19.javafxgame.Rooms;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class RoomComponent extends Component {

    protected static RoomComponent[][] maze = new RoomComponent[13][13];

    protected DoorComponent[] doors;
    protected String filename;
    protected static int numberOfRooms;
    protected int xGrid;
    protected int yGrid;

    public RoomComponent(String filename, int xGrid, int yGrid) {
        this.filename = filename;

        this.xGrid = xGrid;
        this.yGrid = yGrid;

        if (maze[xGrid][yGrid] != null) {
            throw new IllegalArgumentException("cannot initialize room" +
                    "where one exists");
        }
        maze[xGrid][yGrid] = this;
        numberOfRooms++;
    }

    public RoomComponent(String filename) {
        this.filename = filename;
        numberOfRooms++;
    }

    public void setLevelFromRoom() {
        FXGL.setLevelFromMap(this.filename);
    }

    public RoomComponent() {
        this("MiddleFromDefault.tmx");
    }

    public static void decrementRoom() {
        numberOfRooms--;
    }

    public int getxGrid() {
        return xGrid;
    }

    public int getyGrid() {
        return yGrid;
    }

    public static RoomComponent[][] getMaze() {
        return maze;
    }

    public static void setMaze(RoomComponent[][] maze) {
        RoomComponent.maze = maze;
    }

    public String getFilename() {
        return filename;
    }
}
