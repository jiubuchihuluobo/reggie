package com.surge.common;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {

    private List<T> records;

    private int total;

    public PageInfo(List<T> dishList, int total) {
        this.records = dishList;
        this.total = total;
    }

}
