package com.group19.javafxgame;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.Texture;
import com.group19.javafxgame.component.PlayerInteractionComponent;
import com.group19.javafxgame.types.DifficultyLevel;
import com.group19.javafxgame.types.WeaponType;
import com.group19.javafxgame.component.MoneyComponent;
import com.group19.javafxgame.component.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.group19.javafxgame.types.CharacterType.PLAYER;
import static com.group19.javafxgame.types.LevelType.*;

//tile sprites from DawnBringer https://opengameart.org/content/dawnlike-16x16-universal-rogue-like-tileset-v181
public class CharacterFactory implements EntityFactory {
    //this is static to allow for junit tests to access it
    private static Texture texture;
    @Spawns("Player")
    public Entity spawnPlayer(SpawnData data) {
        texture = FXGL.texture("swordsman.png");
        WeaponType weapon = geto("weapon");
        DifficultyLevel difficultyLevel = geto("difficulty");
        switch (weapon) {
        case SWORD:
            texture = FXGL.texture("swordsman.png");
            break;
        case SHURIKEN:
            texture = FXGL.texture("ninja.png");
            break;
        case SHIELD:
            texture = FXGL.texture("shieldsman.png");
            break;
        default:
            texture = FXGL.texture("ninja.png");
            break;
        }

        PlayerComponent player = new PlayerComponent();
        MoneyComponent money = new MoneyComponent(difficultyLevel);
        PhysicsComponent physics = new PhysicsComponent();

        BodyDef bodyDef = new BodyDef();
        bodyDef.setGravityScale(0);
        bodyDef.setActive(true);

        physics.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .type(PLAYER)
                .at(
                        getAppWidth() / 2.0 - texture.getWidth() / 2.0,
                        getAppHeight() / 2.0 - texture.getHeight() / 2.0
                )
                .viewWithBBox(texture)
                .with(player)
                .with(money)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PlayerInteractionComponent())
                .build();
    }

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
    public Entity newExit(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(DOOR)
                .bbox(
                        new HitBox(
                                BoundingShape.box(data.<Integer>get("width"),
                                data.<Integer>get("height"))
                        )
                )
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(BACKGROUND)
                .view("background/MainMenuBackground.jpg")
                .build();
    }

    public static Texture getTexture() {
        return texture;
    }
}
