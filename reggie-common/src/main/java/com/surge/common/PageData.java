package com.surge.common;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {

    private List<T> records;

    private int total;

    public PageData(List<T> dishList, int total) {
        this.records = dishList;
        this.total = total;
    }

}
