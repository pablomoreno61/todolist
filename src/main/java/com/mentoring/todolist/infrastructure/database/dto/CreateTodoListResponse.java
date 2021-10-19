package com.mentoring.todolist.infrastructure.database.dto;

import com.mentoring.todolist.domain.usecase.CreateTodoListOutput;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class CreateTodoListResponse
{
    private UUID id;
    private String name;
    private ZonedDateTime createdAt;

    public static CreateTodoListResponse fromCreateTodoListOutput(
        CreateTodoListOutput createTodoListOutput
    ) {
        return CreateTodoListResponse.builder()
            .id(createTodoListOutput.getId())
            .name(createTodoListOutput.getName())
            .createdAt(createTodoListOutput.getCreatedAt())
            .build();
    }
}
