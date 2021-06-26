package com.group19.javafxgame;

import com.group19.javafxgame.utils.EnumUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumSet;

public class EnumUtilTest {

    private enum TestEnum {
        ONE, TWO, THREE
    }

    private EnumUtil<TestEnum> testUtil;

    @BeforeEach
    public void setup() {
        testUtil = new EnumUtil<>();
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

}
