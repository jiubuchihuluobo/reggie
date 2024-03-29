package com.surge.reggie.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Category implements Serializable {

    private Long id;

    private int type;

    private String name;

    private int sort;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;

}
