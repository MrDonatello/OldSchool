
package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.request.TokenDtoRequest;
import net.thumbtack.school.hiring.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSkillService {

    private static Gson gson = new Gson();
    private static Server server = new Server();
    private static String tokenEmployeeString;

    @BeforeClass
    public static void testEmployee() {
        server.startServer(null);
        Employee employee = new Employee("firstNameEmployee", "lastNameEmployee", "patronymicEmployee", "emailEmployee", "loginEmployee", "passwordEmployee");
        tokenEmployeeString = gson.fromJson(server.employeeService.registerEmployee(gson.toJson(employee)), TokenDtoRequest.class).getToken();
        server.stopServer("1.txt");
    }

    @Before
    public void start() {
        server.startServer("1.txt");
    }

    @After
    public void stop() {
        server.stopServer(null);
    }

    @Test
    public void testAddRemoveSkill() {
        Skill skill = new Skill(tokenEmployeeString, "язык C++", 4);
        assertEquals("\"\"", server.skillService.addSkill(gson.toJson(skill)));
        assertEquals("\"\"", server.skillService.removeSkill(gson.toJson(skill)));
    }

    @Test
    public void testAddSkillNullToken() {
        assertEquals("{\"error\":\"bad skill data\"}", server.skillService.addSkill(gson.toJson(new Skill(null, "язык C++", 4))));
    }

    @Test
    public void testAddSkillNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.skillService.addSkill(gson.toJson(null)));
    }

    @Test
    public void testAddSkillNullSkillName() {
        assertEquals("{\"error\":\"bad skill data\"}", server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, null, 4))));
    }

    @Test
    public void testAddSkillBadLevelSkill() {
        assertEquals("{\"error\":\"bad skill data\"}", server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык C++", -1))));
    }

    @Test
    public void testAddSkillBadToken() {
        assertEquals("{\"error\":\"user with this token does not exist\"}", server.skillService.addSkill(gson.toJson(new Skill("Bad Token", "язык C++", 4))));
    }

    @Test
    public void testRemoveSkillBadToken() {
        assertEquals("{\"error\":\"user with this token or skill with this name does not exist \"}", server.skillService.removeSkill(gson.toJson(new Skill("Bad Token", "язык C++", 4))));
    }

    @Test
    public void testRemoveSkillBadSkillName() {
        assertEquals("{\"error\":\"user with this token or skill with this name does not exist \"}", server.skillService.removeSkill(gson.toJson(new Skill(tokenEmployeeString, "Bad name", 4))));
    }

    @Test
    public void testEditSkillLevel() {
        server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык C#", 4)));
        assertEquals("\"\"", server.skillService.editSkillLevel(gson.toJson(new Skill(tokenEmployeeString, "язык C#", 5))));
    }
}

