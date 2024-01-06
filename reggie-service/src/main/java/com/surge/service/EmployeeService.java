package com.surge.service;

import com.surge.common.Response;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Response<Object> login(String username, String password);

}
