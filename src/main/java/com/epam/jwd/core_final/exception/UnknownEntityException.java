package com.epam.jwd.core_final.exception;

import java.util.Arrays;

public class UnknownEntityException extends RuntimeException {

    private static final String NEW_LINE = "/n";
    private final String entityName;
    private final Object[] args;

    public UnknownEntityException(String entityName) {
        super();
        this.entityName = entityName;
        this.args = null;
    }

    public UnknownEntityException(String entityName, Object[] args) {
        super();
        this.entityName = entityName;
        this.args = args;
    }

    @Override
    public String getMessage() {
        String message = "Can't create entity " + entityName;

        if (args != null && args.length != 0) {
            message += " with args: ";
            String finalMessage = message;
            Arrays.stream(args).filter(arg -> arg != null)
                    .forEach(arg -> finalMessage.concat(arg.toString() + NEW_LINE));
            message = finalMessage;
        }

        return message;
    }
}
