package net.thumbtack.school.hiring.server.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.server.Users.Employee;
import net.thumbtack.school.hiring.server.service.data.DataTransferObject;

public class EmployeeService {


    public String registerEmployee(String requestJsonString){

        Gson gson =new Gson();

        DataTransferObject dto = gson.fromJson(requestJsonString, DataTransferObject.class);

        if(DataTransferObject.validate(dto)){

            Employee employee = new Employee(dto.getFirstName(),dto.getLogin(),dto.getPassword());




        }



        // Регистрирует работодателя на сервере. requestJsonString содержит данные о работодателе ,
        // необходимые для регистрации.  Метод при успешном выполнении возвращает json
        // с единственным элементом “token”. Если же команду по какой-то причине выполнить нельзя,
        // возвращает json с элементом “error”
        return null;

    }


    public String getVacancies(String requestJsonString){

        // Работник  получает список вакансий. requestJsonString содержит параметры для поиска вакансий и token,
        // полученный как результат выполнения команды регистрации работника. Метод при успешном выполнении
        // возвращает json с описанием всех вакансий. Если же команду почему-то выполнить нельзя, возвращает
        // json с элементом “error”
        Gson gson =new Gson();
        gson.fromJson(requestJsonString, DataTransferObject.class);






        return null;
    }

}
