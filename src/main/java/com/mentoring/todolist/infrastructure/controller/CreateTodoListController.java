package com.mentoring.todolist.infrastructure.controller;

import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.domain.usecase.CreateTodoList;
import com.mentoring.todolist.domain.usecase.CreateTodoListInput;
import com.mentoring.todolist.domain.usecase.CreateTodoListOutput;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListRequest;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todolist")
public class CreateTodoListController
{
    private final CreateTodoList createTodoList;

    @Autowired
    public CreateTodoListController(
        CreateTodoList createTodoList
    ) {
        this.createTodoList = createTodoList;
    }

    // TODO Catch exception with an ExceptionHandler
    // TODO Catch size/format name exception
    @PostMapping("/add")
    public CreateTodoListResponse add(
        @RequestBody CreateTodoListRequest todoListRequestData
    ) {
        CreateTodoListInput todoListRequest = new CreateTodoListInput(
            todoListRequestData.getName()
        );

        CreateTodoListOutput createTodoListOutput;

        try {
            createTodoListOutput = createTodoList.execute(todoListRequest);
        } catch (InvalidTodoListFormatException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
            );
        }

        return CreateTodoListResponse.fromCreateTodoListOutput(createTodoListOutput);
    }
}
