package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.SkillDao;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.service.ServiceException;

public class SkillDaoImpl implements SkillDao {

    public String addSkill(Skill skill) throws ServiceException {

        return Server.getDataBase().addSkill(skill);
    }

    public String editSkillLevel(Skill skill) throws ServiceException {

        return Server.getDataBase().editSkillLevel(skill);
    }

    public String removeSkill(Skill skill) throws ServiceException {

        return Server.getDataBase().removeSkill(skill);
    }
}
