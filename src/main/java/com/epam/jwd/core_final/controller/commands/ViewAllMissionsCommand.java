package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class ViewAllMissionsCommand implements ICommand {
    private Consumer<? super String> action;

    private final String EMPTY_MESSAGE = "List is empty";
    private final String MESSAGE = "View all missions";
    private final String SPACE = " ";
    private final String NEW_LINE = "\n";

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
            AtomicInteger i = new AtomicInteger();
            crewService.findAllMissions().stream()
                    .map((mission) -> (i.incrementAndGet() + NEW_LINE + mission.toString()))
                    .forEach(action);
        } else {
            action.accept(EMPTY_MESSAGE);
        }
    }
}