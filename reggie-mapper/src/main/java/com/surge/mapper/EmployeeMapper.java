package com.surge.mapper;

import com.surge.reggie.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    Employee findByUserName(String username);

    List<Employee> findAll();

}
