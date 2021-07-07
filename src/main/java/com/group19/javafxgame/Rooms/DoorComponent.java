package com.group19.javafxgame.Rooms;


import com.almasb.fxgl.entity.component.Component;
import com.group19.javafxgame.types.DoorLocation;

public class DoorComponent extends Component {

    private final String side;
    private DoorLocation doorLocation;

    public DoorComponent(String side) {
        this.side = side;
        if (side.equalsIgnoreCase("left")) {
            doorLocation = DoorLocation.LEFT;
        } else if (side.equalsIgnoreCase("right")) {
            doorLocation = DoorLocation.RIGHT;
        } else if (side.equalsIgnoreCase("top")) {
            doorLocation = DoorLocation.TOP;
        } else if (side.equalsIgnoreCase("bottom")) {
            doorLocation = DoorLocation.BOTTOM;
        } else {
            throw new IllegalArgumentException("Unrecognized door side: " + side);
        }
    }

    public String getSide() {
        return side;
    }

    public DoorLocation getDoorLocation() {
        return doorLocation;
    }
}
