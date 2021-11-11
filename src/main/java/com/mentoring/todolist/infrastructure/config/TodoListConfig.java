package com.mentoring.todolist.infrastructure.config;

import com.mentoring.todolist.domain.repository.TodoListRepository;
import com.mentoring.todolist.domain.usecase.CreateTodoList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoListConfig
{
    @Bean
    public CreateTodoList createTodoList(TodoListRepository todoListRepository) {
        return new CreateTodoList(todoListRepository);
    }
}
