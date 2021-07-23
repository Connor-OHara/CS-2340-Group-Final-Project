package com.group19.javafxgame.factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.ProgressBar;
import com.group19.javafxgame.component.*;
import com.group19.javafxgame.rooms.RoomComponent;
import com.group19.javafxgame.types.CharacterType;
import com.group19.javafxgame.types.DifficultyLevel;
import com.group19.javafxgame.types.WeaponType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.*;

//tile sprites from DawnBringer https://opengameart.org/content/dawnlike-16x16-universal-rogue-like-tileset-v181
public class CharacterFactory implements EntityFactory {
    //this is static to allow for junit tests to access it
    private static Texture texture;
    private static Texture monsterTexture;
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
                .type(CharacterType.PLAYER)
                .at(
                        getAppWidth() / 2.0 - texture.getWidth() / 2.0,
                        getAppHeight() / 2.0 - texture.getHeight() / 2.0
                )
                .with(new IrremovableComponent())
                .viewWithBBox(texture)
                .with(player)
                .with(money)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PlayerInteractionComponent(getPhysicsWorld()))
                .with(new RoomComponent())
                .build();
    }

    @Spawns("Monster1")
    public Entity spawnMonster1(SpawnData data) {
        monsterTexture = FXGL.texture("enemy01.png");

        Point2D startLocation = new Point2D(data.getX(), data.getY());
        MonsterComponent monster = new MonsterComponent(startLocation);
        PhysicsComponent physics = new PhysicsComponent();

        var monsterHP = new ProgressBar(false);
        monsterHP.setFill(Color.LIGHTGREEN);
        monsterHP.setMaxValue(25);
        monsterHP.setWidth(45);
        monsterHP.setTranslateY(0);
        monsterHP.setTranslateX(-8);
        monsterHP.currentValueProperty().bind(monster.getHp().valueProperty());

        BodyDef bodyDef = new BodyDef();
        bodyDef.setGravityScale(0);
        bodyDef.setActive(true);

        physics.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .type(CharacterType.MONSTER)
                .with(new IrremovableComponent())
                .viewWithBBox(monsterTexture)
                .view(monsterHP)
                .with(monster)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MonsterInteractionComponent(getPhysicsWorld()))
                .build();
    }



    @Spawns("Monster2")
    public Entity spawnMonster2(SpawnData data) {
        monsterTexture = FXGL.texture("enemy02.png");

        Point2D startLocation = new Point2D(data.getX(), data.getY());
        MonsterComponent monster = new MonsterComponent(startLocation);
        PhysicsComponent physics = new PhysicsComponent();

        var monsterHP = new ProgressBar(false);
        monsterHP.setFill(Color.LIGHTGREEN);
        monsterHP.setMaxValue(25);
        monsterHP.setWidth(45);
        monsterHP.setTranslateY(0);
        monsterHP.setTranslateX(-8);
        monsterHP.currentValueProperty().bind(monster.getHp().valueProperty());

        BodyDef bodyDef = new BodyDef();
        bodyDef.setGravityScale(0);
        bodyDef.setActive(true);

        physics.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .type(CharacterType.MONSTER)
                .with(new IrremovableComponent())
                .viewWithBBox(monsterTexture)
                .view(monsterHP)
                .with(monster)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MonsterInteractionComponent(getPhysicsWorld()))
                .build();
    }


    @Spawns("Monster3")
    public Entity spawnMonster3(SpawnData data) {
        //TODO: add more types of monster, and make it random?
        monsterTexture = FXGL.texture("enemy03.png");

        Point2D startLocation = new Point2D(data.getX(), data.getY());
        MonsterComponent monster = new MonsterComponent(startLocation);
        PhysicsComponent physics = new PhysicsComponent();

        var monsterHP = new ProgressBar(false);
        monsterHP.setFill(Color.LIGHTGREEN);
        monsterHP.setMaxValue(25);
        monsterHP.setWidth(45);
        monsterHP.setTranslateY(0);
        monsterHP.setTranslateX(-8);
        monsterHP.currentValueProperty().bind(monster.getHp().valueProperty());

        BodyDef bodyDef = new BodyDef();
        bodyDef.setGravityScale(0);
        bodyDef.setActive(true);

        physics.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .type(CharacterType.MONSTER)
                .with(new IrremovableComponent())
                .viewWithBBox(monsterTexture)
                .view(monsterHP)
                .with(monster)
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new MonsterInteractionComponent(getPhysicsWorld()))
                .build();
    }
    public static Texture getTexture() {
        return texture;
    }

    public static Texture getMonsterTexture() {
        return monsterTexture;
    }

}
