package com.surge.common;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class SnowFlakeUtil {

    private static final Snowflake snowFlake = IdUtil.getSnowflake();

    public static Long getId() {
        return snowFlake.nextId();
    }

    public static String getIdStr() {
        return snowFlake.nextIdStr();
    }

}
