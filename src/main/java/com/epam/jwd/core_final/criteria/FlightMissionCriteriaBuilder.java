package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.Date;

/**
 * {@link Criteria}  Builder class
 *
 * @author Yury Zmushko
 * @version 1.0
 */
public class FlightMissionCriteriaBuilder {

    private FlightMissionCriteria criteria;

    /**
     * Constructor is hidden
     */
    private FlightMissionCriteriaBuilder() {}


    public static FlightMissionCriteriaBuilder newBuild() {
        FlightMissionCriteriaBuilder builder = new FlightMissionCriteriaBuilder();
        builder.criteria = new FlightMissionCriteria();

        return builder;
    }

    public FlightMissionCriteriaBuilder setDateAfter(LocalDate date) {
        criteria.setDateAfter(date);
        return this;
    }

    public FlightMissionCriteriaBuilder setDateBefore(LocalDate date) {
        criteria.setDateBefore(date);
        return this;
    }

    public Criteria<FlightMission> build() {
        return criteria;
    }
}
