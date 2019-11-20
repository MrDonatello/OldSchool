package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.service.ServiceException;

public interface EmployerDao {

    String insert(Employer employer);

    public String EditDataEmployer(Employer updatedEmployer) throws ServiceException;
}
