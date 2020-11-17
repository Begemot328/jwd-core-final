package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.util.CollectionJSONWriter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class ViewAllCrewMemebersCommand implements ICommand {

    private final String MESSAGE = "View all crewmembers";
    private final String EMPTY_MESSAGE = "List is empty";
    private final String NEW_LINE = "\n";

    private Consumer<? super String> action;

    public ViewAllCrewMemebersCommand(Consumer<? super String> action) {
        this.action = action;
    }

    @Override
    public String getText() {
        return MESSAGE;
    }

    @Override
    public void execute() {
        CrewServiceImpl crewService = CrewServiceImpl.getInstance();

        if (!crewService.findAllCrewMembers().isEmpty()) {
            AtomicInteger i = new AtomicInteger();

            crewService.findAllCrewMembers().stream()
                    .map(member -> i.incrementAndGet() + NEW_LINE +member.toString())
                    .forEach(action::accept);
        } else {
            action.accept(EMPTY_MESSAGE);
        }
    }
}
