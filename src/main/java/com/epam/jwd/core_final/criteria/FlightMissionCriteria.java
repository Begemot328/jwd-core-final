package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.Predicate;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    Predicate<FlightMission> predicate;

    FlightMissionCriteria() {
        predicate = (figure) -> true;
    }

    @Override
    public Predicate<FlightMission> getPredicate() {
        return predicate;
    }

    public  void setDateAfter(Date date) {
        predicate = predicate.and(
                flightMission -> flightMission.getStartDate().getTime() >= date.getTime() );
    }

    public  void setDateBefore(Date date) {
        predicate = predicate.and(
                flightMission -> flightMission.getStartDate().getTime() <= date.getTime() );
    }
}
