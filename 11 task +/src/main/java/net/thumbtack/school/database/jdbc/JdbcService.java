package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.*;


public class JdbcService {


    public static void insertTrainee(Trainee trainee) throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("insert into  trainee set firstname = ?, lastname = ?, rating = ?", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, trainee.getFirstName());
            preparedStatement.setString(2, trainee.getLastName());
            preparedStatement.setInt(3, trainee.getRating());
            preparedStatement.executeUpdate();
            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                trainee.setId(key.getInt((1)));
            } else {
                throw new SQLException("No ID obtained.");
            }
        }
    }

    public static void updateTrainee(Trainee trainee) throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("update  trainee  set firstname = ?, lastname = ?, rating = ?  where id = ? ")) {
            preparedStatement.setString(1, trainee.getFirstName());
            preparedStatement.setString(2, trainee.getLastName());
            preparedStatement.setInt(3, trainee.getRating());
            preparedStatement.setInt(4, trainee.getId());
            preparedStatement.executeUpdate();
        }
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        Trainee trainee = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select * from trainee where id = ?")) {
            preparedStatement.setInt(1, traineeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                trainee = new Trainee(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("rating"));
            }
        }
        return trainee;
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        Trainee trainee = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select * from trainee where id = ?")) {
            preparedStatement.setInt(1, traineeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                trainee = new Trainee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        }
        return trainee;
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        List<Trainee> traineeList = new LinkedList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select * from trainee"); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                traineeList.add(new Trainee(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("rating")));
            }
        }
        return traineeList;

    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        List<Trainee> traineeList = new LinkedList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select * from trainee"); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                traineeList.add(new Trainee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        }
        return traineeList;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("delete from trainee where id = ?")) {
            preparedStatement.setInt(1, trainee.getId());
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteTrainees() throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("delete from trainee")) {
            preparedStatement.executeUpdate();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("insert into  subject set name = ?", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.executeUpdate();
            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                subject.setId(key.getInt((1)));
            } else {
                throw new SQLException("No ID obtained.");
            }
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        Subject subject = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select * from subject where id = ?")) {
            preparedStatement.setInt(1, subjectId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                subject = new Subject(rs.getInt("id"), rs.getString("name"));
            }
        }
        return subject;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        Subject subject = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select * from subject where id = ?")) {
            preparedStatement.setInt(1, subjectId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                subject = new Subject(rs.getInt(1), rs.getString(2));
            }
        }
        return subject;
    }

    public static void deleteSubjects() throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("delete from subject")) {
            preparedStatement.executeUpdate();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("insert into  school set name = ?, year = ?", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, school.getName());
            preparedStatement.setInt(2, school.getYear());
            preparedStatement.executeUpdate();
            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                school.setId(key.getInt((1)));
            } else {
                throw new SQLException("No ID obtained.");
            }
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        School school = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select * from school where id = ?")) {
            preparedStatement.setInt(1, schoolId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                school = new School(rs.getInt("id"), rs.getString("name"), rs.getInt("year"));
            }
        }
        return school;
    }

    static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        School school = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select * from school where id = ?")) {
            preparedStatement.setInt(1, schoolId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                school = new School(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        }
        return school;
    }

    public static void deleteSchools() throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("delete from school")) {
            preparedStatement.executeUpdate();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("insert into  groups set name = ?, room = ?, schoolId = ?", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getRoom());
            preparedStatement.setInt(3, school.getId());
            preparedStatement.executeUpdate();
            ResultSet key = preparedStatement.getGeneratedKeys();
            if (key.next()) {
                group.setId(key.getInt((1)));
            } else {
                throw new SQLException("No ID obtained.");
            }
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        School school = null;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select s.id as school_Id, s.name as school_name, year, g.id as group_id, g.name as group_name, room from school s left Join groups g on schoolId = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (id == rs.getInt("school_Id")) {
                    if (school == null) {
                        school = new School(id, rs.getString("school_name"), rs.getInt("year"));
                    }
                    school.addGroup(new Group(rs.getInt("group_id"), rs.getString("group_name"), rs.getString("room")));
                }
            }
        }
        return school;
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        List<School> schoolList = new ArrayList<>();
        int id = 0;
        int index = -1;
        School school;
        try (PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement("select s.id as school_Id, s.name as school_name, year, g.id as group_id, g.name as group_name, room from school s left Join groups g on schoolId = s.id"); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                if (id == rs.getInt("school_Id")) {
                    schoolList.get(index).addGroup(new Group(rs.getInt("group_id"), rs.getString("group_name"), rs.getString("room")));
                }
                if (id != rs.getInt("school_Id")) {
                    school = new School(rs.getInt("school_Id"), rs.getString("school_name"), rs.getInt("year"));
                    school.addGroup(new Group(rs.getInt("group_id"), rs.getString("group_name"), rs.getString("room")));
                    schoolList.add(school);
                    index++;
                }
                id = rs.getInt("school_Id");
            }
            return schoolList;
        }
    }
}