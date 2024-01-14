package com.surge.service;

import com.surge.reggie.domain.Dish;
import com.surge.reggie.domain.DishFlavor;
import com.surge.reggie.domain.Employee;

import java.util.List;

public interface DishFlavorService {

    int insertDishFlavor(List<DishFlavor> dishFlavorList, Dish dish, Employee createUser);

    int updateDishFlavor(List<DishFlavor> dishFlavorList, Dish dish, Employee createUser);

}
