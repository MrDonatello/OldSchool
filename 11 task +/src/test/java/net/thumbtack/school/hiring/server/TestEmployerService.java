package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.request.*;
import net.thumbtack.school.hiring.dto.response.ResponseError;
import net.thumbtack.school.hiring.model.*;
import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestEmployerService {

    private static Gson gson = new Gson();
    private static Server server = new Server();
    private static String tokenEmployerJson;
    private static String tokenEmployerString;
    private static String tokenRegisterEmployer;
    private static Employer employer = new Employer("firstNameEmployer", "lastNameEmployer", "patronymicEmployer", "emailEmployer", "loginEmployer", "passwordEmployer", "companyName", "address");


    @BeforeClass
    public static void testEmployee() {
        server.startServer(null);
        tokenRegisterEmployer = server.employerService.registerEmployer(gson.toJson(employer));
        tokenEmployerJson = server.employerService.getToken(gson.toJson(new LoginPasswordDtoRequest("loginEmployer", "passwordEmployer")));
        tokenEmployerString = gson.fromJson(tokenEmployerJson, TokenDtoRequest.class).getToken();
        employer.setToken(tokenEmployerString);
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
    public void testRegisterEmployer() {
        assertEquals(tokenEmployerJson, tokenRegisterEmployer);
    }

    @Test
    public void testRegisterEmployerNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.employerService.registerEmployer(gson.toJson(null)));
    }

    @Test
    public void testRegisterEmployerNullFirstName() {
        assertEquals("{\"error\":\"first name field is blank\"}", server.employerService.registerEmployer(gson.toJson(new Employer(null, "test", "test", "test", "test", "test", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerNullLastName() {
        assertEquals("{\"error\":\"last name field is blank\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", null, "test", "test", "test", "test", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerNullEmail() {
        assertEquals("{\"error\":\"email field is blank\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", null, null, "test", "test", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerNullLogin() {
        assertEquals("{\"error\":\"login field is blank\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", null, "test", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerShortLogin() {
        assertEquals("{\"error\":\"invalid length of login \"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", "123", "test", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerTooLongLogin() {
        assertEquals("{\"error\":\"invalid length of login \"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", "login length is more than 20", "test", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerExistingLogin() {
        assertEquals("{\"error\":\"login already exist\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", "loginEmployer", "test", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerNullPassword() {
        assertEquals("{\"error\":\"password field is blank\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", "test", null, "test", "test"))));
    }

    @Test
    public void testRegisterEmployerShortPassword() {
        assertEquals("{\"error\":\"invalid length of password\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", "test", "1", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerTooLongPassword() {
        assertEquals("{\"error\":\"invalid length of password\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", "test", "password length is more than 20", "test", "test"))));
    }

    @Test
    public void testRegisterEmployerNullCompanyName() {
        assertEquals("{\"error\":\"company name field is blank\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", "test", "test", null, "test"))));
    }

    @Test
    public void testRegisterEmployerNullAddress() {
        assertEquals("{\"error\":\"address field is blank\"}", server.employerService.registerEmployer(gson.toJson(new Employer("test", "test", "test", "test", "test", "test", "test", null))));
    }

    @Test
    public void testHire() {
        Employee employee = new Employee("firstNameEmployee", "lastNameEmployee", "patronymicEmployee", "emailEmployee", "loginEmployee", "passwordEmployee");
        server.employeeService.registerEmployee(gson.toJson(employee));
        String tokenEmployeeJson = server.employeeService.getToken(gson.toJson(new LoginPasswordDtoRequest("loginEmployee", "passwordEmployee")));
        String tokenEmployeeString = gson.fromJson(tokenEmployeeJson, TokenDtoRequest.class).getToken();
        server.skillService.addSkill(gson.toJson(new Skill(tokenEmployeeString, "язык JAVA", 5)));

        Map<String, Requirements> requirementsHashMap = new HashMap<>();
        requirementsHashMap.put("язык JAVA", new Requirements("язык JAVA", 5, true));
        Vacancy vacancy = new Vacancy("JAVA", 30000, requirementsHashMap, tokenEmployerString, true);
        server.vacancyService.addVacancy(gson.toJson(vacancy));

        assertEquals("\"\"", server.vacancyService.hire(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString, employee.getLogin()))));
        server.employeeService.leaveServer(tokenEmployeeJson);
    }

    @Test
    public void testHireNullLogin() {
        assertEquals("{\"error\":\"vacancy or employee login is not found\"}", server.vacancyService.hire(gson.toJson(new VacancyDtoRequest("JAVA", 30000, tokenEmployerString, null))));
    }

    @Test
    public void testHireNullJobTitle() {
        Employee employee = new Employee("firstNameEmployee", "lastNameEmployee", "patronymicEmployee", "emailEmployee", "loginEmployee", "passwordEmployee");
        assertEquals("{\"error\":\"job title field is blank\"}", server.vacancyService.hire(gson.toJson(new VacancyDtoRequest(null, 30000, tokenEmployerString, employee.getLogin()))));
    }

    @Test
    public void testHireBadToken() {
        Employee employee = new Employee("firstNameEmployee", "lastNameEmployee", "patronymicEmployee", "emailEmployee", "loginEmployee", "passwordEmployee");
        assertEquals("{\"error\":\"vacancy or employee login is not found\"}", server.vacancyService.hire(gson.toJson(new VacancyDtoRequest("JAVA", 30000, "bad token", employee.getLogin()))));
    }

    @Test
    public void testHireBadSalary() {
        Employee employee = new Employee("firstNameEmployee", "lastNameEmployee", "patronymicEmployee", "emailEmployee", "loginEmployee", "passwordEmployee");
        assertEquals("{\"error\":\"invalid salary value \"}", server.vacancyService.hire(gson.toJson(new VacancyDtoRequest("JAVA", 0, tokenEmployerString, employee.getLogin()))));
    }

    @Test
    public void testHireBadJobTitle() {
        Employee employee = new Employee("firstNameEmployee", "lastNameEmployee", "patronymicEmployee", "emailEmployee", "loginEmployee", "passwordEmployee");
        assertEquals("{\"error\":\"vacancy or employee login is not found\"}", server.vacancyService.hire(gson.toJson(new VacancyDtoRequest("BadTitle", 30000, tokenEmployerString, employee.getLogin()))));
    }

    @Test
    public void testHireNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.vacancyService.hire(gson.toJson(null)));
    }

    @Test
    public void testEditFirstName() {
        employer.setFirstName("newFirstNameEmployer");
        assertEquals("\"\"", server.employerService.editRegistrationData(gson.toJson(new EditingEmployerDtoRequest(employer))));
    }

    @Test
    public void testEditFirstNameNullEmployee() {
        assertEquals("{\"error\":\"Invalid edit data Employer\"}", server.employerService.editRegistrationData(gson.toJson(new EditingEmployerDtoRequest(null))));
    }

    @Test
    public void testEditFirstNameNullData() {
        assertEquals("{\"error\":\"request data error\"}", server.employerService.editRegistrationData(null));
    }

    @Test
    public void testEditLastName() {
        employer.setLastName("newLastNameEmployee");
        assertEquals("\"\"", server.employerService.editRegistrationData(gson.toJson(new EditingEmployerDtoRequest(employer))));
    }

    @Test
    public void testEditPatronymic() {
        employer.setPatronymic("newPatronymicEmployee");
        assertEquals("\"\"", server.employerService.editRegistrationData(gson.toJson(new EditingEmployerDtoRequest(employer))));
    }

    @Test
    public void testEditEmail() {
        employer.setEmail("newEmailEmployer");
        assertEquals("\"\"", server.employerService.editRegistrationData(gson.toJson(new EditingEmployerDtoRequest(employer))));
    }

    @Test
    public void testPassword() throws ServiceException {
        employer.setPassword("newPasswordEmployer");
        assertEquals("\"\"", server.employerService.editRegistrationData(gson.toJson(new EditingEmployerDtoRequest(employer))));
    }

    @Test
    public void testCompanyName() {
        employer.setCompanyName("newCompanyName");
        assertEquals("\"\"", server.employerService.editRegistrationData(gson.toJson(new EditingEmployerDtoRequest(employer))));
    }

    @Test
    public void testAddress() {
        employer.setAddress("newAddress");
        assertEquals("\"\"", server.employerService.editRegistrationData(gson.toJson(new EditingEmployerDtoRequest(employer))));
    }

    @Test
    public void testLogOutLogInServer() {
        assertEquals("\"\"", server.employerService.logOutServer(tokenEmployerJson));
        assertEquals("\"\"", server.employerService.logInServer(gson.toJson(new LoginPasswordDtoRequest("loginEmployer", "passwordEmployer"))));
    }

    @Test
    public void testLogOutLogInServerNullLogin() {
        assertEquals("{\"error\":\"user is not found\"}", server.employerService.logInServer(gson.toJson(new LoginPasswordDtoRequest(null, "passwordEmployer"))));
    }

    @Test
    public void testLogOutLogInServerNullPassword() {
        assertEquals("{\"error\":\"user is not found\"}", server.employerService.logInServer(gson.toJson(new LoginPasswordDtoRequest("loginEmployer", null))));
    }

    @Test
    public void testLogOutLogInServerBadPassword() {
        assertEquals("{\"error\":\"user is not found\"}", server.employerService.logInServer(gson.toJson(new LoginPasswordDtoRequest("loginEmployer", "BadPassword"))));
    }

    @Test
    public void testLeaveServer() {
        assertEquals("\"\"", server.employerService.leaveServer(tokenEmployerJson));
        assertEquals(gson.toJson(new ResponseError(ErrorCode.USER_ERROR.getErrorString())), server.employerService.logInServer(tokenEmployerJson));
    }

    @Test
    public void testLeaveServerNullToken() {
        assertEquals("{\"error\":\"request data error\"}", server.employerService.leaveServer(null));
    }
}
