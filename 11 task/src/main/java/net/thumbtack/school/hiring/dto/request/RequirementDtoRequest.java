package net.thumbtack.school.hiring.dto.request;


import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

public class RequirementDtoRequest {

    private String jobTitle;
    private int salary;
    private String token;
    private String Name;
    private String newRequirementName;
    private int level;
    private boolean obliged;

    public RequirementDtoRequest(String jobTitle, int salary, String token, String requirementName, int level, boolean obliged) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.token = token;
        this.Name = requirementName;
        this.level = level;
        this.obliged = obliged;
    }

    public RequirementDtoRequest(String jobTitle, int salary, String token, String requirementName, int newRequirementsLevel) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.token = token;
        this.Name = requirementName;
        this.level = newRequirementsLevel;
    }

    public RequirementDtoRequest(String jobTitle, int salary, String token, String requirementName, boolean newObliged) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.token = token;
        this.Name = requirementName;
        this.obliged = newObliged;
    }

    public RequirementDtoRequest(String jobTitle, int salary, String token, String requirementName, String newRequirementName) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.token = token;
        this.Name = requirementName;
        this.newRequirementName = newRequirementName;
    }

    public RequirementDtoRequest(String jobTitle, int salary, String token, String requirementName) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.token = token;
        this.Name = requirementName;
    }

    public String getNewRequirementName() {
        return newRequirementName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public String getToken() {
        return token;
    }

    public String getRequirementName() {
        return Name;
    }

    public int getLevel() {
        return level;
    }

    public boolean isObliged() {
        return obliged;
    }

    public void validate() throws ServiceException {

        if (getJobTitle() == null) {
            throw new ServiceException(ErrorCode.NULL_VACANCY);
        }

    }
}
