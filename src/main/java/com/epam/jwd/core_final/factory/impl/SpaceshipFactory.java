package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
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
                        && args[2] instanceof Short[]
                        && ((Long[]) args[2]).length == 4
                        && args[1] instanceof Long)) {
            return null;
        } else {
            Short[] array = (Short[]) args[2];
            name = (String) args[1];
            distance = (Long) args[0];

            Map<Role, Short> crew = new HashMap<>();
            crew.put(Role.resolveRoleById(1), array[0]);
            return new Spaceship(crew, distance, name);
        }
    }
}
