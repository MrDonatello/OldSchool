package net.thumbtack.school.hiring.model;

import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

public abstract class UsersData {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String login;
    private String password;

    protected UsersData(String firstName, String lastName, String patronymic, String email, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) throws ServiceException {

        this.password = password;
        if (passwordNull()) {
            throw new ServiceException(ErrorCode.NULL_PASSWORD_REQUEST);
        }
        if (passwordValidate()) {
            throw new ServiceException(ErrorCode.WRONG_PASSWORD_REQUEST);
        }


    }

    protected boolean passwordNull() {

        boolean bool = false;
        if (password == null) {
            bool = true;
        }
        return bool;
    }

    protected boolean passwordValidate() {

        boolean bool = false;
        if (password.length() <= 3 || password.length() > 20) {
            bool = true;
        }
        return bool;
    }

    public boolean passwordCompare(String password) {

        boolean bool = false;
        if (this.password.equals(password)) {
            bool = true;
        }
        return bool;
    }
}
