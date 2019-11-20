package net.thumbtack.school.ttschool;

public class TrainingException extends Exception {

    private TrainingErrorCode trainingErrorCode;

    TrainingException(TrainingErrorCode trainingErrorCode) {

        this.trainingErrorCode = trainingErrorCode;
    }

    public TrainingErrorCode getErrorCode() {

        return trainingErrorCode;
    }
}
