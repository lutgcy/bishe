package com.lut.mapper;

import com.lut.entity.SpecialEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface SpecialMapper {


    @Select("TRUNCATE TABLE special")
    void truncateTable();

    @Select("SELECT special.emp_id,employee.emp_name,special_year,special_month,per_house,comp_house,per_fire,comp_fire,comp_birth,per_medical,comp_medical,comp_hurt,per_old,comp_old FROM special LEFT JOIN employee ON special.emp_id=employee.emp_id")
    List<Map<String, Object>> getAllSpecials();

//    @Select("")SELECT special.emp_id,employee.emp_name,special_year,special_month,per_house,comp_house,per_fire,comp_fire,comp_birth,per_medical,comp_medical,comp_hurt,per_old,comp_old FROM special LEFT JOIN employee ON special.emp_id=employee.emp_id WHERE special.emp_id=1 AND employee.emp_name='戴坤' AND special_year=2019 AND special_month=1
    List<Map<String, Object>> searchSpecials(HashMap<String, Object> condition);

    @Insert("INSERT INTO special (emp_id,special_year,special_month,per_house,comp_house,per_fire,comp_fire,comp_birth,per_medical,comp_medical,comp_hurt,per_old,comp_old) " +
            "VALUES(#{empId},#{spacialYear},#{specialMonth},#{perHouse},#{compHouse},#{perFire},#{compFire},#{compBirth},#{perMedical},#{compMedical},#{compHurt},#{perOld},#{compOld})")
    int insertSpecial(SpecialEntity specialEntity);


}
