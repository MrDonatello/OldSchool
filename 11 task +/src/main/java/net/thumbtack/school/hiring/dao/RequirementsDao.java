package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.service.ServiceException;

public interface RequirementsDao {

    String addRequirement(String jobTitle,int salary,String token,String requirementName,int level,boolean obliged) throws ServiceException;

    String removeRequirement(String jobTitle,int salary,String token,String requirementName) throws ServiceException;

    String editRequirementLevel(String jobTitle,int salary,String token,String requirementName,int level) throws ServiceException;

    String editRequirementObliged(String jobTitle,int salary,String token,String requirementName,boolean obliged) throws ServiceException;
}

