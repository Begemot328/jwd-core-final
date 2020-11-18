package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public interface ApplicationContext {

    static Logger logger = LoggerFactory.getLogger(ApplicationContext.class);

    <T extends BaseEntity>Collection<T> retrieveBaseEntityList(Class<T> tClass);

    void init() throws InvalidStateException;

    int getId();

    static Logger getLoggerInstance() {
        return logger;
    }

}
