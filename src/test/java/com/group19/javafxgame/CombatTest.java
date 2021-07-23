package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.group19.javafxgame.component.MonsterComponent;
import com.group19.javafxgame.component.PlayerComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


public class CombatTest {
    private static PlayerComponent player;
    private static Entity monster;

    @BeforeAll
    public static void setup() {
        GameApplication.launch(Main.class, new String[] {});
        player = new PlayerComponent();
        monster = FXGL.spawn("Monster" + 1,
        new SpawnData(200, 200));
        System.out.println("New Setup");
    }



    @Test
    public void checkPlayerCombat() {
        assertEquals(player.getHealth(), 100);
        player.addHealth(15);
        assertEquals(player.getHealth(), 100);
        player.subtractHealth(15);
        assertEquals(FXGL.geti("gameOver"), 0);
        assertEquals(player.getHealth(), 85);
        player.subtractHealth(85);
        assertEquals(player.getHealth(), 0);
        assertEquals(FXGL.geti("gameOver"), 1);
    }

    @Test
    public void checkMonsterCombat() {
        assertEquals(monster.getComponent(MonsterComponent.class).getHealth(), 25);
        monster.getComponent(MonsterComponent.class).subtractHealth(5);
        monster.getComponent(MonsterComponent.class).checkHP();
        assertEquals(monster.getComponent(MonsterComponent.class).getHealth(), 20);
        assertEquals(monster.activeProperty().toString(), "ReadOnlyBooleanProperty [value: true]");
        monster.getComponent(MonsterComponent.class).subtractHealth(20);
        assertEquals(monster.getComponent(MonsterComponent.class).getHealth(), 0);
        monster.getComponent(MonsterComponent.class).checkHP();
        assertEquals(monster.activeProperty().toString(), "ReadOnlyBooleanProperty [value: false]");
    }

}
