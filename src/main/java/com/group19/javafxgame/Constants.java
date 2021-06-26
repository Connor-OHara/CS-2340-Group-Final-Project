package com.group19.javafxgame;

import com.group19.javafxgame.Types.DifficultyLevel;
import com.group19.javafxgame.Types.WeaponType;

public class Constants {

    public static String getGameName() {
        return "Dungeon Crawler";
    }

    public static String getGameVersion() {
        return "Development 0.0.1";
    }

    public static int getScreenWidth() {
        return 1280;
    }

    public static int getScreenHeight() {
        return 720;
    }

    public static DifficultyLevel getDefaultDifficulty() {
        return DifficultyLevel.BEGINNER;
    }

    public static WeaponType getDefaultWeapon() {
        return WeaponType.SWORD;
    }

    public static int getDefaultMoney() {
        return 10;
    }

}
