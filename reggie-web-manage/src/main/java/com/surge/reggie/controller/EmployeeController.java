package com.surge.reggie.controller;

import com.surge.common.Constant;
import com.surge.common.Response;
import com.surge.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public Response<Object> login(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) {
        String username = (String) requestBody.get("username");
        String password = (String) requestBody.get("password");

        Authentication authentication = employeeService.login(username, password);

        if (authentication.isAuthenticated()) {
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);
            request.getSession().setAttribute(Constant.SESSION_EMPLOYEE, authentication.getPrincipal());
            return Response.success(authentication.getPrincipal());
        } else {
            return Response.failure("认证失败");
        }
    }

}
