package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.criteria.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteriaBuilder;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.NoResourceException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceShipServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateMissionCommand implements ICommand {
    private static final String DESCRIPTION = "Create mission";
    private static final String MISSION_NAME = "Mission";
    private static final String EXCEPTION_MESSAGE = "Mission cannot be created ";
    private static final String SPACE = " ";

    @Override
    public String getText() {
        return DESCRIPTION;
    }

    @Override
    public void execute() throws NoResourceException {
        CrewServiceImpl crewService = CrewServiceImpl.getInstance();
        SpaceShipServiceImpl shipService = SpaceShipServiceImpl.getInstance();

        MissionServiceImpl missionService = MissionServiceImpl.getInstance();
        Criteria<Spaceship> spaceshipCriteria =
                SpaceshipCriteriaBuilder.newBuild().setReady(true).build();
        Spaceship ship = shipService.findAllSpaceshipsByCriteria(
                spaceshipCriteria)
                .stream().findAny().orElseThrow(() -> new NoResourceException(EXCEPTION_MESSAGE, spaceshipCriteria));

        List<CrewMember> list = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < ship.getCrew().get(Role.resolveRoleById(i)); j++) {
                Criteria<CrewMember> crewMemberCriteria =
                        CrewMemberCriteriaBuilder.newBuild()
                                .setRole(Role.resolveRoleById(i)).setReady(true).build();
                CrewMember member = crewService.findAllCrewMembersByCriteria(
                        crewMemberCriteria
                ).stream().findAny().orElseThrow(() -> new NoResourceException(EXCEPTION_MESSAGE, crewMemberCriteria));

                crewService.assignCrewMemberOnMission(member);
                shipService.assignSpaceshipOnMission(ship);

                list.add(member);
            }
        }

        LocalDate dateBegin = LocalDate.now().plusYears(randomize(10)).plusMonths(randomize(6)).plusDays(randomize(15));
        LocalDate dateEnd = dateBegin.plusWeeks(randomize(7));

        FlightMission mission = missionService.createMission(
                MISSION_NAME + SPACE + ((int) (Math.random() * 100.)),
                dateBegin,
                dateEnd,
                (long) (Math.random() * 1000000.),
                ship,
                list,
                MissionResult.PLANNED);

        System.out.println(mission);
    }

    private int randomize(int range) {
        return (int) (Math.random() * range * 1.);
    }
}
