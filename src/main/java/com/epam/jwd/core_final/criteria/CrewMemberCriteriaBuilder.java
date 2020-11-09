package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * {@link Criteria}  Builder class
 *
 * @author Yury Zmushko
 * @version 1.0
 */
public class CrewMemberCriteriaBuilder {

    private CrewMemberCriteria criteria;

    /**
     * Constructor is hidden
     */
    private CrewMemberCriteriaBuilder() {}


    public static CrewMemberCriteriaBuilder newBuild() {
        CrewMemberCriteriaBuilder builder = new CrewMemberCriteriaBuilder();
        builder.criteria = new CrewMemberCriteria();

        return builder;
    }

    public CrewMemberCriteriaBuilder setRank(Rank rank) {
        criteria.setRank(rank);
        return this;
    }

    public CrewMemberCriteriaBuilder setRole(Role role) {
        criteria.setRole(role);
        return this;
    }

    public Criteria build() {
        return criteria;
    }

    public CrewMemberCriteriaBuilder setReady(boolean b) {
        criteria.setReady(b);
        return this;
    }
}
