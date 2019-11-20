package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.service.ServiceException;

import java.util.List;

public interface EmployeeDao {

    String insert(Employee employee);

    public String EditDataEmployee(Employee updatedEmployee) throws ServiceException;

    List<Employee> employeesWithAllSkills(String jobTitle,int salary,String token) throws ServiceException;

    List<Employee> employeesWithObligedSkills(String jobTitle,int salary,String token) throws ServiceException;

    List<Employee> employeesWithSkillsAllLevel(String jobTitle,int salary,String token) throws ServiceException;

    List<Employee> employeesWithOneSkill(String jobTitle,int salary,String token) throws ServiceException;
}
