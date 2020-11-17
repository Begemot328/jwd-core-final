package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.function.Consumer;

public class ViewAllMissionsCommand implements ICommand {
    private Consumer<? super String> action;

    private final String EMPTY_MESSAGE = "List is empty";
    private final String MESSAGE = "View all missions";

    public ViewAllMissionsCommand(Consumer<? super String> action) {
        this.action = action;
    }

    @Override
    public String getText() {
        return MESSAGE;
    }

    @Override
    public void execute() {
        MissionServiceImpl crewService = MissionServiceImpl.getInstance();
        if (!crewService.findAllMissions().isEmpty()) {
            crewService.findAllMissions().stream()
                    .map(mission -> mission.toString())
                    .forEach(action);
        } else {
            action.accept(EMPTY_MESSAGE);
        }
    }
}