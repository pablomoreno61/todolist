package com.mentoring.todolist.domain.repository;

import com.mentoring.todolist.domain.entity.TodoList;

public interface TodoListRepository {
    void save(TodoList todoList);
}
