package com.surge.service.impl;

import com.surge.common.Response;
import com.surge.mapper.EmployeeMapper;
import com.surge.reggie.domain.Employee;
import com.surge.service.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder) {
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Response<Object> login(String username, String password) {
        Employee employee = employeeMapper.findByUserName(username);
        if (employee != null && passwordEncoder.matches(password, employee.getPassword())) {
            return Response.success(employee);
        } else {
            return Response.failure("用户名或密码错误");
        }
    }

}
