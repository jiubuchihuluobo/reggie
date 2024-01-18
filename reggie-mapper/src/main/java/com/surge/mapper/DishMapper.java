package com.surge.mapper;

import com.surge.reggie.domain.Dish;
import com.surge.reggie.domain.DishWithCategoryAndFlavorVo;
import com.surge.reggie.domain.DishWithCategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    List<DishWithCategoryVo> find(String name);

    DishWithCategoryAndFlavorVo findById(Long id);

    @Select("SELECT * FROM dish WHERE name = #{name}")
    Dish findIdByName(String name);

    int insert(Dish dish);

    int update(Dish dish);

    List<Dish> findDishListById(Long id);

}
