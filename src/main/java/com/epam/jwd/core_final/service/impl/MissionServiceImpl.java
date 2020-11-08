package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.ObjectCreationException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MissionServiceImpl implements MissionService {
    private ApplicationContext context;

    private final static CrewServiceImpl INSTANCE = new CrewServiceImpl();

    public static CrewServiceImpl getInstance() {
        return INSTANCE;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }


    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) context.retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> list = findAllMissions();

        return  Arrays.asList(list.stream()
                .filter((Predicate<FlightMission>) criteria.getPredicate())
                .toArray(FlightMission[]::new));
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> list = findAllMissions();

        return  (list.stream()
                .filter((Predicate<FlightMission>) criteria.getPredicate())
                .findAny());
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        List<FlightMission> list = findAllMissions();
        Optional<FlightMission> optional = list.stream()
                .filter(mission -> mission.getName().equals(flightMission.getName()))
                .findAny();
        if (optional.isPresent()) {
            list.remove(optional.get());
            list.add(flightMission);
        } else {
            return null;
        }
        return flightMission;
    }

    @Override
    public FlightMission createMission(Object... args) {
        FlightMissionFactory factory = new FlightMissionFactory();
        FlightMission flightMission = factory.create(args);

        List<FlightMission> list = findAllMissions();

        if (list.stream()
                .filter(mission -> mission.getMissionName().equals(flightMission.getMissionName()))
                .findAny().isPresent()) {
            throw new ObjectCreationException(flightMission);
        }
        list.add(flightMission);
        return flightMission;
    }
}
