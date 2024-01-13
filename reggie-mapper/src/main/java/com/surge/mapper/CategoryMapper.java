package com.surge.mapper;

import com.surge.reggie.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> findAll();

    Long insert(Category category);

    Long update(Category category);

    Category findById(Long id);

    int delete(Long id);

}
