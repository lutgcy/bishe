package com.lut.mapper;

import com.lut.entity.SalaryEntity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface SalaryMapper {

    //    @Insert("INSERT INTO salary (emp_id,salary_year,salary_month,post_salary,base_salary) VALUES(#{},#{},#{},#{},#{})")
    int insertInitSalary(List<SalaryEntity> list);

    @Select("SELECT * FROM salary")
    List<SalaryEntity> getWholeSalary();

    @Select("SELECT * FROM salary WHERE salary_year=#{year} AND salary_month=#{month}")
    List<SalaryEntity> getSalaryByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

    @Select("SELECT salary_month, post_salary + base_salary as salary FROM salary WHERE emp_id=#{empId} AND salary_year=#{salaryYear}")
    List<Map<String, Object>> querySalaryByEmpIdAndYear(@Param("empId") Integer empId, @Param("salaryYear") Integer salaryYear);

    @Update("UPDATE salary SET real_salary=#{realSalary} WHERE emp_id=#{id} AND salary_year=#{year} AND salary_month=#{month}")
    int setRealSalary(@Param("realSalary") BigDecimal realSalary, @Param("id") Integer id, @Param("year") Integer year, @Param("month") Integer month);

    /*@Select("SELECT employee.emp_id,employee.emp_name,post.post_name,dept.dept_name,salary_year,salary.salary_month,salary.base_salary,salary.post_salary,special.per_old,special.per_medical,special.per_fire,special.per_house," +
            "child_edu,big_sick,continue_edu,old_man,credit,rent,taxable_income,tax_money,real_salary " +
            "FROM (((((salary LEFT JOIN employee ON employee.emp_id=salary.emp_id) " +
            "LEFT JOIN post ON employee.post_id=post.post_id) LEFT JOIN dept ON employee.dept_id=dept.dept_id) " +
            "LEFT JOIN special ON special.emp_id=salary.emp_id AND special.special_year=salary.salary_year AND special.special_month=salary.salary_month) " +
            "LEFT JOIN addition ON addition.emp_id=salary.emp_id AND addition.addition_year=salary.salary_year AND addition.addition_month=salary.salary_month) " +
            "LEFT JOIN income_tax ON income_tax.emp_id=salary.emp_id AND income_tax.tax_year=salary.salary_year AND income_tax.tax_month=salary.salary_month")*/
    List<Map<String, Object>> queryAllSalaryInfo(HashMap<String, Object> condition);

    @Select("SELECT salary_year,MAX(salary_month) as salary_month FROM salary WHERE salary_year = (SELECT MAX(salary_year) FROM salary)")
    Map<String, Integer> getNextMonthDate();

    @Select("SELECT employee.emp_id,post.post_salary as salary FROM employee LEFT JOIN post ON employee.post_id=post.post_id")
    List<Map<String, Object>> getSalaryForNextMonth();

    @Delete("DELETE FROM salary WHERE salary_year=#{year} AND salary_month=#{month}")
    int deleteSalaryNewMonth(@Param("year") Integer year, @Param("month") Integer month);

    @Update("UPDATE salary SET real_salary=NULL WHERE salary_year=#{year} AND salary_month=#{month}")
    int setLastMonthRealSalaryZero(@Param("year") Integer year, @Param("month") Integer month);

}
