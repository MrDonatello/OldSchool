package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.service.ErrorCode;
import net.thumbtack.school.hiring.service.ServiceException;

public class EditingEmployeeDtoRequest {

    private Employee employee;

    public EditingEmployeeDtoRequest(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void validate() throws ServiceException {


    }
}
