package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.daoimpl.SkillDaoImpl;
import net.thumbtack.school.hiring.dto.request.SkillDtoRequest;
import net.thumbtack.school.hiring.dto.response.ResponseError;
import net.thumbtack.school.hiring.model.Skill;

public class SkillService {

    private Gson gson = new Gson();
    private SkillDaoImpl skillDao;

    public SkillService(SkillDaoImpl skillDao) {
        this.skillDao = skillDao;
    }

    public String addSkill(String requestJsonString) {

        String result;
        SkillDtoRequest dto = gson.fromJson(requestJsonString, SkillDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(skillDao.addSkill(gson.fromJson(gson.toJson(dto), Skill.class)));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String editSkillLevel(String requestJsonString) {

        String result;
        SkillDtoRequest dto = gson.fromJson(requestJsonString, SkillDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(skillDao.editSkillLevel(gson.fromJson(gson.toJson(dto), Skill.class)));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String removeSkill(String requestJsonString) {

        String result;
        SkillDtoRequest dto = gson.fromJson(requestJsonString, SkillDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(skillDao.removeSkill(gson.fromJson(gson.toJson(dto), Skill.class)));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }
}
