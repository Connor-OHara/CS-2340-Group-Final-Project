package com.group19.javafxgame.factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.group19.javafxgame.rooms.DoorComponent;
import com.group19.javafxgame.types.LevelType;

//tile sprites from DawnBringer https://opengameart.org/content/dawnlike-16x16-universal-rogue-like-tileset-v181
public class RoomFactory implements EntityFactory {

    @Spawns("wall")
    public Entity newWall(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(LevelType.WALL)
                .bbox(
                    new HitBox(
                        BoundingShape.box(data.<Integer>get("width"),
                        data.<Integer>get("height"))
                    )
                )
                .with(new PhysicsComponent())
                .collidable()
                .build();
    }

    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        String side = data.get("side");
        return FXGL.entityBuilder(data)
                .type(LevelType.DOOR)
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
                .type(LevelType.BACKGROUND)
                .view("background/MainMenuBackground.jpg")
                .build();
    }

    @Spawns("end_game_platform")
    public Entity newEndGamePlatform(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(LevelType.END_GAME_PLATFORM)
                .bbox(
                    new HitBox(
                        BoundingShape.box(data.<Integer>get("width"),
                        data.<Integer>get("height"))
                    )
                )
                .with(new CollidableComponent(true))
                .build();
    }

}
