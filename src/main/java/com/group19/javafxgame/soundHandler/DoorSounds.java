package com.group19.javafxgame.soundHandler;

import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.dsl.FXGL;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class DoorSounds {

    private static Sound lockedDoorSound = getAssetLoader().loadSound("locked_door.mp3");
    private static Sound unlockedDoorSound = getAssetLoader().loadSound("unlocked_door.mp3");


    public static void playUnlockedDoor() {
        FXGL.getAudioPlayer().playSound(unlockedDoorSound);
    }


    public static void playLockedDoor() {
        FXGL.getAudioPlayer().playSound(lockedDoorSound);
    }
}
