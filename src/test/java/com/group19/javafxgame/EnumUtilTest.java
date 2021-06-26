package com.group19.javafxgame;

import com.group19.javafxgame.utils.EnumUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EnumUtilTest {

    private enum testEnum {
        ONE, TWO, THREE
    }

    EnumUtil<testEnum> testUtil;

    @BeforeEach
    public void setup() {
        testUtil = new EnumUtil<>();
    }

    @Test
    public void testEmptyEnum() {
        var result = testUtil
                .enumToObservableList(EnumSet.noneOf(testEnum.class));

        ObservableList<testEnum> emptyList =
                FXCollections.emptyObservableList();

        Assertions.assertEquals(emptyList, result);
    }

    @Test
    public void testSingleElementEnum() {
        var result = testUtil
                .enumToObservableList(EnumSet.of(testEnum.ONE));

        ArrayList<testEnum> list = new ArrayList<testEnum>();
        list.add(testEnum.ONE);

        ObservableList<testEnum> oneList =
                FXCollections.observableList(list);

        Assertions.assertEquals(oneList, result);

    }

}
