package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

import java.util.function.Predicate;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private final String READY= "ready for next mission; ";
    private final String NOT_READY= "not ready for next mission; ";

    private Predicate<Spaceship> predicate;
    private String description;

    SpaceshipCriteria() {
        predicate = (figure) -> true;
        description = new String();
    }

    @Override
    public Predicate<Spaceship> getPredicate() {
        return predicate;
    }

    public void setMinimalDistance(long distance) {
        predicate = predicate.and(spaceship -> spaceship.getFlightDistance() >= distance);
        description.concat("distance = " + distance);
    }

    public void setReady(boolean ready) {
        predicate = predicate.and(spaceship -> spaceship.isReadyForNextMissions() == ready);
        description += (ready ? READY : NOT_READY);

    }
}
