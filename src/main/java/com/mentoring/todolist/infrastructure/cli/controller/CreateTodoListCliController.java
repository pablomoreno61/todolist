package com.mentoring.todolist.infrastructure.cli.controller;

import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.domain.usecase.CreateTodoList;
import com.mentoring.todolist.domain.usecase.CreateTodoListInput;
import com.mentoring.todolist.domain.usecase.CreateTodoListOutput;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListRequest;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class CreateTodoListCliController
{
    private final CreateTodoList createTodoList;

    @Autowired
    public CreateTodoListCliController(
        CreateTodoList createTodoList
    ) {
        this.createTodoList = createTodoList;
    }

    @ExceptionHandler({InvalidTodoListFormatException.class})
    public ResponseEntity<String> handleUnauthorizedException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    public CreateTodoListResponse add(
        CreateTodoListRequest todoListRequestData
    ) {
        CreateTodoListInput todoListRequest = new CreateTodoListInput(
            todoListRequestData.getName()
        );

        CreateTodoListOutput createTodoListOutput;

        createTodoListOutput = createTodoList.execute(todoListRequest);

        return CreateTodoListResponse.fromCreateTodoListOutput(createTodoListOutput);
    }
}
