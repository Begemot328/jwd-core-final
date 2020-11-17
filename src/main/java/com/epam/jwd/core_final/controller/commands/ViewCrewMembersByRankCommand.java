package com.epam.jwd.core_final.controller.commands;

import com.epam.jwd.core_final.controller.ICommand;
import com.epam.jwd.core_final.criteria.CrewMemberCriteriaBuilder;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ViewCrewMembersByRankCommand implements ICommand {
    private Supplier<Short> supplier;
    private Consumer<? super String> action;


    private final String UNKNOWN_RANK_MESSAGE = "Unknown rank";
    private final String MESSAGE = "View crewmembers by rank";
    private final String EMPTY_MESSAGE = "List is empty";
    private final String NEW_LINE = "\n";

    @Override
    public String getText() {
        return MESSAGE;
    }

    public ViewCrewMembersByRankCommand(Consumer<? super String> action, Supplier<Short> supplier) {
        this.action = action;
        this.supplier = supplier;
    }


    @Override
    public void execute() {
        CrewServiceImpl crewService = CrewServiceImpl.getInstance();
        List<CrewMember> list;
        Rank rank = null;

        while (true) {
            try {
                rank = Rank.resolveRankById(supplier.get());
                break;
            } catch (RuntimeException e) {
                System.out.println(UNKNOWN_RANK_MESSAGE);
            }
        }

        list = crewService.findAllCrewMembersByCriteria(
                CrewMemberCriteriaBuilder.newBuild().setRank(rank).build());

        if (list != null && !list.isEmpty()) {
            AtomicInteger i = new AtomicInteger();
            list.stream()
                    .map(member -> i.incrementAndGet() + NEW_LINE +member.toString())
                    .forEach(action::accept);
        } else {
            action.accept(EMPTY_MESSAGE);
        }
    }
}
