package com.surge.service;

import com.surge.common.PageData;
import com.surge.reggie.domain.SetMealListVo;
import com.surge.reggie.domain.SetMealModifyVo;

public interface SetMealService {

    PageData<SetMealListVo> setMealList(int page, int pageSize, String name);

    SetMealModifyVo findSetMealWithDishById(Long id);

}
