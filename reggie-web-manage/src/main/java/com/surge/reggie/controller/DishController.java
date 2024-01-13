package com.surge.reggie.controller;

import com.surge.common.Response;
import com.surge.service.DishService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/page")
    public Response<Object> DishManagementList(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String name) {
        return Response.success(dishService.DishManagementList(page, pageSize, name));
    }

    @GetMapping("/{id}")
    public Response<Object> findDish(@PathVariable Long id) {
        return Response.success(dishService.findDish(id));
    }

}
