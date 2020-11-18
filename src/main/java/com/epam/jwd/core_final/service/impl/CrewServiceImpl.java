package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.ObjectCreationException;
import com.epam.jwd.core_final.domain.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CrewServiceImpl implements CrewService {
    private ApplicationContext context = NassaContext.getInstance();

    private final static CrewServiceImpl INSTANCE = new CrewServiceImpl();

    public static CrewServiceImpl getInstance() {
        return INSTANCE;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return (List<CrewMember>) context.retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> list = findAllCrewMembers();

        return  Arrays.asList(list.stream()
                .filter((Predicate<CrewMember>) criteria.getPredicate())
                .toArray(CrewMember[]::new));
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> list = findAllCrewMembers();

        return  (list.stream()
                .filter((Predicate<CrewMember>) criteria.getPredicate())
                .findAny());
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        List<CrewMember> list = findAllCrewMembers();
        Optional<CrewMember> optional = list.stream()
                .filter(member -> member.getName().equals(crewMember.getName()))
                .findAny();
        if (optional.isPresent()) {
            list.remove(optional.get());
            list.add(crewMember);
        } else {
            return null;
        }
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        crewMember.setReadyForNextMissions(false);
        updateCrewMemberDetails(crewMember);
    }

    @Override
    public CrewMember createCrewMember(Object... args) throws RuntimeException {
        CrewMemberFactory factory = new CrewMemberFactory();
        CrewMember member = factory.create(args);
        List<CrewMember> list = findAllCrewMembers();

        if (list.stream()
                .filter(crewMember -> member.getName().equals(crewMember.getName()))
                .findAny().isPresent()) {
            throw new ObjectCreationException(member);
        }
        list.add(member);
        member.setId(context.getId());
        return member;
    }
}
