package com.lut.mapper;

import com.lut.entity.EmployeeEntity;
import com.lut.entity.HumanResource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE username = #{username}")
    EmployeeEntity getEmployeeInfoByUsername(@Param("username") String username);

    @Select("SELECT post_name AS postName, COUNT(*) AS count FROM employee LEFT JOIN post ON employee.post_id=post.post_id GROUP BY post.post_id ORDER BY count DESC")
    List<Map<String, Object>> countPostRate();

    @Select("SELECT INTERVAL(avgSalary,17000,18000,19000,20000,21000,22000,23000,100000) num,COUNT(1) count FROM(SELECT  SUM(post_salary+base_salary)/count(1) as avgSalary FROM salary GROUP BY emp_id) as aaa GROUP BY INTERVAL(avgSalary,17000,18000,19000,20000,21000,22000,23000,100000)")
    List<Map<String, Object>> salaryInterval();

    List<Map<String, Object>> searchEmployees(HashMap<String, Object> condition);

    @Select("SELECT username, pwd_salt, pwd_hash FROM employee WHERE username = #{username}")
    EmployeeEntity getLoginInfoFromEmployee(@Param("username") String username);

    @Select("SELECT emp_id,username,emp_name,emp_sex,emp_email,emp_telephone,emp_address,emp_idnumber,emp_bank,emp_introduction,emp_hiredate,dept_name,post_name,dept.dept_id,post.post_id " +
            "FROM (employee LEFT JOIN dept ON employee.dept_id = dept.dept_id) LEFT JOIN post ON employee.post_id=post.post_id")
    List<Map<String, Object>> getAllEmp();

    @Insert("INSERT INTO employee(username,pwd_hash,dept_id,post_id,emp_name,emp_sex,emp_email,emp_telephone,emp_address,emp_idnumber,emp_bank,emp_hiredate,emp_introduction) " +
            "VALUES(#{username},'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a',#{deptId},#{postId},#{empName},#{empSex},#{empEmail},#{empTelephone},#{empAddress},#{empIdnumber},#{empBank},#{empHiredate},#{empIntroduction})")
    int insertEmp(EmployeeEntity employee);

    @Update("UPDATE employee set username=#{username},dept_id=#{deptId},post_id=#{postId},emp_name=#{empName},emp_sex=#{empSex},emp_email=#{empEmail},emp_telephone=#{empTelephone},emp_address=#{empAddress}," +
            "emp_introduction=#{empIntroduction},emp_hiredate=#{empHiredate},emp_idnumber=#{empIdnumber},emp_bank=#{empBank} WHERE emp_id=#{empId}")
    int updateEmp(EmployeeEntity employee);

    @Delete("DELETE FROM employee WHERE emp_id = #{empId}")
    int deleteEmp(Integer empId);

    @Select("SELECT emp_id FROM employee WHERE username=#{username}")
    Integer getEmpIdFormUsername(String username);

    @Update("UPDATE employee SET pwd_hash = #{newPasswordSHA256} WHERE username=#{username}")
    int updatePassword(@Param("newPasswordSHA256")String newPasswordSHA256, @Param("username") String username);

    @Update("UPDATE employee SET pwd_hash = #{defaultPassword} WHERE emp_id=#{empId}")
    int resetPwd(@Param("defaultPassword") String defaultPassword, @Param("empId") String empId);
}
