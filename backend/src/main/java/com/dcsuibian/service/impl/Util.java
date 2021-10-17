package com.dcsuibian.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

class Util {
    public static <T, R> List<R> batchConvert(Iterable<T> iterable, Function<T, R> converter) {
        ArrayList<R> list = new ArrayList<>();
        for (var item : iterable) {
            list.add(converter.apply(item));
        }
        return list;
    }
}
