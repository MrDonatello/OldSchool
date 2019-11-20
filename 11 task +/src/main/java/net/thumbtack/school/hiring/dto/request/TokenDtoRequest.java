package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

public class TokenDtoRequest {

    private String token;

    public TokenDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void validate() throws ServiceException {
        if (getToken() == null) {
            throw new ServiceException(ErrorCode.NULL_REQUEST);
        }
    }
}
