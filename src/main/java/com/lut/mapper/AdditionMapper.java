package com.lut.mapper;

import com.lut.entity.SalaryEntity;
import com.lut.entity.SpecialEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdditionMapper {

    @Select("TRUNCATE TABLE addition")
    void truncateAddition();

    @Insert("INSERT INTO addition (emp_id,addition_year, addition_month,child_edu,big_sick,continue_edu,old_man,credit,rent) VALUES(#{empId},#{salaryYear},#{salaryMonth},0,0,0,0,0,0)")
    int setZero(SalaryEntity salaryEntity);

    int insertNewAdditionWithZero(List<SpecialEntity> list);

    @Update("UPDATE addition set ${applyType}=#{applyMoney} WHERE emp_id=#{empId} AND addition_year=#{additionYear} AND addition_month=#{additionMonth}")
    int submitApply(HashMap<String, Object> argMap);

    @Select("SELECT addition_month, child_edu+big_sick+continue_edu+old_man+credit+rent as additionSum FROM addition WHERE emp_id=#{empId} AND addition_year=#{AdditionYear}")
    List<Map<String, Object>> queryAdditionByEmpIdAndYear(@Param("empId") Integer empId, @Param("AdditionYear") Integer AdditionYear);

    @Delete("DELETE FROM addition WHERE addition_year=#{year} AND addition_month=#{month}")
    int deleteAdditionNewMonth(@Param("year") Integer year, @Param("month") Integer month);
}
