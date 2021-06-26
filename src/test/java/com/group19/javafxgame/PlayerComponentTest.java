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
    public void checkHealthEquality() {
        assertEquals(playerComponent.getHealth(), 10);
    }

    @Test
    public void checkStrengthEquality() {
        assertEquals(playerComponent.getStrength(), 20);
    }

    @Test
    public void checkLocationEquality() {
        assertEquals(playerComponent.getLocation(), new Point2D(53, 14));
    }



}
