package net.thumbtack.school.hiring.database;

import net.thumbtack.school.hiring.model.*;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class DataBase implements Serializable {

    private Map<String, Employee> employeeHashMap = new HashMap<>();
    private Map<String, Employer> employerHashMap = new HashMap<>();
    private Set<String> UsersOnServer = new HashSet<>();
    private Set<String> loginField = new HashSet<>();
    private Map<String, Set<Vacancy>> vacancyMap = new HashMap<>();
    private Map<String, Set<Skill>> skillMap = new HashMap<>();
    private Set<String> inactiveProfiles = new HashSet<>();


    //////////////////////////////////////////////Vacancy///////////////////////////////////////////////////////////////

    public String insertVacancy(Vacancy vacancy) throws ServiceException {

        String result = null;
        try {
            Set<Vacancy> vacancySet = new LinkedHashSet<>();
            vacancy.getRequirements().keySet().forEach(key -> skillMap.putIfAbsent(key, new LinkedHashSet<>()));
            if (employerHashMap.containsKey(vacancy.getToken())) {
                if (vacancyMap.containsKey(vacancy.getToken())) {
                    vacancyMap.get(vacancy.getToken()).add(vacancy);
                    result = "";
                } else {
                    vacancySet.add(vacancy);
                    vacancyMap.put(vacancy.getToken(), vacancySet);
                    result = "";
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ADD_VACANCY_ERROR);
        }
        return result;
    }

    public String removeVacancy(Vacancy vacancy) throws ServiceException {

        String result = null;
        try {
            if (vacancyMap.get(vacancy.getToken()).remove(vacancy)) {
                result = "";
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.REMOVE_VACANCY_ERROR);
        }
        return result;
    }

    public String activateDeactivateVacancy(String jobTitle, int salary, String token, boolean activeInactive) throws ServiceException {

        String result = null;
        try {
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (vacancy.getJobTitle().equals(jobTitle) && vacancy.getSalary() == salary) {
                    vacancy.setActive(activeInactive);
                    result = "";
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ACTIVE_INACTIVE_VACANCY_ERROR);
        }
        return result;
    }

    public Set<Vacancy> getAllVacancies(String token) throws ServiceException {

        Set<Vacancy> result;
        try {
            result = vacancyMap.get(token);
            if (result == null) {
                result = new HashSet<>();
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.GET_VACANCY_ERROR);
        }
        return result;
    }

    public Set<Vacancy> getActiveVacancies(String token) throws ServiceException {

        Set<Vacancy> result = new LinkedHashSet<>();
        try {
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (vacancy.isActive()) {
                    result.add(vacancy);
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ACTIVE_INACTIVE_VACANCY_ERROR);
        }

        return result;
    }

    public Set<Vacancy> getInactiveVacancies(String token) throws ServiceException {

        Set<Vacancy> result = new LinkedHashSet<>();
        try {
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (!vacancy.isActive()) {
                    result.add(vacancy);
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ACTIVE_INACTIVE_VACANCY_ERROR);
        }
        return result;
    }

    ///////////////////////////////////////////////requestEmployer//////////////////////////////////////////////////////

    private Vacancy searchVacancy(String jobTitle, int salary, String token) throws ServiceException {

        Vacancy resultVacancy = null;

        try {
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (vacancy.getJobTitle().equals(jobTitle) && vacancy.getSalary() == salary) {
                    resultVacancy = vacancy;
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.NULL_VACANCY);
        }
        return resultVacancy;
    }

    public List<Employee> employeesWithAllSkills(String jobTitle, int salary, String token) throws ServiceException {

        List<Employee> employeeList = new LinkedList<>();
        Map<String, Integer> temporaryMap = new HashMap<>();
        Vacancy vacancy = searchVacancy(jobTitle, salary, token);

        try {
            vacancy.getRequirements().forEach((key, requirement) -> skillMap.get(key).forEach(skill -> {
                if (skill.getLevel() >= requirement.getLevel() && !inactiveProfiles.contains(skill.getToken())) {
                    temporaryMap.merge(skill.getToken(), 1, (a, b) -> (a + b));
                    if (temporaryMap.get(skill.getToken()) == vacancy.getRequirements().size()) {
                        employeeList.add(employeeHashMap.get(skill.getToken()));
                    }
                }
            }));
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.NULL_VACANCY);
        }
        return employeeList;
    }

    public List<Employee> employeesWithObligedSkills(String jobTitle, int salary, String token) throws ServiceException {

        List<Employee> employeeList = new LinkedList<>();
        Map<String, Integer> temporaryMap = new HashMap<>();
        Vacancy vacancy = searchVacancy(jobTitle, salary, token);

        if (obligedVacancy(vacancy.getRequirements()) > 0) {
            vacancy.getRequirements().forEach((key, requirement) -> skillMap.get(key).forEach(skill -> {
                if (requirement.requirementsIsObliged()) {
                    if (skill.getLevel() >= requirement.getLevel() && !inactiveProfiles.contains(skill.getToken())) {
                        temporaryMap.merge(skill.getToken(), 1, (a, b) -> (a + b));
                    }
                }
                if (temporaryMap.get(skill.getToken()) == obligedVacancy(vacancy.getRequirements())) {
                    employeeList.add(employeeHashMap.get(skill.getToken()));
                }
            }));
        } else {
            employeeList.addAll(employeeHashMap.values());
        }
        return employeeList;
    }

    public List<Employee> employeesWithSkillsAllLevel(String jobTitle, int salary, String token) throws ServiceException {

        List<Employee> employeeList = new LinkedList<>();
        Map<String, Integer> temporaryMap = new HashMap<>();
        Vacancy vacancy = searchVacancy(jobTitle, salary, token);

        vacancy.getRequirements().forEach((key, requirement) -> skillMap.get(key).forEach(skill -> {
            if (!inactiveProfiles.contains(skill.getToken())) {
                temporaryMap.merge(skill.getToken(), 1, (a, b) -> (a + b));
                if (temporaryMap.get(skill.getToken()) == vacancy.getRequirements().size()) {
                    employeeList.add(employeeHashMap.get(skill.getToken()));
                }
            }
        }));
        return employeeList;
    }

    public List<Employee> employeesWithOneSkill(String jobTitle, int salary, String token) throws ServiceException {

        Set<Employee> employeeSet = new HashSet<>();
        Vacancy vacancy = searchVacancy(jobTitle, salary, token);

        vacancy.getRequirements().forEach((key, requirement) -> skillMap.get(key).forEach(skill -> {
            if (skill.getLevel() >= requirement.getLevel() && !inactiveProfiles.contains(skill.getToken())) {
                employeeSet.add(employeeHashMap.get(skill.getToken()));
            }
        }));
        return new LinkedList<>(employeeSet);
    }

    private int obligedVacancy(Map<String, Requirements> requirementsMap) {

        int obliged = 0;
        for (Requirements requirements : requirementsMap.values()) {
            if (requirements.requirementsIsObliged()) {
                obliged++;
            }
        }
        return obliged;
    }

    public String hire(String jobTitle, int salary, String token, String employeeLogin) throws ServiceException {

        String result = null;
        try {
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (vacancy.getJobTitle().equals(jobTitle) && vacancy.getSalary() == salary) {
                    vacancy.setActive(false);
                    for (Employee employee : employeeHashMap.values()) {
                        if (employee.getLogin().equals(employeeLogin)) {
                            inactiveProfiles.add(employee.getToken());
                            result = "";
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.HIRE_ERROR);
        }
        return result;
    }

    //////////////////////////////////////////////requestEmployee///////////////////////////////////////////////////////

    public Set<Vacancy> getAllSuitableVacancies(String token) {

        Map<Vacancy, Integer> temporaryMap = new HashMap<>();
        Set<Vacancy> vacancySetResponse = new LinkedHashSet<>();
        List<Skill> skillList = searchSkill(token);

        vacancyMap.values().forEach(vacanciesSet -> vacanciesSet.forEach(vacancy -> {
            skillList.forEach(skill -> {
                if (vacancy.getRequirements().get(skill.getName()) != null && vacancy.isActive() &&
                        skill.getLevel() >= vacancy.getRequirements().get(skill.getName()).getLevel()) {
                    temporaryMap.merge(vacancy, 1, (a, b) -> (a + b));
                    if (temporaryMap.get(vacancy) == vacancy.getRequirements().size()) {
                        vacancySetResponse.add(vacancy);
                    }
                }
            });
        }));
        return vacancySetResponse;
    }

    public Set<Vacancy> RequirementObligedVacancy(String token) {

        Map<Vacancy, Integer> temporaryMap = new HashMap<>();
        Set<Vacancy> vacancySetResponse = new LinkedHashSet<>();
        List<Skill> skillList = searchSkill(token);

        vacancyMap.values().forEach(vacanciesSet -> vacanciesSet.forEach(vacancy -> {
            skillList.forEach(skill -> {
                if (vacancy.getRequirements().get(skill.getName()) != null && vacancy.isActive()) {
                    if (vacancy.getRequirements().get(skill.getName()).requirementsIsObliged()) {
                        if (skill.getLevel() >= vacancy.getRequirements().get(skill.getName()).getLevel()) {
                            temporaryMap.merge(vacancy, 1, (a, b) -> (a + b));
                        }
                    } else {
                        temporaryMap.merge(vacancy, 1, (a, b) -> (a + b));
                    }
                    if (temporaryMap.get(vacancy) == vacancy.getRequirements().size()) {
                        vacancySetResponse.add(vacancy);
                    }
                }
            });
        }));
        return vacancySetResponse;
    }

    public Set<Vacancy> getAllLevelVacancies(String token) {

        Map<Vacancy, Integer> temporaryMap = new HashMap<>();
        Set<Vacancy> vacancySetResponse = new LinkedHashSet<>();
        List<Skill> skillList = searchSkill(token);

        vacancyMap.values().forEach(vacanciesSet -> vacanciesSet.forEach(vacancy -> {
            skillList.forEach(skill -> {
                if (vacancy.getRequirements().get(skill.getName()) != null && vacancy.isActive()) {
                    temporaryMap.merge(vacancy, 1, (a, b) -> (a + b));
                    if (temporaryMap.get(vacancy) == vacancy.getRequirements().size()) {
                        vacancySetResponse.add(vacancy);
                    }
                }
            });
        }));
        return vacancySetResponse;
    }

    public Set<Vacancy> getVacanciesOneSkill(String token) {

        Map<Vacancy, Integer> temporaryMap = new HashMap<>();
        Set<Vacancy> vacancySetResponse = new LinkedHashSet<>();
        List<Skill> skillList = searchSkill(token);

        vacancyMap.values().forEach(vacanciesSet -> vacanciesSet.forEach(vacancy -> {
            skillList.forEach(skill -> {
                if (vacancy.getRequirements().get(skill.getName()) != null && vacancy.isActive()) {
                    if (skill.getLevel() >= vacancy.getRequirements().get(skill.getName()).getLevel()) {
                        temporaryMap.merge(vacancy, 1, (a, b) -> (a + b));
                    }
                }
            });
        }));


        temporaryMap.entrySet().stream().sorted(Map.Entry.<Vacancy, Integer>comparingByValue().reversed()).forEach(e -> vacancySetResponse.add(e.getKey()));
        return vacancySetResponse;
    }

    private List<Skill> searchSkill(String token) {

        List<Skill> skillList = new LinkedList<>();
        for (Set<Skill> set : skillMap.values()) {
            for (Skill skill : set) {
                if (skill.getToken().equals(token)) {
                    skillList.add(skill);
                }
            }
        }
        return skillList;
    }

    ////////////////////////////////////////////Requirements////////////////////////////////////////////////////////////

    public String addRequirement(String jobTitle, int salary, String token, Requirements requirement) throws ServiceException {

        String result = null;
        try {
            skillMap.putIfAbsent(requirement.getName(), new LinkedHashSet<>());
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (vacancy.getJobTitle().equals(jobTitle) && vacancy.getSalary() == salary) {
                    vacancy.getRequirements().put(requirement.getName(), requirement);
                    result = "";
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ERROR_DATA_REQUIREMENT);
        }
        return result;
    }

    public String removeRequirement(String jobTitle, int salary, String token, String requirementName) throws ServiceException {

        String result = null;
        try {
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (vacancy.getJobTitle().equals(jobTitle) && vacancy.getSalary() == salary) {
                    if (vacancy.getRequirements().remove(requirementName, vacancy.getRequirements().get(requirementName))) {
                        result = "";
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ERROR_DATA_REQUIREMENT);
        }
        return result;
    }

    public String editRequirementLevel(String jobTitle, int salary, String token, String requirementName, int newRequirementsLevel) throws ServiceException {

        String result = null;
        try {
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (vacancy.getJobTitle().equals(jobTitle) && vacancy.getSalary() == salary) {
                    vacancy.getRequirements().get(requirementName).setLevel(newRequirementsLevel);
                    result = "";
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ERROR_DATA_REQUIREMENT);
        }
        return result;
    }

    public String editRequirementObliged(String jobTitle, int salary, String token, String requirementName, boolean newObliged) throws ServiceException {

        String result = null;
        try {
            for (Vacancy vacancy : vacancyMap.get(token)) {
                if (vacancy.getJobTitle().equals(jobTitle) && vacancy.getSalary() == salary) {
                    vacancy.getRequirements().get(requirementName).setRequirementsIsObliged(newObliged);
                    result = "";
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.EDIT_REQUIREMENT_ERROR);
        }
        return result;
    }

    ///////////////////////////////////////////////Skill////////////////////////////////////////////////////////////////

    public String addSkill(Skill skill) throws ServiceException {

        String result = null;
        skillMap.putIfAbsent(skill.getName(), new LinkedHashSet<>());
        if (employeeHashMap.containsKey(skill.getToken()) && skillMap.get(skill.getName()).add(skill)) {
            result = "";
        }
        if (result == null) {
            throw new ServiceException(ErrorCode.ADD_SKILL_ERROR);
        }
        return result;
    }

    public String removeSkill(Skill skillRemove) throws ServiceException {

        String result = null;
        try {
            if (employeeHashMap.get(skillRemove.getToken()).getToken().equals(skillRemove.getToken())) {//
                skillMap.get(skillRemove.getName()).remove(skillRemove);
                result = "";
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.REMOVE_SKILL_ERROR);
        }
        return result;
    }

    public String editSkillLevel(Skill skillEdit) throws ServiceException {

        String result = null;
        try {
            for (Skill skill : skillMap.get(skillEdit.getName())) {
                if (skill.getToken().equals(skillEdit.getToken())) {
                    skill.setLevel(skillEdit.getLevel());
                    result = "";
                    break;
                }
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.EDIT_SKILL_ERROR);
        }
        return result;
    }

    ///////////////////////////////////////////////Registration/////////////////////////////////////////////////////////

    public String insertEmployee(Employee employee) {

        UUID uuid = UUID.randomUUID();
        employee.setToken(uuid.toString());
        employeeHashMap.put(uuid.toString(), employee);
        UsersOnServer.add(uuid.toString());
        loginField.add(employee.getLogin());
        return uuid.toString();
    }

    public String insertEmployer(Employer employer) {

        UUID uuid = UUID.randomUUID();
        employer.setToken(uuid.toString());
        employerHashMap.put(uuid.toString(), employer);
        UsersOnServer.add(uuid.toString());
        loginField.add(employer.getLogin());
        return uuid.toString();
    }

    public String EditDataEmployee(Employee updatedEmployee) {

        employeeHashMap.put(updatedEmployee.getToken(), updatedEmployee);
        return "";
    }

    public String EditDataEmployer(Employer updatedEmployer) throws ServiceException {

        try {
            employerHashMap.put(updatedEmployer.getToken(), updatedEmployer);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.EDIT_DATA_EMPLOYER_ERROR);
        }
        return "";
    }
    ///////////////////////////////////////////////////loginLogout/////////////////////////////////////////////////////

    public String leaveServer(String token) throws ServiceException {

        String result = null;
        if (UsersOnServer.contains(token)) {
            UsersOnServer.remove(token);
            result = "";
        }
        if (employeeHashMap.remove(token, employeeHashMap.get(token)) || employerHashMap.remove(token, employerHashMap.get(token))) {//?
            vacancyMap.remove(token);
            loginField.remove(token);
        }
        if (result == null) {
            throw new ServiceException(ErrorCode.USER_ERROR);
        }
        return result;
    }

    public String logInServer(String login, String password) throws ServiceException {

        String result = null;
        if (UsersOnServer.add(getToken(login, password))) {
            result = "";
        }
        if (result == null) {
            throw new ServiceException(ErrorCode.USER_ERROR);
        }
        return result;
    }

    public String logOutServer(String token) throws ServiceException {

        String result = null;
        if (UsersOnServer.contains(token)) {
            UsersOnServer.remove(token);
            result = "";
        }
        if (result == null) {
            throw new ServiceException(ErrorCode.USER_ERROR);
        }
        return result;
    }

    //////////////////////////////////////////////Token/////////////////////////////////////////////////////////////////

    public String getToken(String login, String password) throws ServiceException {

        String result = null;

        for (Employee employee : employeeHashMap.values()) {
            if (employee.getLogin().equals(login) && employee.passwordCompare(password)) {
                result = employee.getToken();
            }
        }

        for (Employer employer : employerHashMap.values()) {
            if (employer.getLogin().equals(login) && employer.passwordCompare(password)) {
                result = employer.getToken();
            }
        }

        if (result == null) {
            throw new ServiceException(ErrorCode.USER_ERROR);
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String activateDeactivateProfile(String token, boolean active) {

        if (active) {
            inactiveProfiles.remove(token);
        }

        if ((!active)) {
            inactiveProfiles.add(token);
        }
        return "";
    }

    public boolean searchLogin(String login) {
        return loginField.contains(login);
    }

    public Set<String> getSkillsRequirements() {
        return skillMap.keySet();
    }
}

