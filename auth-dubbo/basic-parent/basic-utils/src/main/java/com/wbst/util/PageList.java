package com.wbst.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageList<T> implements Serializable{
    //总条数
    private Long total;

    //总页数
    private Integer totalPages;


    //返回数据
    private List<T> rows = new ArrayList<>();

    public PageList(){}

    public PageList(Long total, Integer totalPages, List<T> rows) {
        this.total = total;
        this.totalPages = totalPages;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
