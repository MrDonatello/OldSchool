package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

public class SkillDtoRequest {

    private String token;
    private String Name;
    private int level;

    public String getToken() {
        return token;
    }

    public String getSkillName() {
        return Name;
    }

    public int getLevelOfSkill() {
        return level;
    }

    public void validate() throws ServiceException {

        if (token == null) {
            throw new ServiceException(ErrorCode.SKILL_ERROR);
        }

        if (Name == null) {
            throw new ServiceException(ErrorCode.SKILL_ERROR);
        }
        if (level < 0) {
            throw new ServiceException(ErrorCode.SKILL_ERROR);
        }
    }
}
