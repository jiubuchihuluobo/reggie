package com.surge.reggie.controller;

import com.surge.common.Constant;
import com.surge.common.Response;
import com.surge.reggie.domain.Employee;
import com.surge.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    private final SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();

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

    @PostMapping("/logout")
    public Response<Object> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        securityContextLogoutHandler.logout(request, response, authentication);
        return Response.success(null);
    }

    @GetMapping("/find")
    public Response<Object> find(@RequestParam(name = "name", required = false) String name) {
        return Response.success(employeeService.find(name));
    }

    @GetMapping("/{id}")
    public Response<Object> findById(@PathVariable Long id) {
        return Response.success(employeeService.findById(id));
    }

    @PostMapping
    public Response<Object> save(@RequestBody Employee employee) {
        Employee createUser = employeeService.getCurrentUser();
        return Response.success(employeeService.save(employee, createUser));
    }

    @PutMapping
    public Response<Object> update(@RequestBody Employee employee) {
        Employee createUser = employeeService.getCurrentUser();
        return Response.success(employeeService.save(employee, createUser));
    }

}
