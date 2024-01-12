package com.surge.service.impl;

import com.surge.common.Constant;
import com.surge.common.SnowFlakeUtil;
import com.surge.mapper.EmployeeMapper;
import com.surge.reggie.domain.Employee;
import com.surge.service.EmployeeService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication login(String username, String password) {
        Authentication usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        try {
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (AuthenticationException e) {
            return usernamePasswordAuthenticationToken;
        }
    }

    @Override
    public List<Employee> find(String name) {
        return employeeMapper.find(name);
    }

    @Override
    public Employee findById(Long id) {
        return employeeMapper.findById(id);
    }

    @Override
    public Employee save(Employee employee, Employee createUser) {
        if (employee.getId() == null) {
            employee.setId(SnowFlakeUtil.getId());
            employee.setCreateUser(createUser.getId());
            employee.setUpdateUser(createUser.getId());
            employee.setCreateTime(new Date());
            employee.setUpdateTime(new Date());
            employee.setPassword(passwordEncoder.encode(Constant.INIT_PASSWORD));
            employeeMapper.insert(employee);
        } else {
            employee.setUpdateUser(createUser.getId());
            employee.setUpdateTime(new Date());
            employeeMapper.update(employee);
        }
        employee = employeeMapper.findById(employee.getId());
        return employee;
    }

    @Override
    public Employee getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Employee employee) {
                return employee;
            }
        }
        return null;
    }

}
