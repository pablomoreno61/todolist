package com.mentoring.todolist.domain;

import java.util.UUID;

public class RandomUUIDProvider implements UUIDProvider {

    @Override
    public UUID uuid() {
        return UUID.randomUUID();
    }
}
