package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.request.*;
import net.thumbtack.school.hiring.dto.response.SelectionEmployeesDtoResponse;
import net.thumbtack.school.hiring.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestEmployeeService {

    private static Gson gson = new Gson();
    private static Server server = new Server();
    private static String tokenEmployeeJson;
    private static String tokenEmployeeString;
    private static String tokenEmployerString;
    private static String tokenRegisterEmployee;
    private static List<Employee> employeeList = new LinkedList<>();
    private static Map<String, Requirements> requirementsHashMap = new HashMap<>();
    private Vacancy vacancy = new Vacancy("JAVA", 30000, requirementsHashMap, tokenEmployerString, true);


    @BeforeClass
    public static void testEmployee() {
        Employer employer = new Employer("firstNameEmployer", "lastNameEmployer", "patronymicEmployer", "emailEmployer", "loginEmployer", "passwordEmployer", "companyName", "address");
        Employee employee = new Employee("firstNameEmployee", "lastNameEmployee", "patronymicEmployee", "emailEmployee", "loginEmployee", "passwordEmployee");
        server.startServer(null);

        tokenRegisterEmployee = server.employeeService.registerEmployee(gson.toJson(employee));
        tokenEmployeeJson = server.employeeService.getToken(gson.toJson(new LoginPasswordDtoRequest("loginEmployee", "passwordEmployee")));
        tokenEmployeeString = gson.fromJson(tokenEmployeeJson, TokenDtoRequest.class).getToken();
        employeeList.add(employee);

        server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык JAVA", 5)));
        server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык C#", 4)));


        server.employerService.registerEmployer(gson.toJson(employer));
        String tokenEmployerJson = server.employerService.getToken(gson.toJson(new LoginPasswordDtoRequest("loginEmployer", "passwordEmployer")));
        tokenEmployerString = gson.fromJson(tokenEmployerJson, TokenDtoRequest.class).getToken();
        server.stopServer("1.txt");
    }

    @Before
    public void start() {
        server.startServer("1.txt");
        requirementsHashMap.put("язык JAVA", new Requirements("язык JAVA", 5, true));
        requirementsHashMap.put("язык SQL", new Requirements("язык SQL", 5, true));
        server.vacancyService.addVacancy(gson.toJson(vacancy));
    }

    @After
    public void stop() {
        server.vacancyService.removeVacancy(gson.toJson(vacancy));
        requirementsHashMap.clear();
        server.stopServer("1.txt");
    }

    @Test
    public void testRegisterEmployee() {
        assertEquals(tokenEmployeeJson, tokenRegisterEmployee);
    }

    @Test
    public void testRegisterEmployeeNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.employeeService.registerEmployee(null));

    }

    @Test
    public void testActivateDeactivateProfile() {
        assertEquals("\"\"", server.employeeService.activateDeactivateProfile(gson.toJson(new ProfileDtoRequest(tokenEmployeeString, false))));
        assertEquals("\"\"", server.employeeService.activateDeactivateProfile(gson.toJson(new ProfileDtoRequest(tokenEmployeeString, true))));
    }

    @Test
    public void testActivateDeactivateProfileError() {
        assertEquals("{\"error\":\"token field is blank\"}", server.employeeService.activateDeactivateProfile(gson.toJson(new ProfileDtoRequest(null, false))));
    }

    @Test
    public void testEmployeesWithAllSkills() {
        server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык SQL", 5)));
        assertEquals(gson.toJson(new SelectionEmployeesDtoResponse(employeeList)), server.employeeService.employeesWithAllSkills(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString))));
        server.requirementService.editRequirementLevel(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "язык JAVA", 6)));
        assertNotEquals(gson.toJson(new SelectionEmployeesDtoResponse(employeeList)), server.employeeService.employeesWithAllSkills(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString))));
        server.skillService.removeSkill(gson.toJson(new Skill(tokenEmployeeString, "язык SQL", 5)));
    }

    @Test
    public void testEmployeesWithAllSkillsBadTitle() {
        assertEquals("{\"error\":\"vacancy does not exist\"}", server.employeeService.employeesWithAllSkills(gson.toJson(new VacancyDtoRequest("BadTitle", 30000, tokenEmployerString))));
    }

    @Test
    public void testEmployeesWithAllSkillsNullToken() {
        assertEquals("{\"error\":\"token field is blank\"}", server.employeeService.employeesWithAllSkills(gson.toJson(new VacancyDtoRequest("JAVA", 30000, null))));
    }

    @Test
    public void testEmployeesWithAllSkillsBadSalaryValue() {
        assertEquals("{\"error\":\"invalid salary value \"}", server.employeeService.employeesWithAllSkills(gson.toJson(new VacancyDtoRequest("JAVA", -1, tokenEmployerString))));
    }

    @Test
    public void testEmployeesWithAllSkillsBadToken() {
        assertEquals("{\"error\":\"vacancy does not exist\"}", server.employeeService.employeesWithAllSkills(gson.toJson(new VacancyDtoRequest("JAVA", 30000, "12345"))));
    }

    @Test
    public void testEmployeesWithAllSkillsNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.employeeService.employeesWithAllSkills(null));
    }

    @Test
    public void testEmployeesWithObligedSkills() {
        server.requirementService.editRequirementObliged(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "язык SQL", false)));
        assertEquals(gson.toJson(new SelectionEmployeesDtoResponse(employeeList)), server.employeeService.employeesWithObligedSkills(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString))));
        server.requirementService.editRequirementObliged(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "язык SQL", true)));
        assertNotEquals(gson.toJson(new SelectionEmployeesDtoResponse(employeeList)), server.employeeService.employeesWithObligedSkills(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString))));
    }

    @Test
    public void testEmployeesWithSkillsAllLevel() {
        assertNotEquals(gson.toJson(new SelectionEmployeesDtoResponse(employeeList)), server.employeeService.employeesWithSkillsAllLevel(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString))));
        server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык SQL", 1)));
        assertEquals(gson.toJson(new SelectionEmployeesDtoResponse(employeeList)), server.employeeService.employeesWithSkillsAllLevel(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString))));
        server.skillService.removeSkill(gson.toJson(new Skill(tokenEmployeeString, "язык SQL", 1)));
    }

    @Test
    public void testEmployeesWithOneSkill() {
        assertEquals(gson.toJson(new SelectionEmployeesDtoResponse(employeeList)), server.employeeService.employeesWithOneSkill(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString))));
    }
}
