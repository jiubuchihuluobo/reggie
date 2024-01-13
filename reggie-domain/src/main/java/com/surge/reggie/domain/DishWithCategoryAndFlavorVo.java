package com.surge.reggie.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DishWithCategoryAndFlavorVo {

    private Long id;

    private String name;

    private BigDecimal price;

    private String image;

    private String description;

    private String categoryName;

    private List<DishFlavorVo> flavors;

}
