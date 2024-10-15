package com.itbh.hevent.dtos;

import java.util.List;

public class PageBean<T>  {
    private long total;
    private List<T> content;

    public PageBean(List<T> content, long total) {
        this.content = content;
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
