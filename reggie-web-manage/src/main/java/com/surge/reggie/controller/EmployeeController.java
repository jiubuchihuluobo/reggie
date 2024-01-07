package com.surge.reggie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

//    private final EmployeeService employeeService;
//
//    private final HttpSession httpSession;
//
//    public EmployeeController(EmployeeService employeeService, HttpSession httpSession) {
//        this.employeeService = employeeService;
//        this.httpSession = httpSession;
//    }
//
//    @PostMapping("/login")
//    public Response<Object> login(@RequestBody Map<String, Object> requestBody) {
//        String username = (String) requestBody.get("username");
//        String password = (String) requestBody.get("password");
//
//        Response<Object> response = employeeService.login(username, password);
//
//        if (response.getCode() == 1 && response.getData() instanceof Employee employee) {
//            httpSession.setAttribute(Constant.SESSION_EMPLOYEE, employee);
//        }
//
//        return response;
//    }

}
