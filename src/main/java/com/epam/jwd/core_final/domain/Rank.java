package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum Rank implements BaseEntity {
    TRAINEE(1L),
    SECOND_OFFICER(2L),
    FIRST_OFFICER(3L),
    CAPTAIN(4L);

    private final Long id;

    Rank(Long id) {
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
     *
     * @throws UnknownEntityException if such id does not exist
     */
    public static Rank resolveRankById(int id) {
        Rank[] ranks = Rank.values();
        Stream<Rank> stream =Arrays.stream(ranks);
        Optional<Rank> result =  stream.filter((rank) -> rank.getId() == id)
                .findAny();
        if(result.isPresent()) {
            return result.get();
        } else {
            throw  new UnknownEntityException("No entity with id " + id);
        }
    }
}
