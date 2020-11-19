package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.criteria.Criteria;

public class NoResourceException extends RuntimeException {
    private Criteria criteria;
    private String message = new String();

    public NoResourceException(Criteria criteria) {
        super();
        this.criteria = criteria;
    }

    public NoResourceException(String message, Criteria criteria) {
        super(message);
        this.criteria = criteria;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return  "Object by criteria " + criteria.toString() + " cannot be founded \n " + message;
    }
}
