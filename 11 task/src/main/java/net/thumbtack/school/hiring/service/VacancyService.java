package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.daoimpl.VacancyDaoImpl;
import net.thumbtack.school.hiring.dto.request.VacancyDtoRequest;
import net.thumbtack.school.hiring.dto.request.TokenDtoRequest;
import net.thumbtack.school.hiring.dto.response.ResponseError;
import net.thumbtack.school.hiring.dto.response.VacancyDtoResponse;
import net.thumbtack.school.hiring.model.Vacancy;

public class VacancyService {

    private Gson gson = new Gson();
    private VacancyDaoImpl vacancyDao;

    public VacancyService(VacancyDaoImpl vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    public String addVacancy(String requestJsonString) {

        String result;
        VacancyDtoRequest dto = gson.fromJson(requestJsonString, VacancyDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(vacancyDao.insert(gson.fromJson(gson.toJson(dto), Vacancy.class)));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String removeVacancy(String requestJsonString) {

        String result;
        VacancyDtoRequest dto = gson.fromJson(requestJsonString, VacancyDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(vacancyDao.removeVacancy(gson.fromJson(gson.toJson(dto), Vacancy.class)));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String activateDeactivateVacancy(String requestJsonString) {

        String result;
        VacancyDtoRequest dto = gson.fromJson(requestJsonString, VacancyDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(vacancyDao.activateDeactivateVacancy(dto.getJobTitle(), dto.getSalary(), dto.getToken(), dto.isActive()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String getInactiveVacancies(String requestJsonString) {

        String result;
        TokenDtoRequest dto = gson.fromJson(requestJsonString, TokenDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new VacancyDtoResponse(vacancyDao.getInactiveVacancies(dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String getActiveVacancies(String requestJsonString) {

        String result;
        TokenDtoRequest dto = gson.fromJson(requestJsonString, TokenDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new VacancyDtoResponse(vacancyDao.getActiveVacancies(dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String getAllVacancies(String requestJsonString) {

        String result;
        TokenDtoRequest dto = gson.fromJson(requestJsonString, TokenDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new VacancyDtoResponse(vacancyDao.getAllVacancies(dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String getAllSuitableVacancies(String requestJsonString) {

        String result;
        TokenDtoRequest dto = gson.fromJson(requestJsonString, TokenDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new VacancyDtoResponse(vacancyDao.getAllSuitableVacancies(dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String requirementObligedVacancy(String requestJsonString) {

        String result;
        TokenDtoRequest dto = gson.fromJson(requestJsonString, TokenDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new VacancyDtoResponse(vacancyDao.requirementObligedVacancy(dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String getAllLevelVacancies(String requestJsonString) {

        String result;
        TokenDtoRequest dto = gson.fromJson(requestJsonString, TokenDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new VacancyDtoResponse(vacancyDao.getAllLevelVacancies(dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String getVacanciesOneSkill(String requestJsonString) {

        String result;
        TokenDtoRequest dto = gson.fromJson(requestJsonString, TokenDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(new VacancyDtoResponse(vacancyDao.getVacanciesOneSkill(dto.getToken())));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }

    public String hire(String requestJsonString) {

        String result;
        VacancyDtoRequest dto = gson.fromJson(requestJsonString, VacancyDtoRequest.class);

        try {
            if (dto == null) {
                throw new ServiceException(ErrorCode.NULL_REQUEST);
            }
            dto.validate();
            result = gson.toJson(vacancyDao.hire(dto.getJobTitle(), dto.getSalary(), dto.getToken(), dto.getEmployeeLogin()));
        } catch (ServiceException e) {
            result = gson.toJson(new ResponseError(e.getErrorCode().getErrorString()));
        }
        return result;
    }
}
