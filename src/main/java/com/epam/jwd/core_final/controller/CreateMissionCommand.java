package com.epam.jwd.core_final.controller;

import com.epam.jwd.core_final.criteria.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.criteria.SpaceshipCriteriaBuilder;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceShipServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CreateMissionCommand implements ICommand{
    private static final String DESCRIPTION = "Create mission";

    @Override
    public String getText() {
        return DESCRIPTION;
    }

    @Override
    public void execute() {
        CrewServiceImpl crewService = CrewServiceImpl.getInstance();
        SpaceShipServiceImpl shipService = SpaceShipServiceImpl.getInstance();

        MissionServiceImpl missionService = MissionServiceImpl.getInstance();
        Spaceship ship = (Spaceship) shipService.findAllSpaceshipsByCriteria(
                SpaceshipCriteriaBuilder.newBuild().setReady(true).build()).stream().findAny().get();

        List<CrewMember> list = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < ship.getCrew().get(Role.resolveRoleById(i)); j++) {
                CrewMember member;
                Optional<CrewMember> result= crewService.findAllCrewMembersByCriteria(
                        CrewMemberCriteriaBuilder.newBuild().setRole(Role.resolveRoleById(i)).setReady(true).build()
                ).stream().findAny();
                if (result.isPresent()) {
                    member = (CrewMember) result.get();
                } else {
                    return;
                    // throw  an exception
                }
                crewService.assignCrewMemberOnMission(member);
                list.add(member);
            }
        }

        long time = (long) (Calendar.getInstance().getTime().getTime() * Math.random() * 2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Date dateBegin = calendar.getTime();

        calendar.setTimeInMillis(time + 1000 * 7 * 24 * 3600);

        Date dateEnd = calendar.getTime();

        //(String missionName, Date startDate, Date endDate,
        //                         long distance, Spaceship assignedSpaceShift,
        //                         List<CrewMember> assignedCrew, MissionResult missionResult)
        FlightMission mission = missionService.createMission(new String("Mission" + ((int) Math.random() * 100.)),
                dateBegin,
                dateEnd,
                (long) (Math.random() * 1000000.),
                ship,
                list,
                MissionResult.PLANNED);

        System.out.println(mission);

    }
}
