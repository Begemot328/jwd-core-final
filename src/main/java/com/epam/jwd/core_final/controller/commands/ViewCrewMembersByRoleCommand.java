package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.criteria.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ViewCrewMembersByRoleCommand implements ICommand {
    private Supplier<Short> supplier;
    private Consumer<? super String> action;

    private final String MESSAGE = "View crewmembers by role";
    private final String EMPTY_MESSAGE = "List is empty";

    @Override
    public String getText() {
        return MESSAGE;
    }

    public ViewCrewMembersByRoleCommand(Consumer<? super String> action, Supplier<Short> supplier) {
        this.action = action;
        this.supplier = supplier;
    }


    @Override
    public void execute() {
        CrewServiceImpl crewService = CrewServiceImpl.getInstance();
        List<CrewMember> list;
        Role role = Role.resolveRoleById(supplier.get());

        list = crewService.findAllCrewMembersByCriteria(
                    CrewMemberCriteriaBuilder.newBuild().setRole(role).build());

        if (list != null && !list.isEmpty()) {
            list.stream()
                    .map(member -> member.toString())
                    .forEach(action::accept);
        } else {
            action.accept(EMPTY_MESSAGE);
        }
    }
}
