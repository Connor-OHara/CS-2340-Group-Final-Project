package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.component.MonsterComponent;
import com.group19.javafxgame.rooms.Room;
import com.group19.javafxgame.rooms.RoomUtils;
import com.group19.javafxgame.types.DoorLocation;
import com.group19.javafxgame.utils.Point2I;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class RoomClearTest {

    private Room[][] maze;
    private RoomUtils roomUtils;
    private Point2I currentLocation;
    private static Entity player;

    private Room middleRoom = new Room(
            "Middle.tmx",
            new Point2I(0, 1),
            new Point2I(2, 1),
            new Point2I(1, 0),
            new Point2I(1, 2)
    );

    private void checkMonsterHP(Entity monster) {
        System.out.println(monster.getComponent(MonsterComponent.class).getHealth());
        if (monster.getComponent(MonsterComponent.class).getHealth() <= 0) {
            middleRoom.removeMonster(monster);
            if (middleRoom.getMonsters().isEmpty()) {
                System.out.println("cleared.");
                middleRoom.setCleared(true);
            }
        }
    }



    private void setRoom(Room room, Point2I coordinates) {
        maze[coordinates.getY()][coordinates.getX()] = room;
    }

    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
        System.out.println("New Setup");
    }

    @BeforeEach
    public void setupRoom() {
        maze = new Room[15][15];
        maze[7][7] = middleRoom;
        middleRoom.setLastDoor(DoorLocation.RIGHT);
        middleRoom.addMonsters();
    }

    @Test
    public void testRoomClear() {
        LinkedList<Entity> monsterList = middleRoom.getMonsters();
        System.out.println(monsterList.size());

        Entity monster1 = monsterList.get(0);
        monster1.getComponent(MonsterComponent.class).subtractHealth(25);
        checkMonsterHP(monster1);

        Entity monster2 = monsterList.get(0);
        monster2.getComponent(MonsterComponent.class).subtractHealth(25);
        checkMonsterHP(monster2);


        Entity monster3 = monsterList.get(0);
        monster3.getComponent(MonsterComponent.class).subtractHealth(25);
        checkMonsterHP(monster3);

        //Monsters have been "defeated" and are despawned.
        // Room's isCleared is now true
        Assertions.assertTrue(middleRoom.isCleared() == true);

    }



}
