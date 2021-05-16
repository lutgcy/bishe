package com.lut.mapper;

import com.lut.entity.AddApplyEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface AddApplyMapper {

    List<Map<String, Object>> searchAddApply(HashMap<String, Object> condition);

    @Insert("INSERT INTO add_apply (emp_id, apply_type, apply_money, apply_date) VALUES(#{obj.empId}, #{obj.applyType}, #{obj.applyMoney}, #{obj.applyDate})")
    @Options(useGeneratedKeys = true, keyProperty = "obj.id", keyColumn = "id")
    int insertAddApply(@Param("obj") AddApplyEntity applyEntity);

    @Update("UPDATE add_apply SET apply_file = #{applyFile} WHERE id=#{id}")
    int setApplyFile(@Param("applyFile") String applyFile, @Param("id") Integer id);

    @Update("UPDATE add_apply SET apply_type = #{applyType}, apply_money = #{applyMoney}, apply_date = #{applyDate} WHERE id = #{id}")
    int alterApply(AddApplyEntity applyEntity);

    @Update("UPDATE add_apply SET apply_state=1 WHERE id=#{id}")
    int agreeApply(Integer id);

    @Update("UPDATE add_apply SET apply_state=2 WHERE id=#{id}")
    int rejectApply(Integer id);

    @Select("SELECT * FROM add_apply WHERE id=#{id}")
    AddApplyEntity selectAddApply(Integer id);

    @Select("SELECT * FROM add_apply WHERE apply_state=1")
    List<AddApplyEntity> selectAddApplyByState();

}
