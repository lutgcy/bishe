package com.lut.mapper;

import com.lut.entity.SalaryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface SalaryMapper {

    @Select("SELECT * FROM salary")
    List<SalaryEntity> getWholeSalary();

    @Select("SELECT salary_month, post_salary + base_salary as salary FROM salary WHERE emp_id=#{empId} AND salary_year=#{salaryYear}")
    List<Map<String, Object>> querySalaryByEmpIdAndYear(@Param("empId") Integer empId, @Param("salaryYear") Integer salaryYear);

}
