package net.thumbtack.school.hiring.service;

public enum ErrorCode {

    NULL_FIRST_NAME_REQUEST("first name field is blank"),
    NULL_VACANCY("vacancy does not exist"),
    NULL_JOB_TITLE_REQUEST("job title field is blank"),
    NULL_TOKEN_REQUEST("token field is blank"),
    NULL_COMPANY_NAME_REQUEST("company name field is blank"),
    NULL_ADDRESS_REQUEST("address field is blank"),
    NULL_LAST_NAME_REQUEST("last name field is blank"),
    NULL_EMAIL_REQUEST("email field is blank"),
    NULL_LOGIN_REQUEST("login field is blank"),
    WRONG_LOGIN_REQUEST("invalid length of login "),
    WRONG_SALARY_REQUEST("invalid salary value "),
    WRONG_DUPLICATE_LOGIN_REQUEST("login already exist"),
    NULL_PASSWORD_REQUEST("password field is blank"),
    WRONG_PASSWORD_REQUEST("invalid length of password"),
    NULL_REQUEST("request data error"),
    USER_ERROR("user is not found"),
    REMOVE_REQUIREMENT_ERROR("requirements is not found"),
    EDIT_REQUIREMENT_ERROR("requirements is not found"),
    EDIT_DATA_EMPLOYER_ERROR("Invalid edit data Employer"),
    ERROR_DATA_REQUIREMENT("requirement data or vacancy data is not found"),
    ADD_SKILL_ERROR("user with this token does not exist"),
    SKILL_ERROR("bad skill data"),
    REMOVE_VACANCY_ERROR("data vacancy error"),
    REMOVE_SKILL_ERROR("user with this token or skill with this name does not exist "),
    EDIT_SKILL_ERROR("user with this token or skill with this name does not exist "),
    ACTIVE_INACTIVE_VACANCY_ERROR("vacancy is not found"),
    ACTIVE_INACTIVE_PROFILE_ERROR("token is already inactive"),
    HIRE_ERROR("vacancy or employee login is not found"),
    GET_VACANCY_ERROR("vacancy is not found"),
    ADD_VACANCY_ERROR("data vacancy error");

    private String error;

    ErrorCode(String errorString) {

        this.error = errorString;
    }

    public String getErrorString() {

        return error;
    }
}
