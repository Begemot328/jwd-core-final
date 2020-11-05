package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    private String name = "CrewMember";
    private String nameMember;
    private Role role;
    private Rank rank;
    private boolean isReadyForNextMissions = true;

    public CrewMember(String name, Role role, Rank rank) {
        this.nameMember = nameMember;
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
}
