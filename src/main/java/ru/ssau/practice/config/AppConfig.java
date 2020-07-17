package ru.ssau.practice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ssau.practice.service.db.pagination.JpaQueryPaginator;
import ru.ssau.practice.service.db.pagination.Paginator;

@Configuration
public class AppConfig
{
    @Bean
    public Paginator<?> paginator()
    {
        return new JpaQueryPaginator<>();
    }

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
