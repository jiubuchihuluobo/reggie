package com.surge.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.surge.common.PageData;
import com.surge.mapper.DishMapper;
import com.surge.mapper.SetMealMapper;
import com.surge.reggie.domain.*;
import com.surge.service.DishService;
import com.surge.service.SetMealDishService;
import com.surge.service.SetMealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetMealServiceImpl implements SetMealService {

    private final SetMealMapper setMealMapper;

    private final SetMealDishService setMealDishService;

    private final DishService dishService;

    public SetMealServiceImpl(SetMealMapper setMealMapper, DishMapper dishMapper, SetMealDishService setMealDishService, DishService dishService) {
        this.setMealMapper = setMealMapper;
        this.setMealDishService = setMealDishService;
        this.dishService = dishService;
    }

    @Override
    public PageData<SetMealListVo> setMealList(int page, int pageSize, String name) {
        try (Page<SetMeal> setMealPage = PageHelper.startPage(page, pageSize)) {
            setMealMapper.find(name);
            List<SetMealListVo> setMealListVoList = setMealPage.getResult().parallelStream()
                    .map(setMeal -> {
                        SetMealListVo setMealListVo = new SetMealListVo();
                        BeanUtils.copyProperties(setMeal, setMealListVo);
                        return setMealListVo;
                    })
                    .collect(Collectors.toList());
            return new PageData<>(setMealListVoList, (int) setMealPage.getTotal());
        }
    }

    @Override
    public SetMealModifyVo findSetMealWithDishById(Long id) {
        SetMeal setMeal = setMealMapper.findSetMealWithDishById(id);
        SetMealModifyVo setMealModifyVo = new SetMealModifyVo();
        BeanUtils.copyProperties(setMeal, setMealModifyVo);
        return setMealModifyVo;
    }

    @Override
    public int insert(SetMeal setMeal, Employee createUser) {
        return 0;
    }

    @Override
    public int update(SetMeal setMeal, Employee updateUser) {
        setMeal.setUpdateTime(new Date());
        setMeal.setUpdateUser(updateUser.getId());
        return setMealMapper.update(setMeal);
    }

    @Override
    public int addSetMeal(SetMealModifyVo setMealModifyVo, Employee crateUser) {
        return 0;
    }

    @Override
    @Transactional
    public int modifySetMeal(SetMealModifyVo setMealModifyVo, Employee updateUser) {
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealModifyVo, setMeal);
        int rowOfNumber = update(setMeal, updateUser);

        List<SetMealDish> setMealDishes = setMealModifyVo.getSetmealDishes().parallelStream()
                .map(dishModifyVo -> {
                    SetMealDish setMealDish = new SetMealDish();
                    BeanUtils.copyProperties(dishModifyVo, setMealDish);
                    setMealDish.setSetmealId(setMeal.getId().toString());
                    setMealDish.setDishId(dishService.findIdByName(setMealDish.getName()).getId().toString());
                    return setMealDish;
                })
                .toList();

        setMealDishService.delete(setMeal.getId());

        setMealDishService.insert(setMealDishes, updateUser);
        return rowOfNumber;
    }

}
