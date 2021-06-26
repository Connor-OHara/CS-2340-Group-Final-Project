package com.group19.javafxgame;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.group19.javafxgame.ui.menu.main.MainMenu;

public class MainSceneFactory extends SceneFactory {
    @Override
    public FXGLMenu newMainMenu() {
        return new MainMenu();
    }
}
