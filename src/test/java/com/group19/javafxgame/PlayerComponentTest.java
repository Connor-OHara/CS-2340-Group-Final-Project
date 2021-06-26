package com.group19.javafxgame;


import com.almasb.fxgl.app.GameApplication;
import com.group19.javafxgame.component.PlayerComponent;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class PlayerComponentTest {

    // Health: 10
    // Strength: 20
    // Location: (53, 14)
    PlayerComponent playerComponent = new PlayerComponent(10, 20, new Point2D(53, 14));

    @BeforeAll
    public static void setup() {}

    @Test
    public void checkAttributes() {
        assertEquals(playerComponent.getHealth(), 10);
        assertEquals(playerComponent.getStrength(), 20);
        assertEquals(playerComponent.getLocation(), new Point2D(53, 14));
    }

    @Test
    public void checkMovement() {
        playerComponent.translateUp();
        assertEquals(playerComponent.getLocation().getY(), 4, 0.001);
        playerComponent.translateDown();
        assertEquals(playerComponent.getLocation().getY(), 14, 0.001);
        playerComponent.translateLeft();
        assertEquals(playerComponent.getLocation().getX(), 43, 0.001);
        playerComponent.translateRight();
        assertEquals(playerComponent.getLocation().getX(), 53, 0.001);
    }



}
