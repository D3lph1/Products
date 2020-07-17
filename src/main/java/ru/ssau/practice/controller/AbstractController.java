package ru.ssau.practice.controller;

import org.springframework.context.MessageSource;
import ru.ssau.practice.service.util.MessageUtil;

public class AbstractController
{
    private final MessageSource messageSource;

    public AbstractController(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }

    protected String t(String localizationKey)
    {
        return MessageUtil.retrieveFromSource(localizationKey, messageSource);
    }
}
