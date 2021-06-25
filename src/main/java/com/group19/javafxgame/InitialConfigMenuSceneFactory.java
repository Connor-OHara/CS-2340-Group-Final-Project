package com.group19.javafxgame;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.group19.javafxgame.ui.InitialConfigMenu;

public class InitialConfigMenuSceneFactory extends SceneFactory {
    @Override
    public FXGLMenu newMainMenu() {
        return new InitialConfigMenu(Constants.DEFAULT_DIFFICULTY, Constants.DEFAULT_WEAPON);
    }

}
