package com.mentoring.todolist.infrastructure.database.repository;

import com.mentoring.todolist.infrastructure.database.entity.TodoListModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTodoListRepository extends JpaRepository<TodoListModel, UUID> {

}
