package net.thumbtack.school.hiring.model;


import java.util.Objects;

public class Skill extends SkillsBase {

    private String token;


    public Skill(String token, String skillName, int levelOfSkill) {

        super(skillName, levelOfSkill);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(token, skill.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(token);
    }
}
