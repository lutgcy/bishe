package com.lut.mapper;

import com.lut.entity.IncomeTaxEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IncomeTaxMapper {

    @Select("TRUNCATE TABLE income_tax")
    void truncateTable();

    @Insert("INSERT INTO income_tax (emp_id, tax_year, tax_month, taxable_income, tax_money) VALUES(#{empId}, #{taxYear}, #{taxMonth}, #{taxableIncome}, #{taxMoney})")
    int insertIncomeTax(IncomeTaxEntity incomeTax);
}
