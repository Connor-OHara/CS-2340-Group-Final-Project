package com.group19.javafxgame.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EnumUtils<T extends Enum<T>> {

    public ObservableList<T> enumToObservableList(EnumSet<T> tEnum) {
        List<T> vals = new ArrayList<>(10);

        //Add all enum values to array that feeds ObservableList
        vals.addAll(tEnum);

        return FXCollections.observableList(vals);
    }

}
