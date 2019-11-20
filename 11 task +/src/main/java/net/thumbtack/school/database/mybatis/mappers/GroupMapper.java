package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GroupMapper {

    @Insert("INSERT INTO groups ( name, room, schoolId) VALUES " +
            "( #{group.name}, #{group.room}, #{school.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    void insert(@Param("school") School school, @Param("group") Group group);

    @Update("UPDATE groups SET name = #{group.name}, room = #{group.room} WHERE id = #{group.id}")
    void update(@Param("group") Group group);

    @Update("UPDATE trainee SET trainee.groupId = #{group.id}")
    void moveTraineeToGroup(@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Update("UPDATE trainee SET groupId = NULL WHERE id = trainee.id ")
    void deleteTraineeFromGroup(@Param("trainee") Trainee trainee);

    @Select("SELECT * from groups")
    List<Group> getAll();

    @Select("SELECT id, name, room FROM groups WHERE schoolId = #{school.id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup", fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup", fetchType = FetchType.LAZY))
    })
    List<Group> getBySchool(School school);

    @Delete("DELETE FROM groups WHERE id = #{group.id}")
    void delete(@Param("group") Group group);

    @Update("INSERT INTO group_subject (groupId, subjectId) VALUES " +
            "( #{group.id}, #{subject.id})")
    void addSubjectToGroup(@Param("group") Group group, @Param("subject") Subject subject);
}
