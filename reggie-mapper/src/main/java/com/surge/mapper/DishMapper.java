package com.surge.mapper;

import com.surge.reggie.domain.Dish;
import com.surge.reggie.domain.DishWithCategoryAndFlavorVo;
import com.surge.reggie.domain.DishWithCategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {

    List<DishWithCategoryVo> find(String name);

    DishWithCategoryAndFlavorVo findById(Long id);

    int insert(Dish dish);

    int update(Dish dish);

    List<Dish> findDishListById(Long id);

}
