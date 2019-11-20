package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.daoimpl.*;
import net.thumbtack.school.hiring.dto.request.*;
import net.thumbtack.school.hiring.dto.response.RegisterEmployerDtoResponse;
import net.thumbtack.school.hiring.dto.response.ResponseError;
import net.thumbtack.school.hiring.model.Employer;

public class EmployerService extends UserService {

    private Gson gson = new Gson();
    private EmployerDaoImpl employerDao;

    public EmployerService(EmployerDaoImpl employerDao, UserProfileDaoImpl userDao) {
        super(userDao);
        this.employerDao = employerDao;
    }

    public String registerEmployer(String requestJsonString) {

        String result;
        RegisterEmployerDtoUsersData dto = gson.fromJson(requestJsonString, RegisterEmployerDtoUsersData.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new RegisterEmployerDtoResponse(employerDao.insert(gson.fromJson(gson.toJson(dto), Employer.class))));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String editRegistrationData(String requestJsonString) {

        String result;
        EditingEmployerDtoRequest dto = gson.fromJson(requestJsonString, EditingEmployerDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(employerDao.EditDataEmployer(dto.getEmployer()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

}
