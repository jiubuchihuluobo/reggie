package com.surge.reggie.controller;

import com.surge.common.Response;
import com.surge.reggie.domain.Employee;
import com.surge.reggie.domain.SetMealModifyVo;
import com.surge.service.EmployeeService;
import com.surge.service.SetMealService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    private final SetMealService setMealService;

    private final EmployeeService employeeService;

    public SetMealController(SetMealService setMealService, EmployeeService employeeService) {
        this.setMealService = setMealService;
        this.employeeService = employeeService;
    }

    @GetMapping("/page")
    public Response<Object> setMealList(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String name) {
        return Response.success(setMealService.setMealList(page, pageSize, name));
    }

    @GetMapping("/{id}")
    public Response<Object> findSetMealWithDishById(@PathVariable Long id) {
        return Response.success(setMealService.findSetMealWithDishById(id));
    }

    @PutMapping
    public Response<Object> modifySetMeal(@RequestBody SetMealModifyVo setMealModifyVo) {
        Employee employee = employeeService.getCurrentUser();
        return Response.success(setMealService.modifySetMeal(setMealModifyVo, employee));
    }

    @PostMapping
    public Response<Object> addSetMeal(@RequestBody SetMealModifyVo setMealModifyVo) {
        Employee employee = employeeService.getCurrentUser();
        return Response.success(setMealService.addSetMeal(setMealModifyVo, employee));
    }

}
