package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.dsl.FXGL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static org.junit.jupiter.api.Assertions.*;

public class AssetTest {

    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
        System.out.println("New Setup");
    }

    public void loadDoorSounds() {
        Sound lockedDoorSound = getAssetLoader().loadSound("locked_door.mp3");
        Sound unlockedDoorSound = getAssetLoader().loadSound("unlocked_door.mp3");
    }

    public void loadMusic() {
        getAssetLoader().loadMusic("background_cave_wind.mp3");
    }

    @Test
    public void doorSoundPathTest() {
        assertDoesNotThrow(() -> loadDoorSounds());

        Path lockedPath = Paths.get("src/main/resources/assets/sounds/locked_door.mp3");
        assertTrue(Files.exists(lockedPath));

        Path unlockedPath = Paths.get("src/main/resources/assets/sounds/unlocked_door.mp3");
        assertTrue(Files.exists(unlockedPath));
    }

    @Test
    public void musicAssetsTest() {
        assertDoesNotThrow(() -> loadMusic());

        Path backgroundMusic =
                Paths.get("src/main/resources/assets/music/background_cave_wind.mp3");
        assertTrue(Files.exists(backgroundMusic));

    }

    @Test
    public void playPlayerPainSound() {
        Sound playerPainSound1 = getAssetLoader().loadSound("player_pain1.mp3");
        Sound playerPainSound2 = getAssetLoader().loadSound("player_pain2.mp3");
        Sound playerPainSound3 = getAssetLoader().loadSound("player_pain3.mp3");


        List<Sound> soundList = new ArrayList<>();
        soundList.add(playerPainSound1);
        soundList.add(playerPainSound2);
        soundList.add(playerPainSound3);

        Random rand = new Random();
        Sound ouchSound = soundList.get(rand.nextInt(soundList.size()));

        FXGL.getAudioPlayer().playSound(ouchSound);
    }


}
