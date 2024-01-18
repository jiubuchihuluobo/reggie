package com.surge.service.impl;

import com.surge.common.SnowFlakeUtil;
import com.surge.mapper.SetMealDishMapper;
import com.surge.reggie.domain.Employee;
import com.surge.reggie.domain.SetMealDish;
import com.surge.service.SetMealDishService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SetMealDishServiceImpl implements SetMealDishService {

    private final SetMealDishMapper setMealDishMapper;

    public SetMealDishServiceImpl(SetMealDishMapper setMealDishMapper) {
        this.setMealDishMapper = setMealDishMapper;
    }

    @Override
    public int insert(List<SetMealDish> setMealDishes, Employee createUser) {
        setMealDishes = setMealDishes.parallelStream()
                .peek(setMealDish -> {
                    setMealDish.setId(SnowFlakeUtil.getId());
                    setMealDish.setCreateTime(new Date());
                    setMealDish.setCreateUser(createUser.getId());
                    setMealDish.setUpdateTime(new Date());
                    setMealDish.setUpdateUser(createUser.getId());
                })
                .toList();
        return setMealDishMapper.insert(setMealDishes);
    }

    @Override
    public int delete(Long id) {
        return setMealDishMapper.deleteBySetMealId(id);
    }

    @Override
    public int update(SetMealDish setMealDish, Employee updateUser) {
        setMealDish.setUpdateTime(new Date());
        setMealDish.setUpdateUser(updateUser.getId());
        return setMealDishMapper.update(setMealDish);
    }

}
