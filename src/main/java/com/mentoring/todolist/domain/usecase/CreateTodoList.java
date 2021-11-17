package com.mentoring.todolist.domain.usecase;

import com.mentoring.todolist.domain.entity.TodoList;
import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.domain.repository.TodoListRepository;

public class CreateTodoList {
    private final TodoListRepository repository;

    public CreateTodoList(TodoListRepository repository) {
        this.repository = repository;
    }

    public CreateTodoListOutput execute(CreateTodoListInput input)
        throws InvalidTodoListFormatException {
        TodoList todoList = new TodoList(input.getName());

        repository.save(todoList);

        return new CreateTodoListOutput(
            todoList.getId(),
            todoList.getName(),
            todoList.getCreatedAt()
        );
    }
}
