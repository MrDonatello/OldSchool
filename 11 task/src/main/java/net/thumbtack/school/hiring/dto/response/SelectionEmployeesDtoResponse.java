package net.thumbtack.school.hiring.dto.response;

import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.EmployeeForResponse;

import java.util.LinkedList;
import java.util.List;

public class SelectionEmployeesDtoResponse {

    private List<EmployeeForResponse> employeeForResponses = new LinkedList<>();

    public SelectionEmployeesDtoResponse(List<Employee> employeeList) {

        for (Employee employee : employeeList) {
            employeeForResponses.add(new EmployeeForResponse(employee.getFirstName(), employee.getLastName(), employee.getPatronymic(), employee.getEmail(), employee.getLogin()));
        }
    }
}
