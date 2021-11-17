package com.mentoring.todolist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mentoring.todolist.infrastructure.cli.CliDispatcher;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnNotWebApplication
public class TodolistCliRunner implements CommandLineRunner { // ApplicationRunner
    @Autowired
    private CliDispatcher cliDispatcher;

    public static void main(String[] args) {
        SpringApplication.run(TodolistCliRunner.class, args);
    }

    @Override
    public void run(String[] args) throws JsonProcessingException, ArrayIndexOutOfBoundsException {
        String managedResponse = cliDispatcher.manage(
            args[0].split("=")[1],
            args[1].replace("--params=", "").split(",")
        );

        System.out.println(managedResponse);
        /*
        String managedResponse = cliDispatcher.manage(
            args.getOptionValues("action").get(0),
            args.getOptionValues("params").get(0).split(",")
        );

        System.out.println(managedResponse);
         */
    }
}
