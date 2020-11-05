package com.epam.jwd.core_final.context;

import java.util.Collection;

public interface Strategy<T> {

    public Collection<T> retrieveBaseEntityList();
}
