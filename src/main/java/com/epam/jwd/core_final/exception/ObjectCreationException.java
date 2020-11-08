package com.epam.jwd.core_final.exception;

public class ObjectCreationException extends RuntimeException{
    Object o;

    public ObjectCreationException(Object o) {
        super();
        this.o = o;
    }

    @Override
    public String getMessage() {
        // todo
        String message;
        message = o.toString() + "cannot be created";

        return message;
    }
}
