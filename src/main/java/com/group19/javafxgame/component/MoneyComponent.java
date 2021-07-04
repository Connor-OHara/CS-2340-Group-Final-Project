package com.group19.javafxgame.component;

import com.almasb.fxgl.entity.component.Component;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.types.DifficultyLevel;

public class MoneyComponent extends Component {

    protected DifficultyLevel difficultyLevel;
    protected int money;

    public MoneyComponent(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        switch (difficultyLevel) {
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

    public MoneyComponent() {
        this(Constants.getDefaultDifficulty());
    }

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
