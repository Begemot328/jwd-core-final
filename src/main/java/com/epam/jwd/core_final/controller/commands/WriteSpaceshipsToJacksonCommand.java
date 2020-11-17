package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceShipServiceImpl;
import com.epam.jwd.core_final.util.CollectionJSONWriter;

import java.util.Collection;

public class WriteSpaceshipsToJacksonCommand implements ICommand {
    private final String MESSAGE = "Write all spaceships to JSON";

    @Override
    public String getText() {
        return MESSAGE;
    }

    @Override
    public void execute() throws InvalidStateException {
        SpaceShipServiceImpl missionService = SpaceShipServiceImpl.getInstance();
        if (!missionService.findAllSpaceships().isEmpty()) {
            CollectionJSONWriter.write((Collection)  missionService.findAllSpaceships());
        }
    }
}
