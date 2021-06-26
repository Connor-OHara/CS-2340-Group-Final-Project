package com.group19.javafxgame;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.group19.javafxgame.Types.DifficultyLevel;
import com.group19.javafxgame.Types.WeaponType;
import com.group19.javafxgame.component.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.geto;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static com.group19.javafxgame.Types.CharacterType.*;
import static com.group19.javafxgame.Types.LevelType.*;
import static com.group19.javafxgame.Types.WeaponType.*;

//tile sprites from DawnBringer https://opengameart.org/content/dawnlike-16x16-universal-rogue-like-tileset-v181
public class CharacterFactory implements EntityFactory {

    @Spawns("Player")
    public Entity spawnPlayer(SpawnData data) {
        /**This switch is supposed to somehow access which weapon was chosen (geti("weapon")?) and change the player sprite
         * based off that. -Matthew
          *///TODO: @Matthew remove?
        var texture = FXGL.texture("swordsman.png");
        WeaponType weapon = geto("weapon");
        DifficultyLevel difficulty = geto("difficulty");
        System.out.println(weapon);
        System.out.println(difficulty);

        switch(difficulty){
            case BEGINNER:
                FXGL.set("money", 10);
                FXGL.set("lives", 5);
                break;
            case INTERMEDIATE:
                FXGL.set("money", 5);
                FXGL.set("lives", 3);
                break;
            case VETERAN:
                FXGL.set("money", 1);
                FXGL.set("lives", 1);
                break;
        }


        switch(weapon) {
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

        return FXGL.entityBuilder(data)
                .type(PLAYER)
                .at(getAppWidth() / 2 - texture.getWidth() / 2, getAppHeight() / 2 - texture.getHeight() / 2)
                .viewWithBBox(texture)
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(WALL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("door")
    public Entity newExit(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(DOOR)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
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



}
