package com.group19.javafxgame.Rooms;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class DoorComponent extends Component {

    private final String side;

    public DoorComponent(String side) {
        this.side = side;
    }

    public void goToNextRoom() {

    }

    public String getSide() {
        return side;
    }
}
