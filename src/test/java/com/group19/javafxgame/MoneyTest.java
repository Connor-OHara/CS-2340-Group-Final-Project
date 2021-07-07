package com.group19.javafxgame;

import com.group19.javafxgame.types.DifficultyLevel;
import com.group19.javafxgame.types.WeaponType;
import com.group19.javafxgame.component.MoneyComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import com.almasb.fxgl.app.GameApplication;

public class MoneyTest {

    private MoneyComponent moneyComponent =
            new MoneyComponent(Constants.getDefaultDifficulty());

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
        int funds = moneyComponent.addFunds(4);
        System.out.println(moneyComponent.showFunds());
        Assertions.assertEquals(14, funds);

        Assertions.assertEquals(6, moneyComponent.addFunds(-8));

        System.out.println(moneyComponent.showFunds());
    }

    @Test
    public void checkShowFunds() {

        System.out.println("Starting checkShowFunds");
        System.out.println(moneyComponent.showFunds());
        moneyComponent.addFunds(4);
        Assertions.assertEquals(14, moneyComponent.showGlobalFunds());
    }
}
