package com.group19.javafxgame;


import com.group19.javafxgame.component.MonsterComponent;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MonsterComponentTest {

    // Health: 10
    // Strength: 20
    // Location: (53, 14)
    private MonsterComponent monsterComponent = new MonsterComponent(10, 20, new Point2D(53, 14));

    @Test
    public void checkAttributes() {
        assertEquals(monsterComponent.getHealth(), 10);
        assertEquals(monsterComponent.getStrength(), 20);
        assertEquals(monsterComponent.getLocation(), new Point2D(53, 14));
    }

    @Test
    public void checkMovement() {
        monsterComponent.translateUp();
        assertEquals(monsterComponent.getLocation().getY(), 12.5, 0.001);
        monsterComponent.translateDown();
        assertEquals(monsterComponent.getLocation().getY(), 14, 0.001);
        monsterComponent.translateLeft();
        assertEquals(monsterComponent.getLocation().getX(), 51.5, 0.001);
        monsterComponent.translateRight();
        assertEquals(monsterComponent.getLocation().getX(), 53, 0.001);
    }

    @Test
    public void testSubstractHP() {
        assertEquals(10, monsterComponent.getHealth());
        monsterComponent.subtractHealth(5);
        assertEquals(5, monsterComponent.getHealth());

    }



}
