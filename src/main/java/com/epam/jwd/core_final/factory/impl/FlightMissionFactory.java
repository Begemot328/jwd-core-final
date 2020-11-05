package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Date;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {


    //(String missionName, Date startDate, Date endDate,
    //                         long distance, Spaceship assignedSpaceShift,
    //                         List<CrewMember> assignedCrew, MissionResult missionResult)

    @Override
    public FlightMission create(Object... args) {
        String missionName;
        Date startDate;
        Date endDate;
        long distance;
        Spaceship assignedSpaceShift;
        List<CrewMember> assignedCrew;
        MissionResult missionResult;

        if (args.length < 7 || !(args[0] instanceof String
                && args[1] instanceof Date
                && args[2] instanceof Date
                && args[3] instanceof Long
                && args[4] instanceof Spaceship
                && args[5] instanceof List
                && ((List<?>) args[5]).isEmpty()
                && (List<?>) ((List<?>) args[5]).get(0) instanceof CrewMember
                && args[6] instanceof MissionResult)) {
            return null;
        } else {

        }
        return new FlightMission((String) args[0], (Date)args[1],
                (Date) args[2], (Long) args[3], (Spaceship) args[4],
                (List<CrewMember>) args[5], (MissionResult) args[6]);
    }
}
