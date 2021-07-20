package com.group19.javafxgame;

import com.group19.javafxgame.types.DifficultyLevel;
import com.group19.javafxgame.types.WeaponType;
import javafx.geometry.Point2D;

public class Constants {

    public static String getGameName() {
        return "Dungeon Crawler";
    }

    public static String getGameVersion() {
        return "Development 0.1.0 Alpha";
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

    public static Point2D getDefaultPosition() {
        return new javafx.geometry.Point2D(100, 100);
    }

    public static Point2D getDefaultMonsterPosition() {
        return new javafx.geometry.Point2D(75, 25);
    }

    public static WeaponType getDefaultWeapon() {
        return WeaponType.SWORD;
    }

    public static int getDefaultMoney() {
        return 10;
    }

    public static int getDefaultPlayerHealth() {
        return 100;
    }

    public static int getDefaultPlayerStrength() {
        return 100;
    }

    public static double getDefaultVelocity() {
        return 150;
    }

    public static int getDefaultMonsterHealth() {
        return 25;
    }

    public static int getDefaultMonsterStrength() {
        return 25;
    }

    public static double getDefaultMonsterVelocity() {
        return 0;
    }

    public static int getDefaultBombRange() {
        return 48;
    }


}
