package com.group19.javafxgame.soundHandler;

import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.dsl.FXGL;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class CombatSounds {

    //TODO: find good explosion sound and add player to bomb
    private static Sound explosionSound = getAssetLoader().loadSound("explosion.mp3");

    public static void playExplosion() {
        FXGL.getAudioPlayer().playSound(explosionSound);
    }

}
