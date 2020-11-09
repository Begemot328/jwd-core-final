package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Spaceship;

import java.util.function.Predicate;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    Predicate<Spaceship> predicate;

    SpaceshipCriteria() {
        predicate = (figure) -> true;
    }

    @Override
    public Predicate<Spaceship> getPredicate() {
        return predicate;
    }

    public void setMinimalDistance(long distance) {
        predicate = predicate.and(spaceship -> spaceship.getFlightDistance() >= distance);
    }

    public void setReady(boolean ready) {
        predicate = predicate.and(spaceship -> spaceship.isReadyForNextMissions() == ready);
    }
}
