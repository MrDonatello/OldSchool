package net.thumbtack.school.hiring.dto.request;


import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

public class ProfileDtoRequest {

    private String token;
    private boolean active;

    public ProfileDtoRequest(String token, boolean active) {
        this.token = token;
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public boolean isActive() {
        return active;
    }

    public void validate() throws ServiceException {
        if (token == null) {
            throw new ServiceException(ErrorCode.NULL_TOKEN_REQUEST);
        }
    }
}
