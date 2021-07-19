package com.group19.javafxgame.factories;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.Texture;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.component.*;
import com.group19.javafxgame.types.AttackType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;

//tile sprites from DawnBringer https://opengameart.org/content/dawnlike-16x16-universal-rogue-like-tileset-v181
public class AttackFactory implements EntityFactory {
    //this is static to allow for junit tests to access it
    private static Texture bombTexture;

    @Spawns("Bomb")
    public Entity spawnBomb(SpawnData data) {
        bombTexture = FXGL.texture("bomb.png");
        BombComponent bomb = new BombComponent();
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        return FXGL.entityBuilder(data)
                .type(AttackType.BOMB)
                .at(
                        getAppWidth() / 2.0,
                        getAppHeight() / 2.0
                )
                .view(bombTexture)
                .with(bomb)
                .with(physics)
                .build();
    }
    @Spawns("Explosion")
    public Entity spawnExplosion(SpawnData data) {
        ExplosionComponent explosion = new
                ExplosionComponent(Constants.getDefaultPlayerStrength() / 4);
        PhysicsComponent physics = new PhysicsComponent();
        return entityBuilder()
                .type(AttackType.EXPLOSION)
                .viewWithBBox(new Circle(Constants.getDefaultBombRange(), Color.RED))
                .with(new CollidableComponent(true))
                .with(explosion)
                .with(physics)
                .build();
    }

    @Spawns("Shuriken")
    public Entity spawnShuriken(SpawnData data) {
        Point2D dir = data.get("dir");
        Point2D loc = data.get("loc");
        return entityBuilder()
                .type(AttackType.SHURIKEN)
                .viewWithBBox(new Rectangle(5, 5, Color.DARKCYAN))
                .at(loc)
                .with(new ProjectileComponent(dir, 300))
                .with(new OffscreenCleanComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    public static Texture getBombTexture() {
        return bombTexture;
    }
}
