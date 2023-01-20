package com.ms.example.context;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(long id) {
        super(String.format("Resource with id %d not found", id));
    }
}
