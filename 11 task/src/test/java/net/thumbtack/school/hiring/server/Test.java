package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.hiring.daoimpl.UserProfileDaoImpl;
import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.service.EmployeeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class Test {

    @org.junit.Test
    public void test() throws Exception {


        Server server =new Server();
        server.startServer(null);
        Gson gson = new Gson();
        Employee employee = new Employee("name", "lastName", "patr", "email", "login", "123");

        server.employeeService.registerEmployee(gson.toJson(employee));
        server.employeeService.employeesWithSkillsAllLevel();

        EmployeeDaoImpl mockEmployeeDao = mock(EmployeeDaoImpl.class);
        UserProfileDaoImpl mockUserProfileDao= mock(UserProfileDaoImpl.class);
        //EmployeeService employeeService = mock(EmployeeService.class);

        EmployeeService employeeService = new EmployeeService(mockEmployeeDao,mockUserProfileDao);
      String s =  employeeService.registerEmployee(gson.toJson(employee));
       // when()

        when(mockUserProfileDao.logInServer(any(String.class),any(String.class))).thenReturn("");


    }



   /* @org.junit.Test
    public void testLogIn() throws Exception {
        // Типичный сценарий тестирования: создание стаб-объекта, возвращающего фиксированное значение,
        // вызов тетируемого метода и проверка результата
        UserService userService = new UserService();
        UserDao mockUserDao = mock(UserDao.class);
        User user = new User("","","","","");
        when(mockUserDao.getUser(any(String.class), any(String.class))).thenReturn(user);
        userService.setUserDao(mockUserDao);

        String jsonresponse = userService.logIn("{\"login\":\"vpupkin\",\"password\":\"P@ssword123\"}");
        assertTrue(jsonresponse.contains("token"));
    }*/
}
