package com.surge.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.surge.common.PageData;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    private final DishFlavorService dishFlavorService;

    private final DishMapper dishMapper;

    public DishServiceImpl(DishFlavorService dishFlavorService, DishMapper dishMapper) {
        this.dishFlavorService = dishFlavorService;
        this.dishMapper = dishMapper;
    }

    @Override
    public PageData<DishWithCategoryVo> DishManagementList(int page, int pageSize, String name) {
        Page<DishWithCategoryVo> dishPage = PageHelper.startPage(page, pageSize);
        dishMapper.find(name);
        return new PageData<>(dishPage, (int) dishPage.getTotal());
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
        dish.setCode(UUID.randomUUID().toString().replace("-", ""));
        return dishMapper.insert(dish);
    }

    @Override
    public int updateDish(Dish dish, Employee updateUser) {
        dish.setUpdateUser(updateUser.getId());
        dish.setUpdateTime(updateUser.getUpdateTime());
        return dishMapper.update(dish);
    }

    @Override
    public int addDish(DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo, Employee createUser) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishWithCategoryAndFlavorVo, dish);
        int numberOfRows = insertDish(dish, createUser);

        List<DishFlavor> dishFlavorList = new ArrayList<>();
        for (DishFlavorVo dishFlavorVo : dishWithCategoryAndFlavorVo.getFlavors()) {
            DishFlavor dishFlavor = new DishFlavor();
            BeanUtils.copyProperties(dishFlavorVo, dishFlavor);
            dishFlavorList.add(dishFlavor);
        }

        dishFlavorService.insertDishFlavor(dishFlavorList, dish, createUser);
        return numberOfRows;
    }

    @Override
    public int modifyDish(DishWithCategoryAndFlavorVo dishWithCategoryAndFlavorVo, Employee updateUser) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishWithCategoryAndFlavorVo, dish);
        int numberOfRows = updateDish(dish, updateUser);

        List<DishFlavor> dishFlavorList = new ArrayList<>();
        for (DishFlavorVo dishFlavorVo : dishWithCategoryAndFlavorVo.getFlavors()) {
            DishFlavor dishFlavor = new DishFlavor();
            BeanUtils.copyProperties(dishFlavorVo, dishFlavor);
            dishFlavorList.add(dishFlavor);
        }

        dishFlavorService.updateDishFlavor(dishFlavorList, dish, updateUser);
        return numberOfRows;
    }

    @Override
    public int batchEnable(Long[] ids) {
        int rowOfNumber = 0;
        Dish dish = new Dish();
        for (Long id : ids) {
            dish.setId(id);
            dish.setStatus(1);
            dishMapper.update(dish);
            rowOfNumber++;
        }
        return rowOfNumber;
    }

    @Override
    public int batchDisable(Long[] ids) {
        int rowOfNumber = 0;
        Dish dish = new Dish();
        for (Long id : ids) {
            dish.setId(id);
            dish.setStatus(0);
            dishMapper.update(dish);
            rowOfNumber++;
        }
        return rowOfNumber;
    }

    @Override
    public int delete(Long[] ids, Employee updateUser) {
        int rowOfNumber = 0;

        for (Long id : ids) {
            Dish dish = new Dish();
            dish.setId(id);
            dish.setIsDeleted(1);
            rowOfNumber = updateDish(dish, updateUser);

            List<DishFlavor> dishFlavorList = dishFlavorService.findIdByDishId(dish);
            for (DishFlavor dishFlavor : dishFlavorList) {
                dishFlavor.setIsDeleted(1);
            }
            dishFlavorService.updateDishFlavor(dishFlavorList, dish, updateUser);
        }

        return rowOfNumber;
    }

    @Override
    public List<DishListVo> findDishList(Long id) {
        List<Dish> dishList = dishMapper.findDishListById(id);
        return dishList.parallelStream()
                .map(dish -> {
                    DishListVo dishListVo = new DishListVo();
                    BeanUtils.copyProperties(dish, dishListVo);
                    return dishListVo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Dish findIdByName(String name) {
        return dishMapper.findIdByName(name);
    }

}

