package com.surge.service;

import com.surge.reggie.domain.Category;
import com.surge.reggie.domain.CategoryDropDownVo;
import com.surge.reggie.domain.Employee;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category save(Category category, Employee employee);

    int delete(Long id);

    List<CategoryDropDownVo> categoryDropDown(int type);

}
