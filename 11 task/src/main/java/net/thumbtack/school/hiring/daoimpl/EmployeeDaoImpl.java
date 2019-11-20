package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.EmployeeDao;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.service.ServiceException;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    public String insert(Employee employee) {

        return Server.getDataBase().insertEmployee(employee);
    }

    public String EditDataEmployee(Employee updatedEmployee) {

        return Server.getDataBase().EditDataEmployee(updatedEmployee);
    }

    public List<Employee> employeesWithAllSkills(String jobTitle, int salary, String token) throws ServiceException {

        return Server.getDataBase().employeesWithAllSkills(jobTitle, salary, token);
    }

    public List<Employee> employeesWithObligedSkills(String jobTitle, int salary, String token) throws ServiceException {

        return Server.getDataBase().employeesWithObligedSkills(jobTitle, salary, token);
    }

    public List<Employee> employeesWithSkillsAllLevel(String jobTitle, int salary, String token) throws ServiceException {

        return Server.getDataBase().employeesWithSkillsAllLevel(jobTitle, salary, token);
    }

    public List<Employee> employeesWithOneSkill(String jobTitle, int salary, String token) throws ServiceException {

        return Server.getDataBase().employeesWithOneSkill(jobTitle, salary, token);
    }

}
