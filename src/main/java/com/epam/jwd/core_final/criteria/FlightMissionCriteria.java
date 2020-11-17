package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Predicate;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private Predicate<FlightMission> predicate;
    private String description;

    FlightMissionCriteria() {
        predicate = (figure) -> true;
        description = new String();
    }

    @Override
    public Predicate<FlightMission> getPredicate() {
        return predicate;
    }

    public  void setDateAfter(LocalDate date) {
        predicate = predicate.and(
                flightMission -> flightMission.getStartDate().isAfter(date));
        description.concat("Date after " + date.format(
                DateTimeFormatter.ofPattern(ApplicationProperties.getInstance().getDateFormat())));
    }

    public  void setDateBefore(LocalDate date) {
        predicate = predicate.and(
                flightMission -> flightMission.getStartDate().isAfter(date));
        description.concat("Date before " + date.format(
                DateTimeFormatter.ofPattern(ApplicationProperties.getInstance().getDateFormat())));
    }
}
