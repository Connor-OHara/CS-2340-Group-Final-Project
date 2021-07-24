package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.group19.javafxgame.component.MoneyComponent;
import com.group19.javafxgame.component.PlayerComponent;
import com.group19.javafxgame.component.PlayerInteractionComponent;
import com.group19.javafxgame.player.PlayerScoreCalculator;
import com.group19.javafxgame.rooms.RoomComponent;
import com.group19.javafxgame.types.DifficultyLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerScoreCalculatorTest {

    private Entity player;

    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
        System.out.println("New Setup");
    }

    @BeforeEach
    public void setupEach() {
        Entity player = new Entity();
        PlayerComponent playerComponent = new PlayerComponent();
        RoomComponent roomComponent = new RoomComponent();
        MoneyComponent moneyComponent = new MoneyComponent();

        player.addComponent(playerComponent);
        player.addComponent(roomComponent);
        player.addComponent(moneyComponent);
        player.addComponent(new PhysicsComponent());
        player.addComponent(new PlayerInteractionComponent(FXGL.getPhysicsWorld()));

        this.player = player;
    }

    @Test
    public void testMoreMonsterKills() {
        DifficultyLevel difficultyLevel = DifficultyLevel.BEGINNER;
        PlayerScoreCalculator scoreCalculator = new PlayerScoreCalculator(player, difficultyLevel);

        int scoreBefore = scoreCalculator.getScore();
        player.getComponent(PlayerComponent.class).incrementMonsterKillCount();
        int scoreAfter = scoreCalculator.getScore();
        Assertions.assertTrue(scoreAfter > scoreBefore);
    }

    @Test
    public void testMoreMoney() {
        DifficultyLevel difficultyLevel = DifficultyLevel.BEGINNER;
        PlayerScoreCalculator scoreCalculator = new PlayerScoreCalculator(player, difficultyLevel);

        int scoreBefore = scoreCalculator.getScore();
        player.getComponent(MoneyComponent.class).addFunds(10);
        int scoreAfter = scoreCalculator.getScore();
        Assertions.assertTrue(scoreAfter > scoreBefore);
    }

    @Test
    public void testHarderDifficulty() {
        PlayerScoreCalculator beginnerScoreCalc = new PlayerScoreCalculator(
                player,
                DifficultyLevel.BEGINNER
        );
        int scoreBeginner = beginnerScoreCalc.getScore();

        PlayerScoreCalculator interScoreCalc = new PlayerScoreCalculator(
                player,
                DifficultyLevel.INTERMEDIATE
        );
        int scoreIntermediate = interScoreCalc.getScore();

        PlayerScoreCalculator veteranScoreCalc = new PlayerScoreCalculator(
                player,
                DifficultyLevel.VETERAN
        );
        int scoreVeteran = veteranScoreCalc.getScore();

        Assertions.assertTrue(scoreBeginner < scoreIntermediate);
        Assertions.assertTrue(scoreIntermediate < scoreVeteran);
    }

}
