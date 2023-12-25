package ru.innopolis.java.homework10_abstract_classes_iterfaces_lambda;

import java.util.ArrayList;
import java.util.List;

abstract class Sequence {
    public static List<Integer> filter(Integer[] array, ByCondition condition) {
        List<Integer> resultArr = new ArrayList<>();

        for(int item : array) {
            if (condition.isOk(item)) resultArr.add(item);
        }

        return resultArr;
    }
}
