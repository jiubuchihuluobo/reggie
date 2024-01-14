package com.surge.service.impl;

import com.surge.common.SnowFlakeUtil;
import com.surge.mapper.CategoryMapper;
import com.surge.reggie.domain.Category;
import com.surge.reggie.domain.CategoryDropDownVo;
import com.surge.reggie.domain.Employee;
import com.surge.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public Category save(Category category, Employee employee) {
        if (category.getId() == null) {
            category.setId(SnowFlakeUtil.getId());
            category.setCreateTime(new Date());
            category.setUpdateTime(new Date());
            category.setCreateUser(employee.getId());
            category.setUpdateUser(employee.getId());
            categoryMapper.insert(category);
        }
        {
            category.setUpdateUser(employee.getId());
            category.setUpdateTime(new Date());
            categoryMapper.update(category);
        }
        category = categoryMapper.findById(category.getId());
        return category;
    }

    @Override
    public int delete(Long id) {
        return categoryMapper.delete(id);
    }

    @Override
    public List<CategoryDropDownVo> categoryDropDown(int type) {
        return categoryMapper.findCategoryByType(type);
    }

}
