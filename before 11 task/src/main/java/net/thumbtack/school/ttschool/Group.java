package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {

    private String name;
    private String room;
    private List<Trainee> trainees;

    public Group(String name, String room) throws TrainingException {

        if (name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        if (room == null || room.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        this.name = name;
        this.room = room;
        trainees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {

        if (name == null || name.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {

        if (room == null || room.equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {

        if (!trainees.contains(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        trainees.remove(trainee);
    }

    public void removeTrainee(int index) throws TrainingException {

        if (index >= trainees.size()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        trainees.remove(trainees.get(index));
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {

        Trainee trainee1 = null;
        for (Trainee trainee : trainees) {
            if (trainee.getFirstName().equals(firstName)) {
                trainee1 = trainee;
                break;
            }
        }
        if (trainee1 == null) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return trainee1;
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {

        Trainee trainee1 = null;
        for (Trainee trainee : trainees) {
            if (trainee.getFullName().equals(fullName)) {
                trainee1 = trainee;
                break;
            }
        }
        if (trainee1 == null) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return trainee1;
    }

    public void sortTraineeListByFirstNameAscendant() {

        trainees.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()));
    }

    public void sortTraineeListByRatingDescendant() {

        trainees.sort((o1, o2) -> -Integer.compare(o1.getRating(), o2.getRating()));
    }

    public void reverseTraineeList() {

        Collections.reverse(trainees);
    }

    public void rotateTraineeList(int positions) {

        if (positions < 0) {
            Collections.rotate(trainees, positions);
        }
        if (positions > 0) {
            Collections.rotate(trainees, -positions);
        }
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {

        int tmp = 5;
        List<Trainee> traineeList = new ArrayList<>();
        if (trainees.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        for (int i = 0; i < 5; i++) {
            for (Trainee trainee : trainees) {
                if (trainee.getRating() == tmp) {
                    traineeList.add(trainee);
                }
            }
            if (!traineeList.isEmpty()) {
                break;
            }
            tmp--;
        }
        return traineeList;
    }

    public boolean hasDuplicates() {

        boolean bool = false;
        for (int i = 0; i < trainees.size() - 1; i++) {
            Trainee trainee = trainees.get(i);
            trainees.remove(i);
            if (trainees.contains(trainee)) {
                bool = true;
                break;
            }
            trainees.add(i, trainee);
        }
        return bool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(room, group.room) &&
                Objects.equals(trainees, group.trainees);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, room, trainees);
    }
}
