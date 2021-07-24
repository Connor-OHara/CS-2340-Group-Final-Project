package com.group19.javafxgame.player;

import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.component.MoneyComponent;
import com.group19.javafxgame.component.PlayerComponent;
import com.group19.javafxgame.rooms.RoomComponent;
import com.group19.javafxgame.types.DifficultyLevel;

public class PlayerScoreCalculator {

    private Entity player;
    private DifficultyLevel difficultyLevel;
    public PlayerScoreCalculator(Entity player, DifficultyLevel difficultyLevel) {
        this.player = player;
        this.difficultyLevel = difficultyLevel;
    }

    public int getGold() {
        return player.getComponent(MoneyComponent.class).getFunds();
    }

    public int getMonstersKilled() {
        return player.getComponent(PlayerComponent.class).getMonsterKillCount();
    }

    public int getRoomsVisited() {
        return player.getComponent(RoomComponent.class).getRoomGenerationCount();
    }

    public int getHealth() {
        return player.getComponent(PlayerComponent.class).getHealth();
    }

    public int getDifficultyValue() {
        switch (difficultyLevel) {
            case INTERMEDIATE:
                return 1;
            case VETERAN:
                return 2;
            default:
                return 0;
        }
    }

    public int getScore() {
        return 3 * getGold()
                + getMonstersKilled()
                + 2 * Math.max(0, getHealth())
                + 4 * getRoomsVisited()
                + 15 * getDifficultyValue();
    }

}
