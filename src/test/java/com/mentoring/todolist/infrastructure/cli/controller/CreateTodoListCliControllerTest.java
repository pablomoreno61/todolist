package com.mentoring.todolist.infrastructure.cli.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mentoring.todolist.TodolistCliRunner;
import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, args = {"--action=create", "--params=name=TODOLIST_NAME"})
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
        String[] args = new String[]{"--action=create", "--params=name="};
        // ApplicationArguments appArguments = new DefaultApplicationArguments(args);

        ArrayIndexOutOfBoundsException thrown = Assertions.assertThrows(
            ArrayIndexOutOfBoundsException.class, () ->
                todolistCliRunner.run(args)
        );
    }


    @Test
    public void cannotCreateTodoList_WhenNameIsTooLarge()
        throws JsonProcessingException, InvalidTodoListFormatException {
        String todolistName = "caracolasdasiuhdashiudihuasdhiuahisudahuisdhiuasdhiuashiudahiusdhuiasihudahuisdiuhashuidahiusdhuiashiudaihusdhuiasdhuiahiusdhiuasdhiuashiudihuasdhuiasdhiuahiusdiuhashiudahiusd";
        String[] args = new String[]{"--action=create", String.format("--params=name=%s", todolistName)};
        // ApplicationArguments appArguments = new DefaultApplicationArguments(args);

        todolistCliRunner.run(args);

        assertEquals("Todo list name length must be between 1 and 50\nnull\n", outputStreamCaptor.toString());
    }

    @Test
    public void createTodoList()
        throws JsonProcessingException, ArrayIndexOutOfBoundsException, InvalidTodoListFormatException {
        String todolistName = "TODOLIST_NAME";
        String[] args = new String[]{"--action=create", String.format("--params=name=%s", todolistName)};
        // ApplicationArguments appArguments = new DefaultApplicationArguments(args);

        todolistCliRunner.run(args);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CreateTodoListResponse createTodoListResponse = objectMapper.readValue(
            outputStreamCaptor.toString(),
            CreateTodoListResponse.class
        );

        assertEquals(todolistName, createTodoListResponse.getName());
        assertNotNull(createTodoListResponse.getCreatedAt());
        assertNotNull(createTodoListResponse.getId());
    }
}
