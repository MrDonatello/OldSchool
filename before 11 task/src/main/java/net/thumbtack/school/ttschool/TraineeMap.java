package net.thumbtack.school.ttschool;


import java.util.*;

public class TraineeMap {

    private Map<Trainee, String> map;


    public TraineeMap() {

        map = new HashMap<>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {

        for (Trainee key : map.keySet()) {
            if (key.equals(trainee)) {
                throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
            }
        }
        map.put(trainee, institute);
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {

        for (Trainee key : map.keySet()) {
            if (key.equals(trainee)) {
                map.replace(trainee, map.get(key), institute);
                return;

            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {

        for (Trainee key : map.keySet()) {
            if (key.equals(trainee)) {
                map.remove(key);
                return;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }


    public int getTraineesCount() {

        return map.size();

    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {

        String str = null;
        for (Trainee key : map.keySet()) {
            if (key.equals(trainee)) {
                str = map.get(key);
            }
        }
        if (str == null) {

            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return str;
    }

    public Set<Trainee> getAllTrainees() {

        return map.keySet();
    }

    public Set<String> getAllInstitutes() {

        return new HashSet<>(map.values());
    }

    public boolean isAnyFromInstitute(String institute) {


        boolean bool = false;
        for (String s : map.values()) {
            if (s.equals(institute)) {
                bool = true;
            }
        }
        return bool;
    }
}
