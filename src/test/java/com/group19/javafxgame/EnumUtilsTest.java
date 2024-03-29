package com.group19.javafxgame;

import com.group19.javafxgame.utils.EnumUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumSet;

public class EnumUtilsTest {

    private enum TestEnum {
        ONE, TWO, THREE
    }

    private EnumUtils<TestEnum> testUtil;

    @BeforeEach
    public void setup() {
        testUtil = new EnumUtils<>();
    }

    @Test
    public void testEmptyEnum() {
        var result = testUtil
                .enumToObservableList(EnumSet.noneOf(TestEnum.class));

        ObservableList<TestEnum> emptyList =
                FXCollections.emptyObservableList();

        Assertions.assertEquals(emptyList, result);
    }

    @Test
    public void testSingleElementEnum() {
        var result = testUtil
                .enumToObservableList(EnumSet.of(TestEnum.ONE));

        ArrayList<TestEnum> list = new ArrayList<TestEnum>();
        list.add(TestEnum.ONE);

        ObservableList<TestEnum> oneList =
                FXCollections.observableList(list);

        Assertions.assertEquals(oneList, result);
    }

    @Test
    public void testElementOrderFullEnum() {
        var result = testUtil
                .enumToObservableList(EnumSet.allOf(TestEnum.class));

        ArrayList<TestEnum> list = new ArrayList<>();

        list.addAll(new ArrayList<>(EnumSet.allOf(TestEnum.class)));

        ObservableList<TestEnum> oneList =
                FXCollections.observableList(list);

        Assertions.assertEquals(oneList, result);
    }

}
