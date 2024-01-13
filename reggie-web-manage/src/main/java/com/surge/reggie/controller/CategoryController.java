package com.surge.reggie.controller;

import com.surge.common.Response;
import com.surge.reggie.domain.Category;
import com.surge.reggie.domain.Employee;
import com.surge.service.CategoryService;
import com.surge.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final EmployeeService employeeService;

    private final CategoryService categoryService;

    public CategoryController(EmployeeService employeeService, CategoryService categoryService) {
        this.employeeService = employeeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/findAll")
    public Response<Object> findAll() {
        return Response.success(categoryService.findAll());
    }

    @PostMapping
    public Response<Object> save(@RequestBody Category category) {
        Employee employee = employeeService.getCurrentUser();
        return Response.success(categoryService.save(category, employee));
    }

    @PutMapping
    public Response<Object> update(@RequestBody Category category) {
        Employee employee = employeeService.getCurrentUser();
        return Response.success(categoryService.save(category, employee));
    }

    @DeleteMapping
    public Response<Object> delete(@RequestParam Long id) {
        return Response.success(categoryService.delete(id));
    }

}
