package com.group19.javafxgame.utils;

import com.almasb.fxgl.dsl.FXGL;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;

import java.util.EnumSet;

public class MenuChoiceBox<T extends Enum<T>> extends ChoiceBox<T> {

    private ChoiceBox<T> choiceBox;

    public MenuChoiceBox(ObservableList<T> obsList, T defaultT, EventHandler<ActionEvent> e) {
        this.choiceBox = FXGL.getUIFactoryService().newChoiceBox(obsList);
        this.choiceBox.setValue(defaultT);
        this.choiceBox.setOnAction(e);
    }

    public MenuChoiceBox(ObservableList<T> obsList, T defaultT) {
        this.choiceBox = FXGL.getUIFactoryService().newChoiceBox(obsList);
        this.choiceBox.setValue(defaultT);
    }

    public MenuChoiceBox(ObservableList<T> obsList) {
        this.choiceBox = FXGL.getUIFactoryService().newChoiceBox(obsList);
    }

    public MenuChoiceBox(EnumSet<T> tEnumSet, T defaultT) {
        this(new EnumUtil<T>().enumToObservableList(tEnumSet), defaultT);
    }

    public MenuChoiceBox(EnumSet<T> tEnumSet) {
        this(new EnumUtil<T>().enumToObservableList(tEnumSet));
    }

    public ChoiceBox<T> getChoiceBox() {
        return choiceBox;
    }
}
