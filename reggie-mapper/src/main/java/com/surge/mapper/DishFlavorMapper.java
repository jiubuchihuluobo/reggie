package com.surge.mapper;

import com.surge.reggie.domain.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    int insert(List<DishFlavor> dishFlavorList);

    int update(DishFlavor dishFlavor);

}
