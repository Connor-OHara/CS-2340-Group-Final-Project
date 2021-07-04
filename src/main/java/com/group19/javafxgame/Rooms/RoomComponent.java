package com.group19.javafxgame.Rooms;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.level.Level;
import com.group19.javafxgame.Types.DirectionType;
import com.group19.javafxgame.Types.LevelType;

import java.util.stream.Collectors;

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

    public void addToMaze() {

    }



//
//        if (numberOfRooms == 0) {
//            numberOfRooms++;
//            maze[6][6] = this;
//            FXGL.setLevelFromMap("MiddleFromDefault.tmx");
//        } else {
//
//            if (numberOfRooms < 12) {
//                numberOfRooms++;
//                Level level = FXGL.setLevelFromMap(filename);
//
//                level.getProperties();
//
//                var doors = level.getEntities().stream()
//                        .filter(p -> p.getType().equals(LevelType.DOOR))
//                        .collect(Collectors.toList());
//
//                doors.forEach(p -> p.getComponent(DoorComponent.class));
//                if (doors.size() == 2) {
//                    System.out.println("Tunnel");
//                }
//
//                System.out.println(doors);
//                System.out.println(numberOfRooms);
//
//            }
//        }

        //public RoomComponent(DirectionType.LEFT direction)



        /* Above we get a list of all the doors.
         * Now we need to set one door equal to the last room
         * One door equal to another room
         * Other doors equal to either a room or a dead end
         * We also need to increment the room count
         * If room count is high enough, set one door to Final Room
         *
         * We also need a way to keep track of rooms
         * We could fake it being the same room by passing in all
         * necessary parameters to recreate it from the door.
         *
         * We could also try to not fake it by actually keeping track.
         * But that might be harder and more resource intensive.
         * I'm leaning towards generating rooms on demand.
         *
         */




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


}
