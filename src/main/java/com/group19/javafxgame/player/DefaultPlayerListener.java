package com.group19.javafxgame.player;

import com.almasb.fxgl.dsl.FXGL;

public class DefaultPlayerListener implements IPlayerListener {
    @Override
    public void healthDidDrop(int from, int to) {
        if (to <= 0) {
            FXGL.set("gameOver", 1);
        }
    }
}
