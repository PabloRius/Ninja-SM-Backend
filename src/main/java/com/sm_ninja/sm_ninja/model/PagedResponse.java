package com.sm_ninja.sm_ninja.model;

import java.util.List;

public class PagedResponse<T> {
    private List<T> data;
    private int page;
    private int limit;
    private int totalItems;
    private int totalPages;

    public PagedResponse(List<T> data, int page, int limit, int totalItems) {
        this.data = data;
        this.page = page;
        this.limit = limit;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / limit);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}