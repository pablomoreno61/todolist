package com.mentoring.todolist.infrastructure.cli.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mentoring.todolist.TodolistCliRunner;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CreateTodoListCliControllerTest
{
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Autowired
    private TodolistCliRunner todolistCliRunner;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void cannotCreateTodoList_whenNameIsEmpty() throws ArrayIndexOutOfBoundsException {
        String[] args = new String[]{"--action=create", "--body=json/create-todolist-empty-name.json"};

        todolistCliRunner.run(args);

        assertEquals("Todo list name length must be between 1 and 50\nnull\n", outputStreamCaptor.toString());
    }

    @Test
    public void cannotCreateTodoList_WhenNameIsTooLarge() {
        String[] args = new String[]{"--action=create", "--body=json/create-todolist-too-long-name.json"};

        todolistCliRunner.run(args);

        assertEquals("Todo list name length must be between 1 and 50\nnull\n", outputStreamCaptor.toString());
    }

    @Test
    public void createTodoList()
        throws IOException, ArrayIndexOutOfBoundsException {
        String[] args = new String[]{"--action=create", "--body=json/create-todolist.json"};

        todolistCliRunner.run(args);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CreateTodoListResponse createTodoListResponse = objectMapper.readValue(
            outputStreamCaptor.toString(),
            CreateTodoListResponse.class
        );

        assertEquals("TODOLIST_NAME", createTodoListResponse.getName());
        assertNotNull(createTodoListResponse.getCreatedAt());
        assertNotNull(createTodoListResponse.getId());
    }
}
