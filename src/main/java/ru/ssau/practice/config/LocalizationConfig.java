package ru.ssau.practice.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.ssau.practice.service.localization.LocalizationKeyResolver;
import ru.ssau.practice.service.localization.MessageSourceLocalizationKeyResolver;

@Configuration
public class LocalizationConfig
{
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocalizationKeyResolver localizationKeyResolver() {
        return new MessageSourceLocalizationKeyResolver(messageSource());
    }
}
