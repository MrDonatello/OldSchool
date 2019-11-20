package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.model.UsersData;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

public class RegisterEmployeeDtoUsersData extends UsersData {

    public RegisterEmployeeDtoUsersData(String firstName, String lastName, String patronymic, String email, String login, String password) {
        super(firstName, lastName, patronymic, email, login, password);
    }

    public void validate() throws ServiceException {

        if (getFirstName() == null) {
            throw new ServiceException(ErrorCode.NULL_FIRST_NAME_REQUEST);
        }
        if (getLastName() == null) {
            throw new ServiceException(ErrorCode.NULL_LAST_NAME_REQUEST);
        }
        if (getEmail() == null) {
            throw new ServiceException(ErrorCode.NULL_EMAIL_REQUEST);
        }
        if (getLogin() == null) {
            throw new ServiceException(ErrorCode.NULL_LOGIN_REQUEST);
        }
        if (!(getLogin().length() > 3 && getLogin().length() < 20)) {
            throw new ServiceException(ErrorCode.WRONG_LOGIN_REQUEST);
        }
        if (Server.getDataBase().searchLogin(getLogin())) {
            throw new ServiceException(ErrorCode.WRONG_DUPLICATE_LOGIN_REQUEST);
        }
        if (passwordNull()) {
            throw new ServiceException(ErrorCode.NULL_PASSWORD_REQUEST);
        }
        if (passwordValidate()) {
            throw new ServiceException(ErrorCode.WRONG_PASSWORD_REQUEST);
        }
    }
}
