package com.group19.javafxgame.ui.menu.main;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FXGLButton;
import com.almasb.fxgl.ui.FontType;
import com.group19.javafxgame.Constants;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.NotNull;

public class MainMenu extends FXGLMenu {

    public MainMenu() {
        super(MenuType.MAIN_MENU);

        // Title Positioning
        StackPane titleContainer = new StackPane();
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPrefWidth(Constants.getScreenWidth());
        titleContainer.setPrefHeight(Constants.getScreenHeight());

        // Title
        Text title = FXGL.getUIFactoryService().newText(
            "Dungeon Crawler",
            Color.WHITE,
            FontType.GAME,
            50
        );
        title.setTextAlignment(TextAlignment.CENTER);
        title.setTranslateY(-100);
        titleContainer.getChildren().addAll(title);

        // Buttons
        Button startButton = new FXGLButton("Start Game");
        Button quitButton = new FXGLButton("Quit");
        quitButton.setOnAction(e -> fireExit());

        Button[] buttons = {startButton, quitButton};
        for (Button button : buttons) {
            button.setMaxWidth(Double.POSITIVE_INFINITY);
            button.setPrefHeight(40);
        }

        // Button Container
        double buttonBoxWidth = 300;
        double tranlsateX = (Constants.getScreenWidth() - buttonBoxWidth) / 2;
        VBox buttonBox = new VBox(15, buttons);
        buttonBox.setPrefWidth(buttonBoxWidth);
        buttonBox.setFillWidth(true);
        buttonBox.setTranslateX(tranlsateX);
        buttonBox.setTranslateY(450);

        Node[] menuChildren = {titleContainer, buttonBox};
        getContentRoot().getChildren().addAll(menuChildren);

        startButton.setOnAction(e -> fireNewGame());
    }


    @NotNull
    @Override
    protected Button createActionButton(@NotNull StringBinding stringBinding,
                                        @NotNull Runnable runnable) {
        return new Button();
    }

    @NotNull
    @Override
    protected Button createActionButton(@NotNull String s, @NotNull Runnable runnable) {
        return new Button();
    }

    @NotNull
    @Override
    protected Node createBackground(double v, double v1) {
        return FXGL.texture("background/MainMenuBackground.jpg");
    }

    @NotNull
    @Override
    protected Node createProfileView(@NotNull String s) {
        return new Rectangle();
    }

    @NotNull
    @Override
    protected Node createTitleView(@NotNull String s) {
        return new Rectangle();
    }

    @NotNull
    @Override
    protected Node createVersionView(@NotNull String s) {
        return new Rectangle();
    }
}
