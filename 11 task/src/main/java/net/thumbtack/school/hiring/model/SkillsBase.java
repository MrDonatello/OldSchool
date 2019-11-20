package net.thumbtack.school.hiring.model;


public abstract class SkillsBase {

    private String Name;
    private int level;

    SkillsBase(String skillName, int levelOfSkill) {
        this.Name = skillName;
        this.level = levelOfSkill;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
