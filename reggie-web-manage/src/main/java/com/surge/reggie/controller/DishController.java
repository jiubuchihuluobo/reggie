package com.surge.reggie.controller;

import com.surge.common.Response;
import com.surge.reggie.domain.DishWithCategoryAndFlavorVo;
import com.surge.reggie.domain.Employee;
import com.surge.service.DishService;
import com.surge.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    private final EmployeeService employeeService;

    public DishController(DishService dishService, EmployeeService employeeService) {
        this.dishService = dishService;
        this.employeeService = employeeService;
    }

    @GetMapping("/page")
    public Response<Object> DishManagementList(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String name) {
        return Response.success(dishService.DishManagementList(page, pageSize, name));
    }

    @GetMapping("/{id}")
    public Response<Object> findDish(@PathVariable Long id) {
        return Response.success(dishService.findDish(id));
    }

    @PutMapping
    public Response<Object> modifyDish(@RequestBody DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo) {
        Employee employee = employeeService.getCurrentUser();
        return Response.success(dishService.modifyDish(dishWithCategoryAndFlavorVo, employee));
    }

    @PostMapping
    public Response<Object> addDish(@RequestBody DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo) {
        Employee employee = employeeService.getCurrentUser();
        return Response.success(dishService.addDish(dishWithCategoryAndFlavorVo, employee));
    }

    @PostMapping("/status/{status}")
    public Response<Object> batchEnableOrDisable(@PathVariable int status, @RequestParam Long[] ids) {
        int rowOfNumber = switch (status) {
            case 0 -> dishService.batchDisable(ids);
            case 1 -> dishService.batchEnable(ids);
            default -> 0;
        };
        return Response.success(rowOfNumber);
    }

    @DeleteMapping
    public Response<Object> delete(@RequestParam Long[] ids) {
        Employee employee = employeeService.getCurrentUser();
        return Response.success(dishService.delete(ids, employee));
    }

    @GetMapping("/list")
    public Response<Object> findByDishList(@RequestParam("categoryId") Long id) {
        return Response.success(dishService.findDishList(id));
    }

}
