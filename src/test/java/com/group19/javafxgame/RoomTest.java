package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.group19.javafxgame.rooms.Room;
import com.group19.javafxgame.types.DoorLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RoomTest {

    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
    }

    @Test
    public void testListDifferenceAndEquality() {
        Assertions.assertEquals(Room.roomsWithDoor(DoorLocation.RIGHT), Room.getRoomsRightDoor());
        Assertions.assertNotEquals(Room.getRoomsBottomDoor(), Room.getRoomsTopDoor());
        var doors = Room.getRoomsLeftDoor();
        System.out.println(doors);
        var differentDoors = Room.getRoomsRightDoor();
        doors.removeAll(differentDoors);
        var door = (Room) doors.toArray()[0];
        Assertions.assertEquals("TLB.tmx", door.getFilename());
    }

    @Test
    public void testAddMonsters() {
        Room room = Room.START.clone();
        room.addMonsters();
        Assertions.assertEquals(3, room.getMonsters().size());
        var monsters = room.getMonsters();
        room.removeMonsters();
        Assertions.assertEquals(3, room.getMonsters().size());
    }

}
