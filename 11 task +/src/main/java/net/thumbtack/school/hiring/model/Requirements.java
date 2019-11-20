package net.thumbtack.school.hiring.model;


public class Requirements extends SkillsBase{


    private boolean requirementsIsObliged;

    public Requirements(String requirementsName, int levelOfRequirements, boolean requirementsIsObliged) {
        super(requirementsName,levelOfRequirements);
        this.requirementsIsObliged = requirementsIsObliged;
    }

    public boolean requirementsIsObliged() {
        return requirementsIsObliged;
    }

    public void setRequirementsIsObliged(boolean requirementsIsObliged) {
        this.requirementsIsObliged = requirementsIsObliged;
    }
}
