package com.surge.service.impl;

import com.surge.mapper.EmployeeMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeMapper employeeMapper;

    public UserDetailsServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = employeeMapper.findByUserName(username);
        if (userDetails != null) {
            return userDetails;
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
