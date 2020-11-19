package com.epam.jwd.core_final.domain;

import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    private String name;
    private Role role;
    private Rank rank;
    private boolean isReadyForNextMissions = true;

    CrewMember(String name, Role role, Rank rank) {
        this.name = name;
        this.role = role;
        this.rank = rank;
    }

    @Override
    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "name='" + name + '\'' +
                ", role=" + role +
                ", rank=" + rank +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMember member = (CrewMember) o;
        return isReadyForNextMissions == member.isReadyForNextMissions &&
                name.equals(member.name) &&
                role == member.role &&
                rank == member.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, rank, isReadyForNextMissions);
    }
}
