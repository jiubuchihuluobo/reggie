package com.surge.reggie.controller;

import com.surge.common.Response;
import com.surge.service.SetMealService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    private final SetMealService setMealService;

    public SetMealController(SetMealService setMealService) {
        this.setMealService = setMealService;
    }

    @GetMapping("/page")
    public Response<Object> setMealList(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String name) {
        return Response.success(setMealService.setMealList(page, pageSize, name));
    }

    @GetMapping("/{id}")
    public Response<Object> findSetMealWithDishById(@PathVariable Long id) {
        return Response.success(setMealService.findSetMealWithDishById(id));
    }

}
