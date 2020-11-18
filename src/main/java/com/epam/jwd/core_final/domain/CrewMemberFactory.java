package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    @Override
    public CrewMember create(Object... args) {
        String name;
        Role role;
        Rank rank;

        if (args.length < 3 ||
                !(args[0] instanceof Role
                        && args[2] instanceof Rank
                        && args[1] instanceof String)) {
            return null;
        } else {
            role = ((Role) args[0]);
            rank = ((Rank) args[2]);
            name = (String) args[1];
        }

        return new CrewMember(name, role, rank);
    }
}
