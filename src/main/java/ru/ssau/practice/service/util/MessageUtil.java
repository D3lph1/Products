package ru.ssau.practice.service.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public final class MessageUtil {
    /**
     * Private constructor to ensure non-instantiation because this class
     * contains only static methods.
     */
    private MessageUtil() {
    }

    public static String retrieveFromSource(String key, MessageSource source) {
        return source.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
