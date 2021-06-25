package com.group19.javafxgame;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.group19.javafxgame.ui.GameMainMenu;
import com.group19.javafxgame.ui.InitialConfigMenu;

public class MainMenuSceneFactory extends SceneFactory {
    @Override
    public FXGLMenu newMainMenu() {
        return new GameMainMenu();
    }

}
