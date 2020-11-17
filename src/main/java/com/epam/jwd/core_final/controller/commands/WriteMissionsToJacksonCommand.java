package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.util.CollectionJSONWriter;

import java.util.Collection;

public class WriteMissionsToJacksonCommand implements ICommand {
    private final String MESSAGE = "Write all misssions to JSON";

    @Override
    public String getText() {
        return MESSAGE;
    }

    @Override
    public void execute() throws InvalidStateException {
        MissionServiceImpl missionService = MissionServiceImpl.getInstance();
        if (!missionService.findAllMissions().isEmpty()) {
            CollectionJSONWriter.write((Collection)  missionService.findAllMissions());
        }
    }
}
