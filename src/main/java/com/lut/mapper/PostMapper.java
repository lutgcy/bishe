package com.lut.mapper;

import com.lut.entity.PostEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

    @Select("SELECT post.post_id,post.create_time,post.post_name,post.create_time,post.post_salary,dept.dept_id,dept.dept_name FROM post LEFT JOIN dept ON post.dept_id=dept.dept_id")
    List<Map<String, Object>> getAllPost();

    @Select("SELECT post_id,post_name FROM post")
    List<Map<String, Object>> getPostIdAndName();

    @Insert("INSERT INTO post (post_name, post_salary, create_time, dept_id) VALUES(#{postName}, #{postSalary}, SYSDATE(), #{deptId})")
    int insertPost(PostEntity postEntity);

    @Update("UPDATE post SET post_name=#{postName}, post_salary=#{postSalary}, dept_id=#{deptId} WHERE post_id=#{postId}")
    int updatePost(PostEntity postEntity);

    @Delete("DELETE FROM post WHERE post_id = #{postId}")
    int deletePost(Integer posId);
}
