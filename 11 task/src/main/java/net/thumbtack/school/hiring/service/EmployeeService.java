package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dao.EmployeeDao;
import net.thumbtack.school.hiring.daoimpl.*;
import net.thumbtack.school.hiring.dto.request.*;
import net.thumbtack.school.hiring.dto.response.RegisterEmployeeDtoResponse;
import net.thumbtack.school.hiring.dto.response.ResponseError;
import net.thumbtack.school.hiring.dto.response.SelectionEmployeesDtoResponse;
import net.thumbtack.school.hiring.model.Employee;

public class EmployeeService extends UserService {

    private Gson gson = new Gson();
    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao, UserProfileDaoImpl userDao) {
        super(userDao);
        this.employeeDao = employeeDao;
    }

    public String registerEmployee(String requestJsonString) {

        String result;
        RegisterEmployeeDtoUsersData dto = gson.fromJson(requestJsonString, RegisterEmployeeDtoUsersData.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            String dtoJson = gson.toJson(dto);
            result = gson.toJson(new RegisterEmployeeDtoResponse(employeeDao.insert(gson.fromJson(dtoJson, Employee.class))));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String editRegistrationData(String requestJsonString) {

        String result;
        EditingEmployeeDtoRequest dto = gson.fromJson(requestJsonString, EditingEmployeeDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(employeeDao.EditDataEmployee(dto.getEmployee()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String activateDeactivateProfile(String requestJsonString) {

        String result;
        ProfileDtoRequest dto = gson.fromJson(requestJsonString, ProfileDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(super.getUserDao().activateDeactivateProfile(dto.getToken(), dto.isActive()));

        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String employeesWithAllSkills(String requestJsonString) {

        String result;
        VacancyDtoRequest dto = gson.fromJson(requestJsonString, VacancyDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new SelectionEmployeesDtoResponse(employeeDao.employeesWithAllSkills(dto.getJobTitle(), dto.getSalary(), dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String employeesWithObligedSkills(String requestJsonString) {

        String result;
        VacancyDtoRequest dto = gson.fromJson(requestJsonString, VacancyDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new SelectionEmployeesDtoResponse(employeeDao.employeesWithObligedSkills(dto.getJobTitle(), dto.getSalary(), dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String employeesWithSkillsAllLevel(String requestJsonString) {

        String result;
        VacancyDtoRequest dto = gson.fromJson(requestJsonString, VacancyDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new SelectionEmployeesDtoResponse(employeeDao.employeesWithSkillsAllLevel(dto.getJobTitle(), dto.getSalary(), dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String employeesWithOneSkill(String requestJsonString) {

        String result;
        VacancyDtoRequest dto = gson.fromJson(requestJsonString, VacancyDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new SelectionEmployeesDtoResponse(employeeDao.employeesWithOneSkill(dto.getJobTitle(), dto.getSalary(), dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

}
