package net.thumbtack.school.hiring.server.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.server.Users.Employee;
import net.thumbtack.school.hiring.server.service.data.DataTransferObject;

public class EmployerService {



    public String registerEmployer(String requestJsonString){

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

    public String addVacancy(String requestJsonString){

        //Работодатель добавляет новую вакансию на сервер. requestJsonString содержит описание вакансии
        // и token, полученный как результат выполнения команды регистрации работодателя.
        // Метод при успешном выполнении возвращает пустой json . Если же команду почему-то
        // выполнить нельзя, возвращает json с элементом “error”
        return null;
    }
}
