package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.Types.WeaponType;
import com.group19.javafxgame.component.PlayerComponent;
import com.group19.javafxgame.ui.menu.config.InitialConfigSubScene;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;




public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Constants.getGameName());
        settings.setWidth(Constants.getScreenWidth());
        settings.setHeight(Constants.getScreenHeight());
        settings.setVersion(Constants.getGameVersion());
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new MainSceneFactory());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
        vars.put("difficulty", Constants.getDefaultDifficulty());
        vars.put("weapon", Constants.getDefaultWeapon());
        vars.put("name", "");
        vars.put("configFinished", 0);
        vars.put("money", Constants.getDefaultMoney());//TODO: Verify if this is what we want to move forward with.
    }

    @Override
    protected void initGame() {
        Entity background;

        getGameWorld().addEntityFactory(new CharacterFactory());

        background = initBackground();
        initConfigScreen();

        getWorldProperties().<Integer>addListener("configFinished", (prev, now) -> {
            if (now == 1) {
                removeBackgroundAndConfigScreen(background);
                loadRoom();
                Entity player = spawn("Player");
                PlayerComponent playerComponent = player.getComponent(PlayerComponent.class);
                spawnCharacters();
                gameUI(playerComponent);
            }
        });
    }

    private Entity initBackground() {
        getGameScene().setBackgroundColor(Color.color(0.5, 0.5, 0.5, 1.0));
        return spawn("background");
    }

    private Entity initDungeonFloor() {
        return spawn("dungeonFloor");
    }

    private void removeBackgroundAndConfigScreen(Entity background) {
        getGameScene().setBackgroundColor(Color.color(0.5, 0.5, 0.5, 1.0));
        getGameScene().clearUINodes();
        background.removeFromWorld();
    }


    private void initConfigScreen() {
        InitialConfigSubScene configSubScene = new InitialConfigSubScene(
                Constants.getDefaultDifficulty(), Constants.getDefaultWeapon()
        );

        getGameScene().addUINodes(configSubScene.getContentRoot());

    }

    private void loadRoom() {
        FXGL.setLevelFromMap("default2.tmx");
    }

    private void spawnCharacters() {
        Entity player = spawn("Player");
    }

    protected void gameUI(PlayerComponent player) {
        Text goldText = new Text();
        Text goldLabel = new Text("Gold:");

        goldText.setTranslateX(getAppWidth() / 14.0);
        goldLabel.setTranslateX(getAppWidth() / 40.0);

        goldText.setTranslateY(45);
        goldText.setFill(Color.GOLD);
        goldText.setFont(Font.font("Calibra", FontWeight.BOLD, 22));
        goldLabel.setTranslateY(45);
        goldLabel.setFill(Color.GOLD);
        goldLabel.setFont(Font.font("Calibra", FontWeight.BOLD, 22));

        String money = String.valueOf(player.showFunds());
        //TODO: Check if this actually "watches" - done
        //makes goldText watch the gold game value in vars
        //goldText.textProperty().bind(getWorldProperties().intProperty("money").asString());
        goldText.textProperty().bind( new SimpleStringProperty(money));

        getGameScene().addUINode(goldText);
        getGameScene().addUINode(goldLabel);
    }


    private void nextLevel() {
        if (geto("weapon") == WeaponType.SWORD) {
            showMessage("You finished the demo!");
            return;
        }

    }


    public static void main(String[] args) {
        //launches JavaFX application
        launch(args);
    }




}