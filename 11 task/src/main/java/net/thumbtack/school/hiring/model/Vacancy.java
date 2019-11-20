package net.thumbtack.school.hiring.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Vacancy {

    private String jobTitle;
    private int salary;
    private Map<String,Requirements> requirementsMap;
    private String token;
    private boolean active;

    public Vacancy(String jobTitle, int salary, Map<String,Requirements> requirements, String token, boolean active) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        requirementsMap = requirements;
        this.token = token;
        this.active = active;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public Map<String,Requirements> getRequirements() {
        return requirementsMap;
    }

    public String getToken() {
        return token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return salary == vacancy.salary &&
                Objects.equals(jobTitle, vacancy.jobTitle) &&
                Objects.equals(token, vacancy.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(jobTitle, salary,token);
    }
}
