package net.thumbtack.school.hiring.dto.request;

public class LoginPasswordDtoRequest {

    private String login;
    private String password;

    public LoginPasswordDtoRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
