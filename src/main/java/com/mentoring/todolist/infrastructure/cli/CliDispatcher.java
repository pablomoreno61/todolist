package com.mentoring.todolist.infrastructure.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mentoring.todolist.infrastructure.cli.dto.input.CreateTodoListCommand;
import com.mentoring.todolist.infrastructure.cli.exception.UnknownCliActionException;
import com.mentoring.todolist.infrastructure.cli.controller.CreateTodoListCliController;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;

@Service
public class CliDispatcher {
    private final String[] actions = new String[]{"create"};
    private final CreateTodoListCliController createTodoListCliController;

    public CliDispatcher(
        CreateTodoListCliController createTodoListCliController) {
        this.createTodoListCliController = createTodoListCliController;
    }

    private String readFromInputStream(InputStream inputStream)
        throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
            = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public String manage(String action, InputStream inputStream)
        throws IOException, ArrayIndexOutOfBoundsException, UnknownCliActionException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String data = readFromInputStream(inputStream);

        String managedResponse = null;

        switch(action) {
            case "create":
                CreateTodoListCommand createTodoListCommand = objectMapper.readValue(data, CreateTodoListCommand.class);

                CreateTodoListResponse createTodoListResponse = createTodoListCliController.add(createTodoListCommand);

                managedResponse = objectMapper.writeValueAsString(createTodoListResponse);

                break;
            case "update":
                // TODO
                break;
            case "delete":
                // TODO
            case "addTask":
                // TODO
                break;

            default:
                throw UnknownCliActionException.withInvalidAction(action, actions);
        }

        return managedResponse;
    }
}
