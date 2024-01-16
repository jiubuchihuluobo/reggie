package com.surge.reggie.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SetMeal {
    private Long id;
    private Long categoryId;
    private String categoryName;
    private String name;
    private BigDecimal price;
    private Integer status;
    private String code;
    private String description;
    private String image;
    private Date createTime;
    private Date updateTime;
    private Long createUser;
    private Long updateUser;
    private Integer isDeleted;
}
