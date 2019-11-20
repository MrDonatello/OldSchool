package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.daoimpl.RequirementsDaoImpl;
import net.thumbtack.school.hiring.dto.request.RequirementDtoRequest;
import net.thumbtack.school.hiring.dto.response.ResponseError;

public class RequirementService {

    private Gson gson = new Gson();
    private RequirementsDaoImpl dao;

    public RequirementService(RequirementsDaoImpl dao) {
        this.dao = dao;
    }

    public String addRequirement(String requestJsonString) {

        String result;
        RequirementDtoRequest dto = gson.fromJson(requestJsonString, RequirementDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();

            result = gson.toJson(dao.addRequirement(dto.getJobTitle(), dto.getSalary(), dto.getToken(), dto.getRequirementName(), dto.getLevel(), dto.isObliged()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String removeRequirement(String requestJsonString) {

        String result;
        RequirementDtoRequest dto = gson.fromJson(requestJsonString, RequirementDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(dao.removeRequirement(dto.getJobTitle(), dto.getSalary(), dto.getToken(), dto.getRequirementName()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String editRequirementLevel(String requestJsonString) {

        String result;
        RequirementDtoRequest dto = gson.fromJson(requestJsonString, RequirementDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(dao.editRequirementLevel(dto.getJobTitle(), dto.getSalary(), dto.getToken(), dto.getRequirementName(), dto.getLevel()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String editRequirementObliged(String requestJsonString) {

        String result;
        RequirementDtoRequest dto = gson.fromJson(requestJsonString, RequirementDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(dao.editRequirementObliged(dto.getJobTitle(), dto.getSalary(), dto.getToken(), dto.getRequirementName(), dto.isObliged()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }
}
