package net.thumbtack.school.hiring.server;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.server.service.EmployeeService;
import net.thumbtack.school.hiring.server.service.EmployerService;

import java.io.*;

public class Server {

    private EmployeeService employeeService;
    private EmployerService employerService;

    public void startServer(String savedDataFileName) {

        if (savedDataFileName != null) {

            Gson gson = new Gson();
            try (BufferedReader br = new BufferedReader(new FileReader(new File(savedDataFileName)))) {
                // trainee = gson.fromJson(br, Trainee.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        //Производит всю необходимую инициализацию и запускает сервер.
        //      savedDataFileName - имя файла, в котором было сохранено состояние сервера.
        // Если savedDataFileName == null, восстановление состояния не производится, сервер стартует “с нуля”.

    }

    public void stopServer(String savedDataFileName) {

        if (savedDataFileName != null) {
            Gson gson = new Gson();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(savedDataFileName)))) {
                // gson.toJson(, bw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Останавливает сервер и записывает все его содержимое в файл сохранения с именем savedDataFileName.
        // Если savedDataFileName == null, запись содержимого не производится.
    }


    public String Name (String JsonString){

      //  Все данные для запроса должны быть упакованы в json-строку и в таком виде переданы методу. Метод возвращает json-строку с результатом выполнения операции.
        return null;
    }




}

