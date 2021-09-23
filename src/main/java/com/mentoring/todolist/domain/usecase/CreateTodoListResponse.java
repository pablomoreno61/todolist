package com.mentoring.todolist.domain.usecase;

import java.time.ZonedDateTime;
import java.util.UUID;

public class CreateTodoListResponse {

    private final UUID id;
    private final String name;
    private final ZonedDateTime createdAt;

    public CreateTodoListResponse(UUID id, String name, ZonedDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }
}
