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

    private PlayerComponent playerComponent =
            new PlayerComponent(10,
                        20,
                                new Point2D(100, 100),
                                10);

    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
        System.out.println("New Setup");
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
        System.out.println(playerComponent.showFunds());
        Assertions.assertEquals(14, funds);

        Assertions.assertEquals(6, playerComponent.addFunds(-8));

        System.out.println(playerComponent.showFunds());
    }

    @Test
    public void checkShowFunds() {
        System.out.println("Starting checkShowFunds");
        //TODO: Check. This starts at 10 as shown below. And then funds are added.
        //IF relying on previous test, you would expect it to be six. It starts here with 10.
        //Is there permanence to this?
        System.out.println(playerComponent.showFunds());
        playerComponent.addFunds(4);

        Assertions.assertEquals(14, playerComponent.showGlobalFunds());
    }
}
