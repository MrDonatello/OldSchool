package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.EmployerDao;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.service.ServiceException;


public class EmployerDaoImpl implements EmployerDao {

    public String insert(Employer employer) {
        return Server.getDataBase().insertEmployer(employer);
    }

    public String EditDataEmployer(Employer updatedEmployer) throws ServiceException {

        return Server.getDataBase().EditDataEmployer(updatedEmployer);
    }
}
