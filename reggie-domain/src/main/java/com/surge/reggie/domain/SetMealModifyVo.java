package com.surge.reggie.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SetMealModifyVo {
    private Long id;
    private Long categoryId;
    private String name;
    private BigDecimal price;
    private Integer status;
    private String description;
    private String image;
    private List<DishModifyVo> setmealDishes;
}
