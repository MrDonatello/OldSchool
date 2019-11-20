package net.thumbtack.school.hiring.model;

import java.util.List;
import java.util.Map;

public class VacancyForResponse {
    private String jobTitle;
    private int salary;
    private Map<String,Requirements> requirements;

    public VacancyForResponse(String jobTitle, int salary, Map<String,Requirements> requirements) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.requirements = requirements;
    }
}
