package com.group19.javafxgame.ui.menu.main;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

public class MainMenuSceneFactory extends SceneFactory {
    @Override
    public FXGLMenu newMainMenu() {
        return new MainMenu();
    }
}
