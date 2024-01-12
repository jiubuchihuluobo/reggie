package com.surge.service;

import com.surge.reggie.domain.Employee;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface EmployeeService {

    Authentication login(String username, String password);

    List<Employee> find(String name);

    Employee findById(Long id);

    Employee save(Employee employee, Employee createUser);

    Employee getCurrentUser();

}
