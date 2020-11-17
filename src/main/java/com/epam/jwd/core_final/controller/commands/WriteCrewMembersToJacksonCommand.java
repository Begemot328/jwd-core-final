package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceShipServiceImpl;
import com.epam.jwd.core_final.util.CollectionJSONWriter;

import java.util.Collection;

public class WriteCrewMembersToJacksonCommand implements ICommand {
    private final String MESSAGE = "Write all crew members to JSON";

    @Override
    public String getText() {
        return MESSAGE;
    }

    @Override
    public void execute() throws InvalidStateException {
        CrewServiceImpl crewService = CrewServiceImpl.getInstance();
        if (!crewService.findAllCrewMembers().isEmpty()) {
            CollectionJSONWriter.write((Collection) crewService.findAllCrewMembers());
        }
    }
}
