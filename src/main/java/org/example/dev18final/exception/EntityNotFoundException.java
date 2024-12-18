package org.example.dev18final.exception;

import org.example.dev18final.exception.DatabaseException;

public class EntityNotFoundException extends DatabaseException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
