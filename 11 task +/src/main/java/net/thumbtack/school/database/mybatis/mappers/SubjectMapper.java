package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubjectMapper {

    @Insert("INSERT INTO subject (name) VALUES " + "( #{name} )")
    @Options(useGeneratedKeys = true)
    void insert(Subject subject);

    @Select("SELECT * from subject where id = id")
    Subject getById(@Param("id") int id);

    @Select("SELECT * from subject")
    List<Subject> getAll();

    @Update("UPDATE subject  SET name = #{subject.name} where id = subject.id")
    Integer update(@Param("subject") Subject subject);

    @Delete("DELETE FROM subject WHERE id = #{subject.id}")
    void delete(@Param("subject") Subject subject);

    @Select("SELECT id, name FROM subject WHERE id IN (SELECT subjectId FROM group_subject WHERE groupId = #{group.id})")
    List<Subject> getByGroup(@Param("group") Group group);

    @Delete("DELETE FROM subject")
    void deleteAll();
}
