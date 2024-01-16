package com.surge.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.surge.common.PageData;
import com.surge.mapper.SetMealMapper;
import com.surge.reggie.domain.SetMeal;
import com.surge.reggie.domain.SetMealListVo;
import com.surge.reggie.domain.SetMealModifyVo;
import com.surge.service.SetMealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetMealServiceImpl implements SetMealService {

    private final SetMealMapper setMealMapper;

    public SetMealServiceImpl(SetMealMapper setMealMapper) {
        this.setMealMapper = setMealMapper;
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

}
