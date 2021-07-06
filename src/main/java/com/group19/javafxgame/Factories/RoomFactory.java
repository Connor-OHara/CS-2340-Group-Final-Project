package com.group19.javafxgame.Factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.group19.javafxgame.Rooms.DoorComponent;
import com.group19.javafxgame.Rooms.RoomComponent;

import static com.group19.javafxgame.Types.LevelType.*;

//tile sprites from DawnBringer https://opengameart.org/content/dawnlike-16x16-universal-rogue-like-tileset-v181
public class RoomFactory implements EntityFactory {

//    @Spawns("Room")
//    public Entity newRoom(SpawnData data) {
//        return FXGL.entityBuilder(data)
//                .type(ROOM)
//                .with(new RoomComponent())
//                .build();
//    }

    @Spawns("wall")
    public Entity newWall(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(WALL)
                .bbox(
                        new HitBox(
                                BoundingShape.box(data.<Integer>get("width"),
                                        data.<Integer>get("height"))
                        )
                )
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        String side = data.get("side");
        return FXGL.entityBuilder(data)
                .type(DOOR)
                .bbox(
                        new HitBox(
                                BoundingShape.box(data.<Integer>get("width"),
                                        data.<Integer>get("height"))
                        )
                )
                .with(new CollidableComponent(true))
                .with(new DoorComponent(side))
                .build();
    }

    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(BACKGROUND)
                .view("background/MainMenuBackground.jpg")
                .build();
    }

}
