package com.mentoring.todolist.infrastructure.rest.controller;

import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.domain.usecase.CreateTodoList;
import com.mentoring.todolist.domain.usecase.CreateTodoListInput;
import com.mentoring.todolist.domain.usecase.CreateTodoListOutput;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListRequest;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidTodoListFormatException(
        InvalidTodoListFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @PostMapping("/add")
    public CreateTodoListResponse add(
        @RequestBody CreateTodoListRequest todoListRequestData
    ) throws InvalidTodoListFormatException {
        CreateTodoListInput todoListRequest = new CreateTodoListInput(
            todoListRequestData.getName()
        );

        CreateTodoListOutput createTodoListOutput;

        createTodoListOutput = createTodoList.execute(todoListRequest);

        return CreateTodoListResponse.fromCreateTodoListOutput(createTodoListOutput);
    }
}
