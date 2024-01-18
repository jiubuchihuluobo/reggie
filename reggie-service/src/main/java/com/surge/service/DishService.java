package com.surge.service;

import com.surge.common.PageData;
import com.surge.reggie.domain.*;

import java.util.List;

public interface DishService {

    PageData<DishWithCategoryVo> DishManagementList(int page, int pageSize, String name);

    DishWithCategoryAndFlavorVo findDish(Long id);

    int insertDish(Dish dish, Employee createUser);

    int updateDish(Dish dish, Employee updateUser);

    int addDish(DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo, Employee crateUser);

    int modifyDish(DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo, Employee updateUser);

    int batchEnable(Long[] ids);

    int batchDisable(Long[] ids);

    int delete(Long[] ids, Employee updateUser);

    List<DishListVo> findDishList(Long id);

    Dish findIdByName(String name);

}
