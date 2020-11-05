package com.epam.jwd.core_final.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo

    private String name = "FlightMission";
    private String missionName;
    private Date startDate;
    private Date endDate;
    private long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMission(String missionName, Date startDate, Date endDate,
                         long distance, Spaceship assignedSpaceShift,
                         List<CrewMember> assignedCrew, MissionResult missionResult) {
        this.missionName = missionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceShift = assignedSpaceShift;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
    }

    public FlightMission(String missionName, Date startDate, Date endDate,
                         long distance, Spaceship assignedSpaceShift,
                         MissionResult missionResult, CrewMember ... members) {
        this.missionName = missionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceShift = assignedSpaceShift;
        this.assignedCrew = Arrays.asList(members.clone());
        this.missionResult = missionResult;
    }

}
