
package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.request.RequirementDtoRequest;
import net.thumbtack.school.hiring.dto.request.TokenDtoRequest;
import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Requirements;
import net.thumbtack.school.hiring.model.Vacancy;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestRequirementService {

    private static Gson gson = new Gson();
    private static Server server = new Server();
    private static String tokenEmployerString;
    private static Map<String, Requirements> requirementsHashMap = new HashMap<>();

    @BeforeClass

    public static void testEmployee() {
        server.startServer(null);
        Employer employer = new Employer("firstNameEmployer", "lastNameEmployer", "patronymicEmployer", "emailEmployer", "loginEmployer", "passwordEmployer", "companyName", "address");
        tokenEmployerString = gson.fromJson(server.employerService.registerEmployer(gson.toJson(employer)), TokenDtoRequest.class).getToken();

        requirementsHashMap.put("язык JAVA", new Requirements("язык JAVA", 4, true));
        Vacancy vacancy = new Vacancy("JAVA", 30000, requirementsHashMap, tokenEmployerString, true);
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
    public void testAddRemoveRequirement() {
        assertEquals("\"\"", server.requirementService.addRequirement(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "SQL", 5, true))));
        assertEquals("\"\"", server.requirementService.removeRequirement(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "SQL"))));
    }

    @Test
    public void testAddRequirementNullNameRequirement() {
        assertEquals("{\"error\":\"requirements is not found\"}", server.requirementService.addRequirement(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, null, 5, true))));
    }

    @Test
    public void testAddRequirementNullJobTitle() {
        assertEquals("{\"error\":\"vacancy does not exist\"}", server.requirementService.addRequirement(gson.toJson(new RequirementDtoRequest(null, 30000, tokenEmployerString, "SQL", 5, true))));
    }

    @Test
    public void testAddRRequirementNullToken() {
        assertEquals("{\"error\":\"requirement data or vacancy data is not found\"}", server.requirementService.addRequirement(gson.toJson(new RequirementDtoRequest("JAVA", 30000, null, "SQL", 5, true))));
    }

    @Test
    public void testAddRequirementNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.requirementService.addRequirement(gson.toJson(null)));
    }

    @Test
    public void testRemoveRequirementNullData() {
        assertEquals("{\"error\":\"requirement data or vacancy data is not found\"}", server.requirementService.removeRequirement(gson.toJson(new RequirementDtoRequest("JAVA", 30000, null, "SQL"))));
    }

    @Test
    public void testRemoveRequirementNullName() {
        assertEquals("{\"error\":\"requirements is not found\"}", server.requirementService.removeRequirement(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, null))));
    }

    @Test
    public void testEditRequirementLevel() {
        assertEquals("\"\"", server.requirementService.editRequirementLevel(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "язык JAVA", 5))));
    }

    @Test
    public void testEditRequirementLevelNullJobTitle() {
        assertEquals("{\"error\":\"vacancy does not exist\"}", server.requirementService.editRequirementLevel(gson.toJson(new RequirementDtoRequest(null, 30000, tokenEmployerString, "язык JAVA", 5))));
    }

    @Test
    public void testEditRequirementLevelBadRequirementName() {
        assertEquals("{\"error\":\"requirement data or vacancy data is not found\"}", server.requirementService.editRequirementLevel(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "BadName", 5))));
    }

    @Test
    public void testEditRequirementLevelBadJobTile() {
        assertEquals("null", server.requirementService.editRequirementLevel(gson.toJson(new RequirementDtoRequest("BadTitle", 30000, tokenEmployerString, "язык JAVA", 5))));
    }

    @Test
    public void testEditRequirementLevelNullToken() {
        assertEquals("{\"error\":\"requirement data or vacancy data is not found\"}", server.requirementService.editRequirementLevel(gson.toJson(new RequirementDtoRequest("JAVA", 30000, null, "язык JAVA", 5))));
    }

    @Test
    public void testEditRequirementObliged() {
        assertEquals("\"\"", server.requirementService.editRequirementObliged(gson.toJson(new RequirementDtoRequest("JAVA", 30000, tokenEmployerString, "язык JAVA", false))));
    }
}

