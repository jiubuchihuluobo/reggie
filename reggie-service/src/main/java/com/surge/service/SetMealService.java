package com.surge.service;

import com.surge.common.PageData;
import com.surge.reggie.domain.SetMealListVo;

public interface SetMealService {

    PageData<SetMealListVo> setMealList(int page, int pageSize, String name);

}
