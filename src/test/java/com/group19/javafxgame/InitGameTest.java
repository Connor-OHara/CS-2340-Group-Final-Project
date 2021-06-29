package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class InitGameTest {
    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
    }

    //This tests if the correct image is being used for the player.
    @Test
    public void testSprite() {
        /**
         * To run this test, start the game and select the weapon type associated with the image
         * in a texture is created from in like 27.
         * Sword: swordsman.png
         * Shuriken: ninja.png
         * Shield: shieldsman.png
         */
        Texture texture = FXGL.texture("ninja.png");
        assertEquals(texture.getImage(), CharacterFactory.getTexture().getImage());
    }

    @Test
    public void testMoney() {
        /**
         * To run this test, start the game and select the difficulty
         * associated with the money in line 40.
         * Beginner: 10
         * Intermediate: 5
         * Veteran: 1
         */
        int money = 10;
        assertEquals(money, FXGL.geti("money"));
    }
}
