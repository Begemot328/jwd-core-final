package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private Map<Role, Short> crew;
    private long flightDistance;
    private String name;
    private boolean isReadyForNextMissions = true;

    Spaceship(Map<Role, Short> crew, long flightDistance, String name) {
        this.name = name;
        this.crew = crew;
        this.flightDistance = flightDistance;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public long getFlightDistance() {
        return flightDistance;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "name='" + name + '\'' +
                ", flightDistance=" + flightDistance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return flightDistance == spaceship.flightDistance &&
                isReadyForNextMissions == spaceship.isReadyForNextMissions &&
                Objects.equals(crew, spaceship.crew) &&
                name.equals(spaceship.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, flightDistance, name, isReadyForNextMissions);
    }
}
