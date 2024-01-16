package com.surge.reggie.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SetMealListVo {
    private Long id;
    private String categoryName;
    private String name;
    private BigDecimal price;
    private Integer status;
    private String image;
    private Date updateTime;
}
