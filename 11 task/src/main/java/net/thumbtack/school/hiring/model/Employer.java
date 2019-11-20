package net.thumbtack.school.hiring.model;

public class Employer extends UsersData {

    private String token;
    private String companyName;
    private String address;

    public String getToken() {
        return token;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Employer(String firstName, String lastName, String patronymic, String email, String login, String password, String companyName, String address) {
        super(firstName, lastName, patronymic, email, login, password);
        this.companyName = companyName;
        this.address = address;
    }
}