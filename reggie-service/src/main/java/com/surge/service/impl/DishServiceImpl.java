package com.surge.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.surge.common.PageInfo;
import com.surge.mapper.DishMapper;
import com.surge.reggie.domain.DishWithCategoryAndFlavorVo;
import com.surge.reggie.domain.DishWithCategoryVo;
import com.surge.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;

    public DishServiceImpl(DishMapper dishMapper) {
        this.dishMapper = dishMapper;
    }

    @Override
    public PageInfo<DishWithCategoryVo> DishManagementList(int page, int pageSize, String name) {
        Page<DishWithCategoryVo> dishPage = PageHelper.startPage(page, pageSize);
        dishMapper.find(name);
        return new PageInfo<>(dishPage, dishPage.getTotal());
    }

    @Override
    public DishWithCategoryAndFlavorVo findDish(Long id) {
        return dishMapper.findById(id);
    }

}

