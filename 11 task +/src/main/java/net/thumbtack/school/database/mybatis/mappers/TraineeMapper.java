package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface TraineeMapper {

    @Insert("INSERT INTO trainee ( firstname, lastname, rating, groupId) VALUES " +
            "( #{trainee.firstName}, #{trainee.lastName}, #{trainee.rating}, #{group.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "trainee.id")
    void insert(@Param("trainee") Trainee trainee, @Param("group") Group group);

    @Select("SELECT * from trainee where id = id")
    Trainee getById(@Param("id") int id);

    @Select("SELECT id, firstname, lastname, rating FROM trainee WHERE groupId = #{group.id}")
    List<Trainee> getByGroup(Group group);

    @Select("SELECT * from trainee")
    List<Trainee> getAll();

    @Insert({"<script>",
            "INSERT INTO trainee (firstname, lastname, rating) VALUES",
            "<foreach item='item' collection='list' separator=','>",
            "( #{item.firstName}, #{item.lastName}, #{item.rating} )",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(@Param("list") List<Trainee> traineeList);

    @Select({"<script>",
            "SELECT id, firstname, lastname, rating FROM trainee",
            "<where>" +
                    "<if test='firstName!= null'> firstName = #{firstName}",
            "</if>",
            "<if test='lastName != null'> AND lastName like #{lastName}",
            "</if>",
            "<if test='rating != null'> AND rating like #{rating}",
            "</if>",
            "</where>" +
                    "</script>"})
    List<Trainee> getAllWithParams(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("rating") Integer rating);

    @Update("UPDATE trainee SET firstname = #{trainee.firstName}, lastname = #{trainee.lastName}," +
            " rating = #{trainee.rating} where id = #{trainee.id}")
    void update(@Param("trainee") Trainee trainee);

    @Delete("DELETE FROM trainee WHERE id = #{trainee.id}")
    void delete(@Param("trainee") Trainee trainee);

    @Delete("DELETE FROM trainee")
    void deleteAll();
}
