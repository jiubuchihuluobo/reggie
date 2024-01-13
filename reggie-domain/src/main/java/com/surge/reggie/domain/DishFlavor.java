package com.surge.reggie.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DishFlavor implements Serializable {

    private Long id;

    private Long dishId;

    private String name;

    private String value;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;

    private Integer isDeleted;

}
