package com.surge.service;

import com.surge.reggie.domain.Employee;
import com.surge.reggie.domain.SetMealDish;

import java.util.List;

public interface SetMealDishService {

    int insert(List<SetMealDish> setMealDish, Employee createUser);

    int delete(Long id);

    int update(SetMealDish setMealDish, Employee updateUser);

}
