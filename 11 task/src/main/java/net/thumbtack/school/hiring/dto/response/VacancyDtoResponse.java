package net.thumbtack.school.hiring.dto.response;

import net.thumbtack.school.hiring.model.Vacancy;
import net.thumbtack.school.hiring.model.VacancyForResponse;

import java.util.LinkedHashSet;
import java.util.Set;

public class VacancyDtoResponse {

    private Set<VacancyForResponse> vacancyForResponse = new LinkedHashSet<>();

    public VacancyDtoResponse(Set<Vacancy> vacancySet) {

        for (Vacancy vacancy : vacancySet) {
            vacancyForResponse.add(new VacancyForResponse(vacancy.getJobTitle(), vacancy.getSalary(), vacancy.getRequirements()));
        }
    }
}
