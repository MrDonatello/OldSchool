package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.service.ServiceException;

public interface SkillDao {

    String addSkill(Skill skill) throws ServiceException;

    String editSkillLevel(Skill skill) throws ServiceException;

    String removeSkill(Skill skill) throws ServiceException;
}
