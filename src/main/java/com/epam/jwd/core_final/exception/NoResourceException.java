package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.criteria.Criteria;

public class NoResourceException extends RuntimeException {
    private Criteria criteria;

    public NoResourceException(Criteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public String getMessage() {
        String message;
        message = " Object by criteria " + criteria.toString() + " cannot be founded";

        return message;
    }
}
