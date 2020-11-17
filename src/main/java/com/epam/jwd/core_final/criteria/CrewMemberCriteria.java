package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.function.Predicate;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private final String READY= "ready for next mission; ";
    private final String NOT_READY= "not ready for next mission; ";
    private Predicate<CrewMember> predicate;
    private String description;

    CrewMemberCriteria() {
        predicate = (figure) -> true;
        description = new String();
    }

    @Override
    public Predicate<CrewMember> getPredicate() {
        return predicate;
    }

    public  void setRank(Rank rank) {
        predicate = predicate.and(crewMember -> crewMember.getRank().getId() == rank.getId());
        description += "Rank=" + rank + "; ";
    }

    public  void setRole(Role role) {
        predicate = predicate.and(crewMember -> crewMember.getRole().getId() == role.getId());
        description += "Role=" + role + "; ";
    }

    public  void setReady(boolean ready) {
        predicate = predicate.and(crewMember -> crewMember.isReadyForNextMissions() == ready);
        description += (ready ? READY : NOT_READY);
    }

    @Override
    public String toString() {
        return "CrewMemberCriteria {" +
                "predicate= ( " + description +
                ")}";
    }
}
