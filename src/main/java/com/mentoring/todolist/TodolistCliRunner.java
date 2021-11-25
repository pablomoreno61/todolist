package com.mentoring.todolist;

import com.mentoring.todolist.infrastructure.cli.CliDispatcher;
import com.mentoring.todolist.infrastructure.cli.exception.UnknownCliActionException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

@ConditionalOnNotWebApplication
@Component
public class TodolistCliRunner implements CommandLineRunner {
    @Autowired
    private CliDispatcher cliDispatcher;

    private String action = "";
    private String body = "";

    public static void main(String[] args) {
        SpringApplication.run(TodolistCliRunner.class, args);
    }

    @Override
    public void run(String[] args)
        throws ArrayIndexOutOfBoundsException {

        InputStream inputStream = null;

        try {
            for (String arg : args) {
                // TODO This split could map to an object String action, String body
                String[] field = arg.split("=");
                if (field[0].equals("--action")) {
                    action = field[1];
                } else if(field[0].equals("--body")) {
                    body = field[1];
                    inputStream = getClass().getClassLoader().getResourceAsStream(body);
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) { }

        if (action.equals("") || body.equals("")) {
            output("Not enough arguments provided: action and body are mandatory.");
            return;
        }

        String managedResponse;

        try {
            managedResponse = cliDispatcher.manage(action, inputStream);
        } catch (UnknownCliActionException | IOException e) {
            output(e.getMessage());
            return;
        }

        output(managedResponse);
    }

    private void output(String s) {
        System.out.println(s);
    }
}
