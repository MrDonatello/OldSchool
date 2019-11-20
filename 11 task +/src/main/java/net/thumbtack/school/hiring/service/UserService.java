package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.daoimpl.*;
import net.thumbtack.school.hiring.dto.request.*;
import net.thumbtack.school.hiring.dto.response.ResponseError;
import net.thumbtack.school.hiring.dto.response.TokenDtoResponse;

public class UserService {

    private Gson gson = new Gson();
    private UserProfileDaoImpl userDao;

    UserService(UserProfileDaoImpl userDao) {
        this.userDao = userDao;
    }

    UserProfileDaoImpl getUserDao() {
        return userDao;
    }

    public String logInServer(String loginPasswordJson) {

        String result;
        LoginPasswordDtoRequest dto = gson.fromJson(loginPasswordJson, LoginPasswordDtoRequest.class);
        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            result = gson.toJson(userDao.logInServer(dto.getLogin(), dto.getPassword()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;

    }

    public String logOutServer(String tokenJson) {

        String result;
        TokenDtoRequest dto = gson.fromJson(tokenJson, TokenDtoRequest.class);
        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(userDao.logOut(dto.getToken()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String leaveServer(String tokenJson) {

        String result;
        TokenDtoRequest dto = gson.fromJson(tokenJson, TokenDtoRequest.class);
        try {
            if (dto == null || dto.getToken() == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(userDao.leaveServer(dto.getToken()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String getToken(String loginPasswordJson) {

        String result;
        LoginPasswordDtoRequest dto = gson.fromJson(loginPasswordJson, LoginPasswordDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            result = gson.toJson(new TokenDtoResponse(userDao.getToken(dto.getLogin(), dto.getPassword())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }
}
