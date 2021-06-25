package com.group19.javafxgame.ui.menu.main;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.group19.javafxgame.Constants;
import com.group19.javafxgame.ui.menu.config.InitialConfigMenu;
import com.group19.javafxgame.ui.menu.main.MainMenu;
import org.jetbrains.annotations.NotNull;

public class MainMenuSceneFactory extends SceneFactory {
    @Override
    public FXGLMenu newMainMenu() {
        return new MainMenu();
    }
}
