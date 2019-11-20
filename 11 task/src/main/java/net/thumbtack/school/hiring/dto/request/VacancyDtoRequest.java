package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.model.Requirements;
import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

import java.util.List;
import java.util.Map;

public class VacancyDtoRequest {

    private String jobTitle;
    private int salary;
    private Map<String, Requirements> requirementsMap;
    private String token;
    private boolean active;
    private String employeeLogin;

    public VacancyDtoRequest(String jobTitle, int salary, String token, String employeeLogin) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.token = token;
        this.employeeLogin = employeeLogin;
    }

    public VacancyDtoRequest(String jobTitle, int salary, Map<String, Requirements> requirements, String token, boolean active) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.requirementsMap = requirements;
        this.token = token;
        this.active = active;
    }

    public VacancyDtoRequest(String jobTitle, int salary, String token) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.token = token;
    }

    public VacancyDtoRequest(String jobTitle, int salary, String token, boolean active) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.token = token;
        this.active = active;
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmployeeLogin() {
        return employeeLogin;
    }

    public void setEmployeeLogin(String employeeLogin) {
        this.employeeLogin = employeeLogin;
    }

    public void validate() throws ServiceException {

        if (jobTitle == null) {
            throw new ServiceException(ErrorCode.NULL_JOB_TITLE_REQUEST);
        }
        if (salary <= 0) {
            throw new ServiceException(ErrorCode.WRONG_SALARY_REQUEST);
        }
        if (token == null) {
            throw new ServiceException(ErrorCode.NULL_TOKEN_REQUEST);
        }
    }
}
