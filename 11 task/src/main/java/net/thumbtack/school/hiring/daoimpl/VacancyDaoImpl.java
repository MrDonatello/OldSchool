package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.VacancyDao;
import net.thumbtack.school.hiring.model.Vacancy;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.service.ServiceException;

import java.util.Set;

public class VacancyDaoImpl implements VacancyDao {

    public String activateDeactivateVacancy(String jobTitle, int salary, String token, boolean activeInactive) throws ServiceException {//(vacancy)

        return Server.getDataBase().activateDeactivateVacancy(jobTitle, salary, token, activeInactive);
    }

    public String insert(Vacancy vacancy) throws ServiceException {

        return Server.getDataBase().insertVacancy(vacancy);
    }

    public Set<Vacancy> getAllVacancies(String token) throws ServiceException {

        return Server.getDataBase().getAllVacancies(token);
    }

    public Set<Vacancy> getActiveVacancies(String token)throws ServiceException {

        return Server.getDataBase().getActiveVacancies(token);
    }

    public Set<Vacancy> getInactiveVacancies(String token) throws ServiceException {

        return Server.getDataBase().getInactiveVacancies(token);
    }

    public Set<Vacancy> getAllSuitableVacancies(String token) {

        return Server.getDataBase().getAllSuitableVacancies(token);
    }

    public Set<Vacancy> requirementObligedVacancy(String token) {

        return Server.getDataBase().RequirementObligedVacancy(token);
    }

    public Set<Vacancy> getAllLevelVacancies(String token) {

        return Server.getDataBase().getAllLevelVacancies(token);
    }

    public Set<Vacancy> getVacanciesOneSkill(String token) {

        return Server.getDataBase().getVacanciesOneSkill(token);
    }

    public String removeVacancy(Vacancy vacancy) throws ServiceException {

        return Server.getDataBase().removeVacancy(vacancy);
    }

    public String hire(String jobTitle, int salary, String token, String employeeLogin) throws ServiceException {//(vacancy,employeeLogin)

        return Server.getDataBase().hire(jobTitle, salary, token, employeeLogin);
    }
}
