package com.surge.service;

import com.surge.common.Response;

public interface EmployeeService {

    Response<Object> login(String username, String password);

}
