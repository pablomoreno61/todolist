package com.mentoring.todolist.infrastructure.config;

import com.mentoring.todolist.domain.RandomUUIDProvider;
import com.mentoring.todolist.domain.UUIDProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UUIDProviderConfig {

    @Bean
    public UUIDProvider uuidProvider() {
        return new RandomUUIDProvider();
    }
}
