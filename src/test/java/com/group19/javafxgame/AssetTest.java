package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.audio.Sound;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


}
