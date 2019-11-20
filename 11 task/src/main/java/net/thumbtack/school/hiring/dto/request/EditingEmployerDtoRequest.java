package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.service.ServiceException;

public class EditingEmployerDtoRequest {

    private Employer employer;

    public EditingEmployerDtoRequest(Employer employer) {
        this.employer = employer;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void validate() throws ServiceException {


    }
}
