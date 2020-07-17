package ru.ssau.practice.service.util;

import java.lang.reflect.Array;
import java.util.*;

public final class ArrayUtil
{
    /**
     * Private constructor to ensure non-instantiation because this class
     * contains only static methods.
     */
    private ArrayUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] returnNotNull(Class<? extends T> type, T... items)
    {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (item != null) {
                result.add(item);
            }
        }

        return result.toArray((T[]) Array.newInstance(type, result.size()));
    }
}
