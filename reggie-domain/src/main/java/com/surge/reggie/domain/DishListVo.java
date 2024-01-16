package com.surge.reggie.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishListVo {
    private Long id;
    private String name;
    private BigDecimal price;
}
