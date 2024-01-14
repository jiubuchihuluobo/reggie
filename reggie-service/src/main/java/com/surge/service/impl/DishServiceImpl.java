package com.surge.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.surge.common.PageInfo;
import com.surge.common.SnowFlakeUtil;
import com.surge.mapper.DishMapper;
import com.surge.reggie.domain.*;
import com.surge.service.DishFlavorService;
import com.surge.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishFlavorService dishFlavorService;

    private final DishMapper dishMapper;

    public DishServiceImpl(DishFlavorService dishFlavorService, DishMapper dishMapper) {
        this.dishFlavorService = dishFlavorService;
        this.dishMapper = dishMapper;
    }

    @Override
    public PageInfo<DishWithCategoryVo> DishManagementList(int page, int pageSize, String name) {
        Page<DishWithCategoryVo> dishPage = PageHelper.startPage(page, pageSize);
        dishMapper.find(name);
        return new PageInfo<>(dishPage, (int) dishPage.getTotal());
    }

    @Override
    public DishWithCategoryAndFlavorVo findDish(Long id) {
        return dishMapper.findById(id);
    }

    @Override
    public int insertDish(Dish dish, Employee createUser) {
        dish.setId(SnowFlakeUtil.getId());
        dish.setCreateTime(new Date());
        dish.setUpdateTime(new Date());
        dish.setCreateUser(createUser.getId());
        dish.setUpdateUser(createUser.getId());
        return dishMapper.insert(dish);
    }

    @Override
    public int updateDish(Dish dish, Employee updateUser) {
        dish.setUpdateUser(updateUser.getId());
        dish.setUpdateTime(updateUser.getUpdateTime());
        // TODO 临时策略


        return dishMapper.update(dish);
    }

    @Override
    public DishWithCategoryAndFlavorVo addDish(DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo) {
        return null;
    }

    @Override
    public int modifyDish(DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo, Employee updateUser) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishWithCategoryAndFlavorVo, dish);
        int numberOfRows = updateDish(dish, updateUser);

        List<DishFlavor> dishFlavorList = new ArrayList<>();
        for (DishFlavorVo dishFlavorVo : dishWithCategoryAndFlavorVo.getFlavors()) {
            DishFlavor dishFlavor = new DishFlavor();
            BeanUtil.copyProperties(dishFlavorVo, dishFlavor);
            dishFlavorList.add(dishFlavor);
        }
        dishFlavorService.updateDishFlavor(dishFlavorList, dish, updateUser);

        return numberOfRows;
    }

}

