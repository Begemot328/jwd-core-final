package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.ObjectCreationException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class SpaceShipServiceImpl implements SpaceshipService {
    private ApplicationContext context = NassaContext.getInstance();

    private final static SpaceShipServiceImpl INSTANCE = new SpaceShipServiceImpl();

    public static SpaceShipServiceImpl getInstance() {
        return INSTANCE;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return (List<Spaceship>) context.retrieveBaseEntityList(Spaceship.class);
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> list = findAllSpaceships();

        return  Arrays.asList(list.stream()
                .filter((Predicate<Spaceship>) criteria.getPredicate())
                .toArray(Spaceship[]::new));
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> list = findAllSpaceships();

        return  (list.stream()
                .filter((Predicate<Spaceship>) criteria.getPredicate())
                .findAny());
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        List<Spaceship> list = findAllSpaceships();
        Optional<Spaceship> optional = list.stream()
                .filter(ship -> ship.getName().equals(spaceship.getName()))
                .findAny();
        if (optional.isPresent()) {
            list.remove(optional.get());
            list.add(spaceship);
        } else {
            return null;
        }
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        spaceship.setReadyForNextMissions(false);
        updateSpaceshipDetails(spaceship);
    }

    @Override
    public Spaceship createSpaceship(Object... args) throws RuntimeException {
        SpaceshipFactory factory = new SpaceshipFactory();
        Spaceship ship = factory.create(args);
        List<Spaceship> list = findAllSpaceships();

        if (list.stream()
                .filter(spaceship -> ship.getName().equals(spaceship.getName()))
                .findAny().isPresent()) {
            throw new ObjectCreationException(ship);
        }
        list.add(ship);
        ship.setId(context.getId());
        return ship;
    }
}
