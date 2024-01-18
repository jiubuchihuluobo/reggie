package com.surge.mapper;

import com.surge.reggie.domain.SetMealDish;

import java.util.List;

public interface SetMealDishMapper {

    int insert(List<SetMealDish> setMealDish);

    int update(SetMealDish setMealDish);

    int deleteBySetMealId(Long id);

}
