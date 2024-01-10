package com.surge.service;

import org.springframework.security.core.Authentication;

public interface EmployeeService {

    Authentication login(String username, String password);

}
