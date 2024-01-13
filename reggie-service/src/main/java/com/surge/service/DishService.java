package com.surge.service;

import com.surge.common.PageInfo;
import com.surge.reggie.domain.DishWithCategoryAndFlavorVo;
import com.surge.reggie.domain.DishWithCategoryVo;

public interface DishService {

    PageInfo<DishWithCategoryVo> DishManagementList(int page, int pageSize, String name);

    DishWithCategoryAndFlavorVo findDish(Long id);

}
