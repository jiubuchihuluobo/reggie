package com.surge;

import com.surge.common.SnowFlakeUtil;
import org.junit.jupiter.api.Test;

public class SnowFlakeTest {

    @Test
    public void getId() {
        System.out.println(SnowFlakeUtil.getId());
    }

}
