package ru.ssau.practice.service.util;

import java.util.HashSet;
import java.util.Set;

public final class SetUtil
{
    /**
     * Private constructor to ensure non-instantiation because this class
     * contains only static methods.
     */
    private SetUtil() {
    }

    public static <T> Set<T> difference(final Set<T> first, final Set<T> second) {
        Set<T> result = new HashSet<T>(first);
        result.removeIf(second::contains);
        return result;
    }
}
