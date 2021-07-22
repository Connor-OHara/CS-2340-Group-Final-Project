package com.group19.javafxgame.soundHandler;

import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.dsl.FXGL;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class CombatSounds {

    //sounds came from https://www.zapsplat.com/

    //TODO: find good explosion sound and add player to bomb
    private static Sound explosionSound = getAssetLoader().loadSound("bomb_explosion.mp3");
    private static Sound playerShurikenSound = getAssetLoader().loadSound("player_shuriken.mp3");
    private static Sound playerPainSound1 = getAssetLoader().loadSound("player_pain1.mp3");
    private static Sound playerPainSound2 = getAssetLoader().loadSound("player_pain2.mp3");
    private static Sound playerPainSound3 = getAssetLoader().loadSound("player_pain3.mp3");



    public static void playExplosion() {
        FXGL.getAudioPlayer().playSound(explosionSound);
    }

    public static void playShurikenSound() {
        FXGL.getAudioPlayer().playSound(playerShurikenSound);
    }

    public static void playPlayerPainSound() {
        List<Sound> soundList = new ArrayList<>();
        soundList.add(playerPainSound1);
        soundList.add(playerPainSound2);
        soundList.add(playerPainSound3);

        Random rand = new Random();
        Sound ouchSound = soundList.get(rand.nextInt(soundList.size()));

        FXGL.getAudioPlayer().playSound(ouchSound);
    }
}
