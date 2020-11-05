package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        return toString();
    }

    /**
     * todo via java.lang.enum methods!
     * @throws UnknownEntityException if such id does not exist
     */
    public static Role resolveRoleById(int id) {
        Role[] roles = Role.values();
        Stream<Role> stream = Arrays.stream(roles);
        Optional<Role> result =  stream.filter((role) -> role.getId() == id)
                .findAny();
        if(result.isPresent()) {
            return result.get();
        } else {
            throw  new UnknownEntityException("No entity with id " + id);
        }
    }
}
