package com.surge.service.impl;

import com.surge.mapper.EmployeeMapper;
import com.surge.reggie.domain.Employee;
import com.surge.service.EmployeeService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

}
