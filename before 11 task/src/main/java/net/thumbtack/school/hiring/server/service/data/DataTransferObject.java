package net.thumbtack.school.hiring.server.service.data;

public class DataTransferObject {

    private String firstName;
    private String login;
    private String password;


    public DataTransferObject(String firstName, String login, String password) {
        this.firstName = firstName;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DataTransferObject(String firstName) {

        this.firstName = firstName;
    }

   public static boolean validate (DataTransferObject dto){



        return true;
    }

}
