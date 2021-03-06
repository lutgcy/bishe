package com.lut.mapper;

import com.lut.entity.IncomeTaxEntity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface IncomeTaxMapper {

    @Select("TRUNCATE TABLE income_tax")
    void truncateTable();

    @Select("SELECT SUM(tax_money) FROM income_tax WHERE emp_id=#{empId} AND tax_year=#{taxYear} AND tax_month<#{taxMonth}")
    BigDecimal getPaidTaxMoney(@Param("empId") Integer empId, @Param("taxYear") Integer taxYear, @Param("taxMonth") Integer taxMonth);

    @Insert("INSERT INTO income_tax (emp_id, tax_year, tax_month, taxable_income, tax_money) VALUES(#{empId}, #{taxYear}, #{taxMonth}, #{taxableIncome}, #{taxMoney})")
    int insertIncomeTax(IncomeTaxEntity incomeTax);

    List<Map<String, Object>> searchIncomeTax(HashMap<String, Object> condition);

    @Delete("DELETE FROM income_tax WHERE tax_year=#{year} AND tax_month=#{month}")
    int deleteIncomeTaxNewMonth(@Param("year") Integer year, @Param("month") Integer month);
}
