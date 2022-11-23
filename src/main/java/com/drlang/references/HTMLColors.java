package com.drlang.references;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HTMLColors {
    public static final Object[][] ARRAY = {};
    public static final Map<Integer, String> MAP = Arrays.stream(ARRAY)
            .collect(Collectors.toMap(
                    element -> (Integer) element[0],
                    element -> (String) element[1],
                    (v1, v2) -> {
                        throw new IllegalStateException();
                    }, LinkedHashMap::new));

    public static <V, K> Map<V, K> tes(Map<K, V> map) {

        return map.entrySet().stream().
                collect(Collectors.toMap(Map.Entry::getValue,
                        Map.Entry::getKey,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        }, LinkedHashMap::new));
    }

    public static void setMap(HashMap<String, Object> k) {
        NavigableMap<Integer, String> COLORS = new ConcurrentSkipListMap<>();
        COLORS.firstEntry();
        COLORS.lastEntry();

    }
}
