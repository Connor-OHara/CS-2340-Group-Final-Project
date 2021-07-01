package com.group19.javafxgame.component;


import com.group19.javafxgame.Constants;
import com.group19.javafxgame.Types.DifficultyLevel;
import javafx.geometry.Point2D;


import static com.almasb.fxgl.dsl.FXGL.geto;

public class PlayerComponent extends CharacterComponent {

    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_STRENGTH = 100;
    private static final Point2D DEFAULT_STARTING_POS = new Point2D(100, 100);

    private int money;

    public PlayerComponent(int health, int strength, Point2D location, int money) {
        super(health, strength, location);
        DifficultyLevel difficulty = geto("difficulty");
        switch (difficulty) {
        case BEGINNER:
            this.money = 10;
            break;
        case INTERMEDIATE:
            this.money = 5;
            break;
        case VETERAN:
            this.money = 1;
            break;
        default:
            break;
        }
    }

    public PlayerComponent(Point2D location) {
        this(DEFAULT_HEALTH, DEFAULT_STRENGTH, location, Constants.getDefaultMoney());
    }

    public PlayerComponent() {
        this(DEFAULT_STARTING_POS);
    }


    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public Point2D getLocation() {
        return entity.getPosition();
    }

    //can add positive or negative number
    public int addFunds(int numb) {
        money += numb;
        return money;
    }

    public int showFunds() {
        return money;
    }

    public int showGlobalFunds() {
        return money;
    }

}
