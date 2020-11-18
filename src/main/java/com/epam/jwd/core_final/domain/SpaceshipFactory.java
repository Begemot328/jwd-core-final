package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.HashMap;
import java.util.Map;

// do the same for other entities
public class SpaceshipFactory implements EntityFactory<Spaceship> {

    @Override
    public Spaceship create(Object... args) {
        String name;
        long distance;

        if (args.length < 3 ||
                !(args[0] instanceof String
                        && args[2] instanceof HashMap
                        && args[2].getClass().equals((new HashMap<Role,Short>()).getClass())
                        && ((HashMap) args[2]).size() == 4
                        && args[1] instanceof Long)) {
            return null;
        } else {
            name = (String) args[0];
            distance = (Long) args[1];

            Map<Role, Short> crew = (HashMap<Role,Short>) args[2];
            return new Spaceship(crew, distance, name);
        }
    }
}
