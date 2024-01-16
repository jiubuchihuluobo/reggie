package com.surge.mapper;

import com.surge.reggie.domain.SetMeal;

import java.util.List;

public interface SetMealMapper {

    int insert(SetMeal setMeal);

    int deleteById(Long id);

    int update(SetMeal setMeal);

    SetMeal findById(Long id);

    List<SetMeal> find(String name);

}
