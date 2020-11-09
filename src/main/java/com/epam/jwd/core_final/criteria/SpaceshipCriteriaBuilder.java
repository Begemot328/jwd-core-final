package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * {@link Criteria}  Builder class
 *
 * @author Yury Zmushko
 * @version 1.0
 */
public class SpaceshipCriteriaBuilder {

    private SpaceshipCriteria criteria;

    /**
     * Constructor is hidden
     */
    private SpaceshipCriteriaBuilder() {}


    public static SpaceshipCriteriaBuilder newBuild() {
        SpaceshipCriteriaBuilder builder = new SpaceshipCriteriaBuilder();
        builder.criteria = new SpaceshipCriteria();

        return builder;
    }

    public SpaceshipCriteriaBuilder setMinimalDistance(long distance) {
        criteria.setMinimalDistance(distance);
        return this;
    }

    public SpaceshipCriteriaBuilder setReady(boolean ready) {
        criteria.setReady(ready);
        return this;
    }


    public Criteria build() {
        return criteria;
    }
}
