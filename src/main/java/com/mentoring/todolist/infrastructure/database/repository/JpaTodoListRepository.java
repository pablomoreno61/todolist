package com.mentoring.todolist.infrastructure.database.repository;

import com.mentoring.todolist.infrastructure.database.entity.TodoListModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTodoListRepository extends JpaRepository<TodoListModel, UUID> {

}
