package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.RequirementsDao;
import net.thumbtack.school.hiring.model.Requirements;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.service.ServiceException;

public class RequirementsDaoImpl implements RequirementsDao {

    public String addRequirement(String jobTitle, int salary, String token, String requirementName, int level, boolean obliged) throws ServiceException {

        return Server.getDataBase().addRequirement(jobTitle, salary, token, new Requirements(requirementName, level, obliged));
    }

    public String removeRequirement(String jobTitle, int salary, String token, String requirementName) throws ServiceException {

        return Server.getDataBase().removeRequirement(jobTitle, salary, token, requirementName);
    }

    public String editRequirementLevel(String jobTitle, int salary, String token, String requirementName, int level) throws ServiceException {

        return Server.getDataBase().editRequirementLevel(jobTitle, salary, token, requirementName, level);
    }

    public String editRequirementObliged(String jobTitle, int salary, String token, String requirementName, boolean obliged) throws ServiceException {

        return Server.getDataBase().editRequirementObliged(jobTitle, salary, token, requirementName, obliged);
    }
}
