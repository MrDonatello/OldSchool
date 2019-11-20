package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.request.LoginPasswordDtoRequest;
import net.thumbtack.school.hiring.dto.request.RequirementDtoRequest;
import net.thumbtack.school.hiring.dto.request.TokenDtoRequest;
import net.thumbtack.school.hiring.dto.request.VacancyDtoRequest;
import net.thumbtack.school.hiring.dto.response.VacancyDtoResponse;
import net.thumbtack.school.hiring.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestVacancyService {

    private static Gson gson = new Gson();
    private static Server server = new Server();
    private static String tokenEmployerJson;
    private static String tokenEmployerString;
    private static String tokenEmployeeJson;
    private static String tokenEmployeeString;
    private static Set<Vacancy> vacancySet = new LinkedHashSet<>();
    private static Map<String, Requirements> requirementsHashMap = new HashMap<>();

    @BeforeClass
    public static void testEmployee() {
        server.startServer(null);
        Employer employer = new Employer("firstNameEmployer", "lastNameEmployer", "patronymicEmployer", "emailEmployer", "loginEmployer", "passwordEmployer", "companyName", "address");
        Employee employee = new Employee("firstNameEmployee", "lastNameEmployee", "patronymicEmployee", "emailEmployee", "loginEmployee", "passwordEmployee");

        server.employeeService.registerEmployee(gson.toJson(employee));
        tokenEmployeeJson = server.employeeService.getToken(gson.toJson(new LoginPasswordDtoRequest("loginEmployee", "passwordEmployee")));
        tokenEmployeeString = gson.fromJson(tokenEmployeeJson, TokenDtoRequest.class).getToken();

        server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык JAVA", 4)));
        server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык C#", 4)));

        server.employerService.registerEmployer(gson.toJson(employer));
        tokenEmployerJson = server.employerService.getToken(gson.toJson(new LoginPasswordDtoRequest("loginEmployer", "passwordEmployer")));
        tokenEmployerString = gson.fromJson(tokenEmployerJson, TokenDtoRequest.class).getToken();

        requirementsHashMap.put("язык JAVA", new Requirements("язык JAVA", 4, true));
        requirementsHashMap.put("язык C#", new Requirements("язык C#", 4, true));
        Vacancy vacancy = new Vacancy("JAVA", 30000, requirementsHashMap, tokenEmployerString, true);
        vacancySet.add(vacancy);
        server.vacancyService.addVacancy(gson.toJson(vacancy));
        server.stopServer("3.txt");
    }

    @Before
    public void start() {
        server.startServer("3.txt");
    }

    @After
    public void stop() {
        server.stopServer(null);
    }

    @Test
    public void testAddRemoveVacancy() {
        Vacancy vacancy = new Vacancy("Programmer", 40000, requirementsHashMap, tokenEmployerString, true);
        assertEquals("\"\"", server.vacancyService.addVacancy(gson.toJson(vacancy)));
        assertEquals("\"\"", server.vacancyService.removeVacancy(gson.toJson(vacancy)));
    }

    @Test
    public void testRemoveVacancyBadJobTitle() {
        assertEquals("{\"error\":\"data vacancy error\"}", server.vacancyService.removeVacancy(gson.toJson(new Vacancy("Bad title", 40000, null, tokenEmployerString, true))));
    }

    @Test
    public void testRemoveVacancyBadToken() {
        assertEquals("{\"error\":\"data vacancy error\"}", server.vacancyService.removeVacancy(gson.toJson(new Vacancy("Java", 40000, requirementsHashMap, "Bad token", true))));
    }

    @Test
    public void testAddVacancyNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.vacancyService.addVacancy(gson.toJson(null)));
    }

    @Test
    public void testAddVacancyNullJobTitle() {
        assertEquals("{\"error\":\"job title field is blank\"}", server.vacancyService.addVacancy(gson.toJson(new Vacancy(null, 40000, requirementsHashMap, tokenEmployerString, true))));
    }

    @Test
    public void testAddVacancyBadSalary() {
        assertEquals("{\"error\":\"invalid salary value \"}", server.vacancyService.addVacancy(gson.toJson(new Vacancy("Java", 0, requirementsHashMap, tokenEmployerString, true))));
    }

    @Test
    public void testAddVacancyNullRequirements() {
        assertEquals("{\"error\":\"data vacancy error\"}", server.vacancyService.addVacancy(gson.toJson(new Vacancy("Java", 40000, null, tokenEmployerString, true))));
    }

    @Test
    public void testAddVacancyNullToken() {
        assertEquals("{\"error\":\"token field is blank\"}", server.vacancyService.addVacancy(gson.toJson(new Vacancy("Java", 40000, requirementsHashMap, null, true))));
    }

    @Test
    public void testAddVacancyBadToken() {
        assertEquals("{\"error\":\"data vacancy error\"}", server.vacancyService.addVacancy(gson.toJson(new Vacancy("Java", 40000, requirementsHashMap, "Bad token", true))));
    }

    @Test
    public void testActivateDeactivateVacancy() {
        assertEquals("\"\"", server.vacancyService.activateDeactivateVacancy(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString, false))));
        assertEquals("\"\"", server.vacancyService.activateDeactivateVacancy(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString, true))));
    }

    @Test
    public void testActivateDeactivateVacancyBadJobTitle() {
        assertEquals("{\"error\":\"vacancy is not found\"}", server.vacancyService.activateDeactivateVacancy(gson.toJson(new VacancyDtoRequest("Bad title", 30000, tokenEmployerString, false))));
    }

    @Test
    public void testActivateDeactivateVacancyBadToken() {
        assertEquals("{\"error\":\"vacancy is not found\"}", server.vacancyService.activateDeactivateVacancy(gson.toJson(new VacancyDtoRequest("JAVA", 30000, "Bad token", false))));
    }

    @Test
    public void testActivateDeactivateVacancyNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.vacancyService.activateDeactivateVacancy(gson.toJson(null)));
    }

    @Test
    public void testGetActiveInactiveVacancies() {
        assertEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.getActiveVacancies(tokenEmployerJson));
        server.vacancyService.activateDeactivateVacancy(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString, false)));
        assertEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.getInactiveVacancies(tokenEmployerJson));
    }

    @Test
    public void testGetActiveVacanciesNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.vacancyService.getActiveVacancies(null));
    }

    @Test
    public void testGetActiveVacanciesBadToken() {
        assertEquals("{\"error\":\"vacancy is not found\"}", server.vacancyService.getActiveVacancies(tokenEmployeeJson));
    }

    @Test
    public void testGetInactiveVacanciesNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.vacancyService.getInactiveVacancies(null));
    }

    @Test
    public void testGetInactiveVacanciesBadToken() {
        assertEquals("{\"error\":\"vacancy is not found\"}", server.vacancyService.getInactiveVacancies(tokenEmployeeJson));
    }

    @Test
    public void testGetAllVacancies() {
        assertEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.getAllVacancies(tokenEmployerJson));
    }

    @Test
    public void testGetAllVacanciesNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.vacancyService.getAllVacancies(null));
    }

    @Test
    public void testGetAllVacanciesBadToken() {
        assertEquals("{\"vacancyForResponse\":[]}", server.vacancyService.getAllVacancies(tokenEmployeeJson));
    }

    @Test
    public void testGetAllSuitableVacancies() {
        assertEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.getAllSuitableVacancies(tokenEmployeeJson));
    }

    @Test
    public void testRequirementObligedVacancy() {
        assertEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.requirementObligedVacancy(tokenEmployeeJson));
        server.requirementService.addRequirement(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "SQL", 5, true)));
        assertNotEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.requirementObligedVacancy(tokenEmployeeJson));
    }

    @Test
    public void testGetAllLevelVacancies() {
        server.skillService.editSkillLevel(gson.toJson(new Skill(tokenEmployeeString, "язык C#", 0)));
        assertEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.getAllLevelVacancies(tokenEmployeeJson));
    }

    @Test
    public void testGetVacanciesOneSkill() {
        server.skillService.removeSkill(gson.toJson(new Skill(tokenEmployeeString, "язык C#", 4)));
        assertEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.getVacanciesOneSkill(tokenEmployeeJson));
        server.skillService.removeSkill(gson.toJson(new Skill(tokenEmployeeString, "язык JAVA", 5)));
        assertNotEquals(gson.toJson(new VacancyDtoResponse(vacancySet)), server.vacancyService.getVacanciesOneSkill(tokenEmployeeJson));
    }
}
