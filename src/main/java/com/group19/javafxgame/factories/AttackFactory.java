package com.group19.javafxgame.factories;

import com.almasb.fxgl.audio.Sound;
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
import javafx.scene.shape.Shape;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.group19.javafxgame.soundHandler.CombatSounds.*;

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
        /*
            The physics component was removed so it can't knock you through walls as it'd knock you
        outside the map. However, it might be a fun mechanic to add (not for class) later to go
        through them.
         */
        return entityBuilder()
                .type(AttackType.EXPLOSION)
                .viewWithBBox(new Circle(Constants.getDefaultBombRange(),
                        Constants.getDefaultBombRange(),
                        Constants.getDefaultBombRange(), Color.RED))
                .with(new CollidableComponent(true))
                .with(explosion)
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
    @Spawns("Sword")
    public Entity spawnSword(SpawnData data) {
        Point2D dir = data.get("dir");
        SwordComponent sword = new SwordComponent(Constants.getDefaultPlayerStrength() / 4, dir);
        Rectangle rect = new Rectangle(0, 0, Constants.getSwordLength(), 5);
        rect.setFill(Color.DIMGRAY);
        return entityBuilder()
                .type(AttackType.SWORD)
                .at(data.getX(), data.getY())
                .viewWithBBox(rect)
                .collidable()
                .with(sword)
                .build();
    }

    /**
     * Spawns a generic projectile
     * @param data data must include location(loc), direction (dir), damage (dmg),  speed (speed)
     *             can contain Shape (shape), sound , color & size [only if doesn't contain Shape]
     * @return the projectile
     * The format for the spawn should be similar to:
     * spawn("projectile", new SpawnData(pos.getX(), pos.getY()).put("dir", dir).put("dmg", damage)
     *      .put("speed", speed)));
     */
    @Spawns("projectile")
    public Entity spawnProjectile(SpawnData data) {
        ProjectileComp projectile = new ProjectileComp(data.get("dmg"));
        Point2D dir = data.get("dir");
        Point2D loc = data.get("loc");
        int speed = data.get("speed");
        Rectangle shape = data.get("shape");
        shape = new Rectangle(shape.getWidth(), shape.getHeight(), shape.getFill());
        if (data.get("sound") != "") {
            Sound sound = getAssetLoader().loadSound(data.get("sound"));
            FXGL.getAudioPlayer().playSound(sound);
        }
        return entityBuilder()
                .type(AttackType.PROJECTILE)
                .with(new OffscreenCleanComponent())
                .viewWithBBox(shape)
                .with(projectile)
                .at(loc)
                .with(new ProjectileComponent(dir, speed))
                .collidable()
                .build();
    }

    public static Texture getBombTexture() {
        return bombTexture;
    }
}
