package com.surge.reggie.domain;

import lombok.Data;

import java.util.Date;


@Data
public class Employee {

    private Long id;

    private String name;

    private String username;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;

}
