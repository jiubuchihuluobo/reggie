package com.surge.service;

import com.surge.common.PageInfo;
import com.surge.reggie.domain.Dish;
import com.surge.reggie.domain.DishWithCategoryAndFlavorVo;
import com.surge.reggie.domain.DishWithCategoryVo;
import com.surge.reggie.domain.Employee;

public interface DishService {

    PageInfo<DishWithCategoryVo> DishManagementList(int page, int pageSize, String name);

    DishWithCategoryAndFlavorVo findDish(Long id);

    int insertDish(Dish dish, Employee createUser);

    int updateDish(Dish dish, Employee updateUser);

    int addDish(DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo, Employee crateUser);

    int modifyDish(DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo, Employee updateUser);

    int batchEnable(Long[] ids);

    int batchDisable(Long[] ids);

}
