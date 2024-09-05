package app.controller;

import app.CoreException;

public class EntityNotFoundException extends CoreException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
