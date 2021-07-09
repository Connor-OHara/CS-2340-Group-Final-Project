package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;


public class GameScreenTest {



    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
        System.out.println("New Setup");
    }



    @Test
    public void checkGameOver() {
        //sets game to end world screen, loads screen and id to active vars
        FXGL.set("gameOver", 1);
        //gets gameWorld id
        String handle = getGameScene().getGameWorld().toString();
        System.out.println(handle);
        //closes JavaFX window
        FXGL.set("closeGame", 1);


        if (handle == getGameScene().getGameWorld().toString()) {
            throw new RuntimeException("GAME NOT CLOSED, LISTENER BROKEN");
        }

    }

}
