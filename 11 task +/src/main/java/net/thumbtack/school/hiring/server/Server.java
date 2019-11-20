package net.thumbtack.school.hiring.server;

import com.google.gson.*;
import net.thumbtack.school.hiring.daoimpl.*;
import net.thumbtack.school.hiring.database.DataBase;
import net.thumbtack.school.hiring.dto.response.SelectionEmployeesDtoResponse;
import net.thumbtack.school.hiring.service.*;
import java.io.*;

public class Server {

    EmployeeService employeeService;
    EmployerService employerService;
    SkillService skillService;
    RequirementService requirementService;
    VacancyService vacancyService;
    private static DataBase dataBase;
    private Gson gson = new Gson();

    public static DataBase getDataBase() {
        return dataBase;
    }

    void startServer(String savedDataFileName) {

        dataBase = new DataBase();
        employeeService = new EmployeeService(new EmployeeDaoImpl(), new UserProfileDaoImpl());
        employerService = new EmployerService(new EmployerDaoImpl(),new UserProfileDaoImpl());
        skillService = new SkillService(new SkillDaoImpl());
        requirementService = new RequirementService(new RequirementsDaoImpl());
        vacancyService = new VacancyService(new VacancyDaoImpl());

        if (savedDataFileName != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(new File(savedDataFileName)))) {
                dataBase = gson.fromJson(br, DataBase.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void stopServer(String savedDataFileName) {

        employerService = null;
        employeeService = null;
        skillService = null;
        requirementService = null;
        vacancyService = null;
        if (savedDataFileName != null) {
            Gson gson = new Gson();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(savedDataFileName)))) {
                gson.toJson(dataBase, bw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}