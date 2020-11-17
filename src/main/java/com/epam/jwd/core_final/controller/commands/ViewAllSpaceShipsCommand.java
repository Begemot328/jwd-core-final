package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceShipServiceImpl;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class ViewAllSpaceShipsCommand implements ICommand {

    private final String MESSAGE = "View all spaceships";
    private final String EMPTY_MESSAGE = "List is empty";

    private Consumer<? super String> action;

    public ViewAllSpaceShipsCommand(Consumer<? super String> action) {
        this.action = action;
    }

    @Override
    public String getText() {
        return MESSAGE;
    }

    @Override
    public void execute() {

        SpaceShipServiceImpl shipService = SpaceShipServiceImpl.getInstance();
        if (!shipService.findAllSpaceships().isEmpty()) {
            shipService.findAllSpaceships().stream()
                    .map(spaceship -> spaceship.toString())
            .forEach(action);
        } else {
            action.accept(EMPTY_MESSAGE);
        }
    }
}
