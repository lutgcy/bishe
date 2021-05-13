package com.lut.mapper;

import com.lut.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper {

    @Select("SELECT dept.dept_id, dept.dept_name, dept.create_time, dept.leader_id, employee.emp_name, employee.emp_email, employee.emp_telephone FROM dept LEFT JOIN employee ON leader_id = emp_id ORDER BY dept.dept_id")
    List<Map<String, Object>> getAllDept();

    @Select("SELECT dept_id, dept_name FROM dept")
    List<Map<String, Object>> getDeptIdAndName();

    @Insert("INSERT INTO dept (dept_name, create_time, leader_id) VALUES(#{deptName}, SYSDATE(), #{leaderId})")
    int insertDept(@Param("deptName") String deptName, @Param("leaderId") Integer leaderId);

    @Update("UPDATE dept SET dept_name=#{deptName}, leader_id=#{leaderId} WHERE dept_id=#{deptId}")
    int updateDept(Department department);

    @Delete("DELETE FROM dept WHERE dept_id = #{dept_id}")
    int deleteDept(Integer dept_id);

}
