package net.thumbtack.school.hiring.model;

public class EmployeeForResponse {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String login;

    public EmployeeForResponse(String firstName, String lastName, String patronymic, String email, String login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.login = login;
    }
}
