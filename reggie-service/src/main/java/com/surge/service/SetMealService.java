package com.surge.service;

import com.surge.common.PageData;
import com.surge.reggie.domain.Employee;
import com.surge.reggie.domain.SetMeal;
import com.surge.reggie.domain.SetMealListVo;
import com.surge.reggie.domain.SetMealModifyVo;

public interface SetMealService {

    PageData<SetMealListVo> setMealList(int page, int pageSize, String name);

    SetMealModifyVo findSetMealWithDishById(Long id);

    int insert(SetMeal setMeal, Employee createUser);

    int update(SetMeal setMeal, Employee updateUser);

    int addSetMeal(SetMealModifyVo setMealModifyVo, Employee crateUser);

    int modifySetMeal(SetMealModifyVo setMealModifyVo, Employee updateUser);

}
