package net.thumbtack.school.hiring.model;

public class Employee extends UsersData {

    private String token;

    public Employee(String firstName, String lastName, String patronymic, String email, String login, String password) {
        super(firstName, lastName, patronymic, email, login, password);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
