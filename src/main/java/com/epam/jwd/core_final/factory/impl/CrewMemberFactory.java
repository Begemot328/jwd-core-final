package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.FactoryException;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    @Override
    public CrewMember create(Object... args) {
        String name;
        Role role;
        Rank rank;

        if (args.length < 3 ||
                !(args[0] instanceof Long
                        && args[2] instanceof Long
                        && args[1] instanceof String)) {
            return null;
        } else {
            role = Role.resolveRoleById((int) args[0]);
            rank = Rank.resolveRankById((int) args[2]);
            name = (String) args[1];
        }

        return new CrewMember(name, role, rank);
    }
}
