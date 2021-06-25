package com.group19.javafxgame;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.group19.javafxgame.component.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.group19.javafxgame.Types.CharacterType.*;

public class CharacterFactory implements EntityFactory {

    @Spawns("Player")
    public Entity spawnPlayer(SpawnData data) {
        var texture = FXGL.texture("stick_figure.png");
        return FXGL.entityBuilder(data)
                .type(PLAYER)
                .at(getAppWidth() / 2 - texture.getWidth() / 2, getAppHeight() / 2 - texture.getHeight() / 2)
                .viewWithBBox(texture)
                .with(new PlayerComponent())
                .build();
    }




}
