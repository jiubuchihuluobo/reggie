package com.surge.reggie.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DishWithCategoryVo {

    private Long id;

    private String name;

    private Long categoryId;

    private BigDecimal price;

    private String code;

    private String image;

    private String description;

    private Integer status;

    private Integer sort;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;

    private Integer isDeleted;

    private String categoryName;

}
