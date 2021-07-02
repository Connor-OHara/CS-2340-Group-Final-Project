package com.group19.javafxgame.Rooms;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.level.Level;
import com.group19.javafxgame.Types.LevelType;

import java.util.stream.Collectors;

public class RoomComponent extends Component {

    protected DoorComponent[] doors;
    protected String filename;

    public RoomComponent(String filename) {
        this.filename = filename;
        Level level = FXGL.setLevelFromMap(filename);

        var doors =level.getEntities().stream()
                .filter(p -> p.getType().equals(LevelType.DOOR))
                .collect(Collectors.toList());

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

        if (doors.size() == 2) {
            System.out.println("Tunnel");
        }

        System.out.println(doors);
    }

    public RoomComponent() {
        this("default2.tmx");
    }

}
