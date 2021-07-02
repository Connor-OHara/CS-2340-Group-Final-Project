package com.group19.javafxgame.Rooms;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class DoorComponent extends Component {

    public DoorComponent() {
        this.nextRoom = new RoomComponent();
    }

    private RoomComponent nextRoom;

    public void goToNextRoom() {
        FXGL.setLevelFromMap(nextRoom.filename);
    }

    public void setNextRoom(RoomComponent nextRoom) {
        this.nextRoom = nextRoom;
    }

    public RoomComponent getNextRoom() {
        return nextRoom;
    }
}
