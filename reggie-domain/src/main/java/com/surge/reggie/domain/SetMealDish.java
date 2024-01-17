package com.surge.reggie.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SetMealDish {
    private Long id;
    private String setmealId;
    private String dishId;
    private String name; // 菜品名称（冗余字段）
    private BigDecimal price; // 菜品原价（冗余字段）
    private Integer copies; // 份数
    private Integer sort; // 排序
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间
    private Long createUser; // 创建人
    private Long updateUser; // 修改人
    private Integer isDeleted; // 是否删除
}
