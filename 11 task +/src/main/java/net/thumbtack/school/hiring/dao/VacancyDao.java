package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.model.Vacancy;
import net.thumbtack.school.hiring.service.ServiceException;

import java.util.Set;

public interface VacancyDao {


    String activateDeactivateVacancy(String jobTitle,int salary, String token, boolean activeInactive) throws ServiceException;

    String insert(Vacancy vacancy) throws ServiceException;

    Set<Vacancy> getAllVacancies(String token) throws ServiceException;

    Set<Vacancy> getActiveVacancies(String token) throws ServiceException;

    Set<Vacancy> getInactiveVacancies(String token) throws ServiceException;

    Set<Vacancy> getAllSuitableVacancies(String token);

    Set<Vacancy> requirementObligedVacancy(String token);

    Set<Vacancy> getAllLevelVacancies(String token);

    Set<Vacancy> getVacanciesOneSkill(String token);

    String removeVacancy(Vacancy vacancy) throws ServiceException;

    String hire(String jobTitle, int salary,String token ,String employeeLogin) throws ServiceException;
}
