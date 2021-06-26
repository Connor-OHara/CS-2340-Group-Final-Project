package com.group19.javafxgame;

import com.group19.javafxgame.Types.DifficultyLevel;
import com.group19.javafxgame.Types.WeaponType;
import com.group19.javafxgame.component.PlayerComponent;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import com.almasb.fxgl.app.GameApplication;

public class MoneyTest {

    private PlayerComponent playerComponent = new PlayerComponent(10, 20, new Point2D(100, 100));

    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
    }



    @Test
    public void getDifficultyAndGear() {
        DifficultyLevel diff = Constants.getDefaultDifficulty();
        WeaponType weapon = Constants.getDefaultWeapon();

        assertEquals(diff, DifficultyLevel.BEGINNER);
        assertEquals(weapon, WeaponType.SWORD);
    }

    @Test
    public void checkAddSubtractFunds() {
        int funds = playerComponent.addFunds(4);
        Assertions.assertEquals(14, funds);

        int subfunds = playerComponent.addFunds(-8);
        Assertions.assertEquals(6, subfunds);
    }

    @Test
    public void checkShowFunds() {
        playerComponent.addFunds(4);
        Assertions.assertEquals(playerComponent.showGlobalFunds(), 10);
    }
}
