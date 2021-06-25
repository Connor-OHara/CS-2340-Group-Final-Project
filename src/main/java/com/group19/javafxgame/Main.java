package com.group19.javafxgame;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.group19.javafxgame.Types.WeaponType;
import com.group19.javafxgame.ui.menu.main.MainMenuSceneFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;

public class Main extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Constants.GAME_NAME);
        settings.setWidth(Constants.SCREEN_WIDTH);
        settings.setHeight(Constants.SCREEN_HEIGHT);
        settings.setVersion(Constants.GAME_VERSION);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new MainMenuSceneFactory());
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        super.initGameVars(vars);
        vars.put("difficulty", Constants.DEFAULT_DIFFICULTY);
        vars.put("weapon", Constants.DEFAULT_WEAPON);
        vars.put("money", 10);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new CharacterFactory());

        getGameScene().setBackgroundColor(Color.color(0.5, 0.5, 0.5, 1.0));
        FXGL.setLevelFromMap("default2.tmx");
        //System.out.println(geto("weapon").toString());
        player = spawn("Player");

        //System.out.println(getWorldProperties().intProperty("difficulty").asString());

        int dist = 50; //TODO: move to constants?
        /**These lines are commented out because it messed up the walls*/
        //getGameScene().getViewport().setBounds(-dist, -dist, getAppWidth() + dist, getAppHeight() + dist);
        //getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
    }


    protected void initUI(){
        Text goldText = new Text("Gold: " + Constants.DEFAULT_MONEY);
        goldText.setTranslateX(getAppWidth() / 38);

        goldText.setTranslateY(45);
        goldText.setFill(Color.GOLD);
        goldText.setFont(Font.font("Calibra", FontWeight.BOLD,22));
        //makes goldText watch the gold game value in vars
        goldText.textProperty().bind(getWorldProperties().intProperty("money").asString());

        getGameScene().addUINode(goldText);
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
