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
    Predicate<CrewMember> predicate;

    CrewMemberCriteria() {
        predicate = (figure) -> true;
    }

    @Override
    public Predicate<CrewMember> getPredicate() {
        return predicate;
    }

    public  void setRank(Rank rank) {
        predicate = predicate.and(crewMember -> crewMember.getRank().getId() == rank.getId());
    }

    public  void setRole(Role role) {
        predicate = predicate.and(crewMember -> crewMember.getRole().getId() > role.getId());
    }
}
