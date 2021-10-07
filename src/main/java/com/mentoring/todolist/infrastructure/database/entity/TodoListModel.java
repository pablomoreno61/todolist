package com.mentoring.todolist.infrastructure.database.entity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="todo_lists")
public class TodoListModel {
    @Id
    private UUID id;

    private String name;

    private ZonedDateTime createdAt;

    @OneToMany
    @Exclude
    private List<TaskModel> tasks;
}
