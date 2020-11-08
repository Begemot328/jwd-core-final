package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.Strategy;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();


    private final static NassaContext INSTANCE = new NassaContext();

    private final static String name = Spaceship.class.getName();

    private NassaContext() {}

    public static NassaContext getInstance() {
        return INSTANCE;
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
         if (tClass.getName().equals(CrewMember.class.getName())) {
             return (Collection<T>) crewMembers;
         }
        if (tClass.getName().equals(Spaceship.class.getName())) {
            return (Collection<T>) spaceships;
        }
        return null;
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        Strategy strategy;
        strategy = new SpaceshipStrategy();
        strategy.populate(spaceships);

        strategy = new CrewMemberStrategy();
        strategy.populate(crewMembers);
        throw new InvalidStateException();
    }



}
