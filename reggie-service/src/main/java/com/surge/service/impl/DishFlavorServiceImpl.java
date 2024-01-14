package com.surge.service.impl;

import com.surge.common.SnowFlakeUtil;
import com.surge.mapper.DishFlavorMapper;
import com.surge.reggie.domain.Dish;
import com.surge.reggie.domain.DishFlavor;
import com.surge.reggie.domain.Employee;
import com.surge.service.DishFlavorService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DishFlavorServiceImpl implements DishFlavorService {

    private final DishFlavorMapper dishFlavorMapper;

    public DishFlavorServiceImpl(DishFlavorMapper dishFlavorMapper) {
        this.dishFlavorMapper = dishFlavorMapper;
    }

    @Override
    public int insertDishFlavor(List<DishFlavor> dishFlavorList, Dish dish, Employee createUser) {
        for (DishFlavor dishFlavor : dishFlavorList) {
            dishFlavor.setId(SnowFlakeUtil.getId());
            dishFlavor.setDishId(dish.getId());
            dishFlavor.setCreateUser(createUser.getId());
            dishFlavor.setUpdateUser(createUser.getId());
            dishFlavor.setCreateTime(new Date());
            dishFlavor.setUpdateTime(new Date());
        }
        return dishFlavorMapper.insert(dishFlavorList);
    }

    @Override
    public int updateDishFlavor(List<DishFlavor> dishFlavorList, Dish dish, Employee updateUser) {
        int i;
        for (i = 0; i < dishFlavorList.size(); i++) {
            DishFlavor dishFlavor = dishFlavorList.get(i);
            dishFlavor.setUpdateUser(updateUser.getId());
            dishFlavor.setUpdateTime(new Date());
            dishFlavorMapper.update(dishFlavor);
        }
        return i;
    }

}
