package com.lut.mapper;

import com.lut.entity.AddApplyEntity;
import com.lut.entity.SalaryEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;

@Mapper
public interface AdditionMapper {

    @Select("TRUNCATE TABLE addition")
    void truncateAddition();

    @Insert("INSERT INTO addition (emp_id,addition_year, addition_month,child_edu,big_sick,continue_edu,old_man,credit,rent) VALUES(#{empId},#{salaryYear},#{salaryMonth},0,0,0,0,0,0)")
    int setZero(SalaryEntity salaryEntity);

    @Update("UPDATE addition set ${applyType}=#{applyMoney} WHERE emp_id=#{empId} AND addition_year=#{additionYear} AND addition_month=#{additionMonth}")
    int submitApply(HashMap<String, Object> argMap);

}
